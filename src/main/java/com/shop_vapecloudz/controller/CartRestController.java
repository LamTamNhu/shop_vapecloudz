package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.model.dto.CartEditDTO;
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
    public ResponseEntity<Void> addToCart(@RequestBody CartEditDTO cartEditDTO) {
        itemService.addToCartByUserEmail(cartEditDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<IUserCartDTO>> getCart(@PathVariable String email) {
        List<IUserCartDTO> userCarts = itemService.getCartByUserEmail(email);
        return new ResponseEntity<>(userCarts, HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<Void> editCart(@RequestBody CartEditDTO cartEditDTO) {
        itemService.editCart(cartEditDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
