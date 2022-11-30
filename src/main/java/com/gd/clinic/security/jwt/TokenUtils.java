package com.gd.clinic.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@UtilityClass
public class TokenUtils {

    private final Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;

    public String createToken(String username, String lastName, String accessTokenSecret) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("lastName", lastName);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(accessTokenSecret.getBytes()))
                .compact();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String token, UserDetails userMain, String accesTokenSecret) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(accesTokenSecret.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            String user = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(user, null, userMain.getAuthorities());
        } catch (JwtException e) {
            return null;
        }
    }

}
