package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.Item;
import com.shop_vapecloudz.model.ItemVariant;
import com.shop_vapecloudz.model.UserCart;
import com.shop_vapecloudz.model.UserEntity;
import com.shop_vapecloudz.model.dto.CartAddDTO;
import com.shop_vapecloudz.model.dto.IItemDTO;
import com.shop_vapecloudz.repository.ItemRepository;
import com.shop_vapecloudz.repository.ItemVariantRepository;
import com.shop_vapecloudz.repository.UserCartRepository;
import com.shop_vapecloudz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService implements IItemService {
    private final ItemRepository itemRepository;
    private final UserCartRepository userCartRepository;
    private final UserRepository userRepository;
    private final ItemVariantRepository itemVariantRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, UserCartRepository userCartRepository, UserRepository userRepository, ItemVariantRepository itemVariantRepository) {
        this.itemRepository = itemRepository;
        this.userCartRepository = userCartRepository;
        this.userRepository = userRepository;
        this.itemVariantRepository = itemVariantRepository;
    }

    @Override
    public Page<IItemDTO> findAll(Pageable pageable, Long brandId, Long categoryId, String name) {
        return itemRepository.findAllItems(pageable, brandId, categoryId, name);
    }

    @Override
    public void addToCartByUserEmail(CartAddDTO cartAddDTO) {
        UserCart userCart = userCartRepository.findByUserEntityEmailAndItemVariantId(cartAddDTO.getEmail(), cartAddDTO.getId()).orElse(null);
        if (userCart != null) {
            userCart.setItemAmount(userCart.getItemAmount() + 1);
        } else {
            UserEntity userEntity = userRepository.findByEmail(cartAddDTO.getEmail()).orElse(null);
            userCart = new UserCart();
            userCart.setUserEntity(userEntity);
            ItemVariant itemVariant = itemVariantRepository.findById(cartAddDTO.getId()).orElse(null);
            userCart.setItemVariant(itemVariant);
            userCart.setItemAmount(1);
        }
        userCartRepository.save(userCart);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }
}
