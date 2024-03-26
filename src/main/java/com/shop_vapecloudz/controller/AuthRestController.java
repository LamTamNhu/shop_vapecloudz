package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.config.JWTTokenHandler;
import com.shop_vapecloudz.model.Role;
import com.shop_vapecloudz.model.UserEntity;
import com.shop_vapecloudz.model.dto.AuthResponseDTO;
import com.shop_vapecloudz.model.dto.UserDTO;
import com.shop_vapecloudz.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/auth")
public class AuthRestController {
    private final IAuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTTokenHandler jwtTokenHandler;

    @Autowired
    public AuthRestController(IAuthService authService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTTokenHandler jwtTokenHandler) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenHandler.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        if (authService.checkUserExistByUsername(userDTO.getUsername())) {
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        Role userRole = new Role();
        userRole.setId(2L);
        user.setRole(userRole);
        authService.saveUser(user);
        return new ResponseEntity<>("New account registered success!", HttpStatus.OK);
    }
}
