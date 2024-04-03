package com.shop_vapecloudz.repository;

import com.shop_vapecloudz.model.ItemVariant;
import com.shop_vapecloudz.model.dto.IItemVariantDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemVariantRepository extends JpaRepository<ItemVariant, Long> {
    List<IItemVariantDTO> findAllByItem_Id(Long id);
}
