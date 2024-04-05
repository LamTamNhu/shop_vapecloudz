package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.config.JWTTokenHandler;
import com.shop_vapecloudz.model.Role;
import com.shop_vapecloudz.model.UserEntity;
import com.shop_vapecloudz.model.dto.AuthResponseDTO;
import com.shop_vapecloudz.model.dto.UserDTO;
import com.shop_vapecloudz.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
        UserEntity user = authService.findByEmail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("Can't find any user with this email!");
        }
        if (user.getIsDeleted()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), userDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenHandler.generateToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setAccessToken(token);
        authResponseDTO.setUsername(user.getUsername());
        authResponseDTO.setRole(authentication.getAuthorities().toString());
        authResponseDTO.setEmail(user.getEmail());
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(new Role(2L));
        user.setBirthday(LocalDate.parse(userDTO.getBirthday()));
        authService.saveUser(user);
        return new ResponseEntity<>("New account registered success!", HttpStatus.OK);
    }

    @GetMapping("/duplicated")
    public ResponseEntity<Boolean> checkDuplicated(@RequestParam("email") String email,
                                                   @RequestParam("username") String username) {
        Boolean isDuplicated = authService.checkUserExist(username, email);
        return new ResponseEntity<>(isDuplicated, HttpStatus.OK);
    }
}
