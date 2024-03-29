package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.model.dto.IItemDTO;
import com.shop_vapecloudz.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/item")
public class ItemRestController {
    private final IItemService iItemService;

    @Autowired
    public ItemRestController(IItemService iItemService) {
        this.iItemService = iItemService;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<IItemDTO>> findAll(@PageableDefault(size = 10) Pageable pageable,
                                                  @RequestParam(value = "brand_id", defaultValue = "") Long brandId,
                                                  @RequestParam(value = "category_id", defaultValue = "") Long categoryId,
                                                  @RequestParam(value = "name", defaultValue = "") String name) {
        Page<IItemDTO> iItemDTOS = iItemService.findAll(pageable, brandId, categoryId, name);
        return new ResponseEntity<>(iItemDTOS, HttpStatus.OK);
    }
}
