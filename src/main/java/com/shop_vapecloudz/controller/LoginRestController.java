package com.shop_vapecloudz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginRestController {
    @GetMapping("/")
    public ResponseEntity<Void> hello() {
        System.out.println("Hello");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
