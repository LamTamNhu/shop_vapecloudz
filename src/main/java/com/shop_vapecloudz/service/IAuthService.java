package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.UserEntity;

public interface IAuthService {
    UserEntity findByEmail(String username);

    boolean checkUserExist(String username, String email);

    void saveUser(UserEntity user);
}
