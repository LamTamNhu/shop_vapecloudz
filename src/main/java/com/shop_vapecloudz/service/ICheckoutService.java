package com.shop_vapecloudz.service;

import com.shop_vapecloudz.model.dto.PayPalOrderDTO;

public interface ICheckoutService {

    String createOrder(PayPalOrderDTO payPalOrderDTO);

    void captureOrder(PayPalOrderDTO payPalOrderDTO);
}
