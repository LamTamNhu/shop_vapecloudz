package com.shop_vapecloudz.model.dto;

public interface IUserCartDTO {
    Long getId();

    Integer getAmount();

    ItemVariantDTO getItemVariant();

    interface ItemVariantDTO {
        Long getId();

        String getName();

        Double getPrice();

        ItemImageUrl getItemImage();

        Item getItem();

        interface Item {
            String getName();
            Long getId();
        }

        interface ItemImageUrl {
            String getUrl();
        }
    }
}
