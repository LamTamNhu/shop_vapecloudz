package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.UserEntity;
import com.shop_vapecloudz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements IAuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public boolean checkUserExist(String username, String email) {
        if (Objects.equals(username, "")) {
            return userRepository.existsByEmail(email);
        }
        return userRepository.existsByUsername(username);
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
}
