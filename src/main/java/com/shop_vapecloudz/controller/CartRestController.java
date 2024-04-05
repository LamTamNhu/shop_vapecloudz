package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.model.UserCart;
import com.shop_vapecloudz.model.dto.CartAddDTO;
import com.shop_vapecloudz.model.dto.IUserCartDTO;
import com.shop_vapecloudz.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{email}")
    public ResponseEntity<List<IUserCartDTO>> getCart(@PathVariable String email) {
        System.out.println(email);
        List<IUserCartDTO> userCarts = itemService.getCartByUserEmail(email);
        return new ResponseEntity<>(userCarts, HttpStatus.OK);
    }
}
