package com.shop_vapecloudz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "item_id",referencedColumnName = "id")
    private Item item;
    @Column(columnDefinition = "varchar(100)")
    private String name;
    private Double price;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "item_image_id",referencedColumnName = "id")
    private ItemImage itemImage;
}
