package com.gd.clinic.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.clinic.model.JwtResponseDto;
import com.gd.clinic.security.service.RefreshTokenService;
import com.gd.clinic.security.service.UserMain;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;


@RequiredArgsConstructor
@ComponentScan
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private RefreshTokenService refreshTokenService;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public JWTAuthenticationFilter(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        try {

            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException ignored) {
        }
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(authCredentials.getUsername(), authCredentials.getPassword(), Collections.emptyList());
        return getAuthenticationManager().authenticate(userToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserMain userDetails = (UserMain) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getName());
        String refreshToken = refreshTokenService.createToken(userDetails.getUser());
        PrintWriter writer = response.getWriter();
        response.addHeader("Authorization", "Bearer " + token);
        response.setHeader("RefreshToken", refreshToken);
        response.setHeader("Content-Type", "text/plain");
        writer.write(objectMapper().writeValueAsString(new JwtResponseDto(token, refreshToken)));
        writer.flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }


}
