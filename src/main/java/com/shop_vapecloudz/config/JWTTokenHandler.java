package com.shop_vapecloudz.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTokenHandler {
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRE_TIME);
        return Jwts.builder().subject(username).issuedAt(currentDate).expiration(expireDate).signWith(Keys.password(SecurityConstant.JWT_SECRET.toCharArray())).compact();
    }

    public String getUsernameFromJWT(String token) {
        String jwt;
        try {
            jwt = Jwts.parser().verifyWith(Keys.password(SecurityConstant.JWT_SECRET.toCharArray())).build().parseSignedClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            e.printStackTrace();
            return null;
        }
        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(Keys.password(SecurityConstant.JWT_SECRET.toCharArray())).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
