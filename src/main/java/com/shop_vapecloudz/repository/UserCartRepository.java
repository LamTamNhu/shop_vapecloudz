package com.shop_vapecloudz.repository;

import com.shop_vapecloudz.model.UserCart;
import com.shop_vapecloudz.model.dto.IUserCartDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserCartRepository extends JpaRepository<UserCart, Long> {
    Optional<UserCart> findByUserEntityEmailAndItemVariantId(String email, Long id);

    List<IUserCartDTO> findAllByUserEntityEmail(String email);
}
