package com.shop_vapecloudz.repository;

import com.shop_vapecloudz.model.ItemVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {
}
