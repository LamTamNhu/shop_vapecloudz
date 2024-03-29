package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.dto.IItemDTO;
import com.shop_vapecloudz.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<IItemDTO> findAll(Pageable pageable, Long brandId, Long categoryId, String name) {
        return itemRepository.findAllItems(pageable,brandId,categoryId,name);
    }
}
