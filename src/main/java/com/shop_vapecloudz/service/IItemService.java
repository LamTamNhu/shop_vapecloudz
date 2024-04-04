package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.dto.CartAddDTO;
import com.shop_vapecloudz.model.dto.IItemDTO;
import com.shop_vapecloudz.model.dto.IUserCartDTO;
import com.shop_vapecloudz.model.dto.ItemDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IItemService {
    Page<IItemDTO> findAll(Pageable pageable, Long brandId, Long categoryId, String name);

    void addToCartByUserEmail(CartAddDTO cartAddDTO);

    ItemDetailDTO findItemDetailById(Long id);

    List<IUserCartDTO> getCartByUserEmail(String email);
}
