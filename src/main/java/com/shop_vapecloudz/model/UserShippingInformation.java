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
public class UserShippingInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
    @Column(columnDefinition = "varchar(10)")
    private String phoneNumber;
    @Column(columnDefinition = "varchar(100)")
    private String address;
    @Column(columnDefinition = "varchar(15)")
    private String city;
}
