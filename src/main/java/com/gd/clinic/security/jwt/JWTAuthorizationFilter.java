package com.gd.clinic.security.jwt;

import com.gd.clinic.security.service.UserDetailServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TokenUtils.getAccessTokenSecret().getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token, userDetailService.loadUserByUsername(claims.getSubject()));
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
            System.out.println(SecurityContextHolder.getContext());
        }
        filterChain.doFilter(request, response);
    }

}
