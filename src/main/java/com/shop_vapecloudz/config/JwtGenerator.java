package com.shop_vapecloudz.config;

import org.springframework.security.core.Authentication;

import java.util.Date;

public class JwtGenerator {
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        return "";
    }
}
