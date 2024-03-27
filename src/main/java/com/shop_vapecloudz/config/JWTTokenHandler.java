package com.shop_vapecloudz.config;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JWTTokenHandler {
    SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRE_TIME);
        return Jwts.builder().subject(username).issuedAt(currentDate).expiration(expireDate).signWith(key).compact();
    }


    public String getUsernameFromJWT(String token) {
        String jwt;
        try {
            jwt = Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getSubject();
        } catch (JwtException e) {
            e.printStackTrace();
            return null;
        }
        return jwt;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
