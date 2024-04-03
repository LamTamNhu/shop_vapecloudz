package com.shop_vapecloudz.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(200)")
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "item_image_id",referencedColumnName = "id")
    private ItemImage itemImage;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "date default (curdate())")
    private LocalDate createDate;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
}
