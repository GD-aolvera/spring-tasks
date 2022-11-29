package com.gd.clinic.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gd.clinic.model.JwtResponseDto;
import com.gd.clinic.security.service.UserMain;
import org.springframework.beans.factory.annotation.Configurable;
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


@ComponentScan
@Configurable
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String accessTokenSecret;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    public JWTAuthenticationFilter(String accessTokenSecret) {
        this.accessTokenSecret = accessTokenSecret;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException ignored) {
            throw new RuntimeException();
        }
        UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(authCredentials.getUsername(), authCredentials.getPassword(), Collections.emptyList());
        return getAuthenticationManager().authenticate(userToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        UserMain userDetails = (UserMain) authResult.getPrincipal();
        String token = TokenUtils.createToken(userDetails.getUsername(), userDetails.getName(),
                accessTokenSecret);
        PrintWriter writer = response.getWriter();
        response.addHeader("Authorization", "Bearer " + token);
        response.setHeader("Content-Type", "text/plain");
        writer.write(objectMapper().writeValueAsString(new JwtResponseDto(token, null)));
        writer.flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }

}
