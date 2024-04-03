package com.shop_vapecloudz.model.dto;

import com.shop_vapecloudz.model.Item;
import com.shop_vapecloudz.model.ItemVariant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetailDTO {
    private ItemDTO itemDTO;
    List<IItemVariantDTO> itemVariantDTOS;
}
