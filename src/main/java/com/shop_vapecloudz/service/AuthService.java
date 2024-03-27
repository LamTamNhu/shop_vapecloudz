package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.UserEntity;
import com.shop_vapecloudz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public boolean checkUserExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }
}
