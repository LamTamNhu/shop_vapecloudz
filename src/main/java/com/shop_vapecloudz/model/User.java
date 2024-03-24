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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(50)")
    private String userName;
    private LocalDate birthday;
    @Column(columnDefinition = "varchar(50)")
    private String email;
    @Column(columnDefinition = "varchar(50)")
    private String password;
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;
}
