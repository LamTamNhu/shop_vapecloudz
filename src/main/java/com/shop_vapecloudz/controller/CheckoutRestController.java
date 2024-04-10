package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.model.dto.PayPalOrderDTO;
import com.shop_vapecloudz.service.ICheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/checkout")
public class CheckoutRestController {
    private final ICheckoutService checkoutService;


    @Autowired
    public CheckoutRestController(ICheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody PayPalOrderDTO payPalOrderDTO) {
        String orderId = checkoutService.createOrder(payPalOrderDTO);
        return new ResponseEntity<>(orderId, HttpStatus.OK);
    }

    @PostMapping("/capture")
    public ResponseEntity<Void> captureOrder(@RequestBody PayPalOrderDTO payPalOrderDTO) {
        checkoutService.captureOrder(payPalOrderDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
