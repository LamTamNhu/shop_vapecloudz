package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.Item;
import com.shop_vapecloudz.model.dto.CartAddDTO;
import com.shop_vapecloudz.model.dto.IItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IItemService {
    Page<IItemDTO> findAll(Pageable pageable, Long brandId, Long categoryId, String name);

    void addToCartByUserEmail(CartAddDTO cartAddDTO);

    Item findById(Long id);
}
