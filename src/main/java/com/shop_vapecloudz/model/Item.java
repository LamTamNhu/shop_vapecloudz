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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(100)")
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @OneToOne
    @JoinColumn(name = "item_image_id",referencedColumnName = "id")
    private ItemImage itemImage;
    @Column(columnDefinition = "varchar(2000)")
    private String description;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
}
