package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.model.dto.CartAddDTO;
import com.shop_vapecloudz.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/customer/cart")
public class CartRestController {
    private final ItemService itemService;

    @Autowired
    public CartRestController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody CartAddDTO cartAddDTO) {
        itemService.addToCartByUserEmail(cartAddDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
