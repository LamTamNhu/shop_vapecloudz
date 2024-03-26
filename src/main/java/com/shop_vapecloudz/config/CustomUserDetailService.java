package com.shop_vapecloudz.config;

import com.shop_vapecloudz.model.UserEntity;
import com.shop_vapecloudz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
        if (user.getIsDeleted()) {
            throw new UsernameNotFoundException("This account is locked!");
        }
        SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority(user.getRole().getName());
        return new User(user.getUsername(), user.getPassword(), Collections.singleton(userAuthority));
    }
}
