package com.shop_vapecloudz.repository;

import com.shop_vapecloudz.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart,Long> {
    Optional<UserCart> findByUserEntityEmailAndItemVariantId(String email, Long id);
}
