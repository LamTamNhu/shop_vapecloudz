package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.UserEntity;

public interface IAuthService {
    UserEntity findByEmail(String username);

    boolean checkUserExistByEmail(String username);

    void saveUser(UserEntity user);
}
