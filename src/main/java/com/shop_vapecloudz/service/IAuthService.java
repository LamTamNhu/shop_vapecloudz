package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.UserEntity;

public interface IAuthService {
    UserEntity findByUsername(String username);

    boolean checkUserExistByUsername(String username);

    void saveUser(UserEntity user);
}
