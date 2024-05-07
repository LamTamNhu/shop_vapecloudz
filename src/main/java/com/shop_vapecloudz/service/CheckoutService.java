package com.shop_vapecloudz.service;

import com.paypal.http.HttpResponse;
import com.paypal.http.exceptions.HttpException;
import com.paypal.orders.*;
import com.shop_vapecloudz.config.Credentials;
import com.shop_vapecloudz.model.dto.PayPalOrderDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CheckoutService implements ICheckoutService {
    @Override
    public String createOrder(PayPalOrderDTO payPalOrderDTO) {
        Order order;
        // Construct a request object and set desired parameters
        // Here, OrdersCreateRequest() creates a POST request to /v2/checkout/orders
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        List<PurchaseUnitRequest> purchaseUnits = new ArrayList<>();
        purchaseUnits.add(new PurchaseUnitRequest().amountWithBreakdown(new AmountWithBreakdown().currencyCode("USD").value(payPalOrderDTO.getTotalPrice().toString())));
        orderRequest.purchaseUnits(purchaseUnits);
        OrdersCreateRequest request = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            // Call API with your client and get a response for your call
            HttpResponse<Order> response = Credentials.client.execute(request);

            // If call returns body in response, you can get the de-serialized version by
            // calling result() on the response
            order = response.result();
            System.out.println("Order ID: " + order.id());
            return order.id();
        } catch (IOException ioe) {
            if (ioe instanceof HttpException he) {
                // Something went wrong server-side
                System.out.println(he.getMessage());
                he.headers().forEach(x -> System.out.println(x + " :" + he.headers().header(x)));
            }
        }
        return null;
    }

    @Override
    public void captureOrder(PayPalOrderDTO payPalOrderDTO) {
        Order order;
        OrdersCaptureRequest request = new OrdersCaptureRequest(payPalOrderDTO.getOrderId());

        try {
            // Call API with your client and get a response for your call
            HttpResponse<Order> response = Credentials.client.execute(request);

            // If call returns body in response, you can get the de-serialized version by
            // calling result() on the response
            order = response.result();
            System.out.println("Capture ID: " + order.purchaseUnits().get(0).payments().captures().get(0).id());
            order.purchaseUnits().get(0).payments().captures().get(0).links()
                    .forEach(link -> System.out.println(link.rel() + " => " + link.method() + ":" + link.href()));
        } catch (IOException ioe) {
            if (ioe instanceof HttpException) {
                // Something went wrong server-side
                HttpException he = (HttpException) ioe;
                System.out.println(he.getMessage());
                he.headers().forEach(x -> System.out.println(x + " :" + he.headers().header(x)));
            } else {
                // Something went wrong client-side
            }
        }
    }
}
