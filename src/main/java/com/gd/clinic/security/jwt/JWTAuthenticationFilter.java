package com.gd.clinic.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.clinic.security.application.RefreshTokenService;
import com.gd.clinic.security.entity.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
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

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    private final RefreshTokenService refreshTokenService;

    public JWTAuthenticationFilter(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException ignored){
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(authCredentials.getUsername(), authCredentials.getPassword(), Collections.emptyList());
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserMain userDetails = (UserMain) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getName());
        //var user = userService.getByUserName(userDetails.getUsername());
        String refreshToken = refreshTokenService.createToken(userDetails.getUser());
        PrintWriter writer = response.getWriter();
        response.addHeader("Authorization", "Bearer " + token);
        response.setHeader("RefreshToken",refreshToken);
        response.setHeader("Content-Type", "text/plain");
        response.getWriter().write(objectMapper.writeValueAsString(JwtResponseDto.of(token, refreshToken)));
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
