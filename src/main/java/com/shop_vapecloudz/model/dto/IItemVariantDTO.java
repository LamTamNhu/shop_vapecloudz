package com.shop_vapecloudz.model.dto;


public interface IItemVariantDTO {
    String getId();

    String getName();
    ImageUrl getItemImage();
    Double getPrice();
    interface ImageUrl{
        String getUrl();
    }
}
