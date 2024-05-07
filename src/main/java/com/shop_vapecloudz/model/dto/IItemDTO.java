package com.shop_vapecloudz.model.dto;

import com.shop_vapecloudz.model.Brand;
import com.shop_vapecloudz.model.Category;
import com.shop_vapecloudz.model.ItemImage;

public interface IItemDTO {
    Long getItemId();

    String getItemName();

    String getDescription();

    String getUrl();
    Double getPrice();
}
