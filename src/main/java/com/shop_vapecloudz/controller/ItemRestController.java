package com.shop_vapecloudz.controller;

import com.shop_vapecloudz.model.Item;
import com.shop_vapecloudz.model.dto.IItemDTO;
import com.shop_vapecloudz.model.dto.ItemDetailDTO;
import com.shop_vapecloudz.repository.ItemRepository;
import com.shop_vapecloudz.service.IItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/item")
public class ItemRestController {
    private final IItemService itemService;

    @Autowired
    public ItemRestController(IItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<IItemDTO>> findAll(@PageableDefault(size = 8, sort = "create_date", direction = Sort.Direction.DESC) Pageable pageable,
                                                  @RequestParam(value = "brand_id", defaultValue = "") Long brandId,
                                                  @RequestParam(value = "category_id", defaultValue = "") Long categoryId,
                                                  @RequestParam(value = "name", defaultValue = "") String name) {
        Page<IItemDTO> iItemDTOS = itemService.findAll(pageable, brandId, categoryId, name);
        return new ResponseEntity<>(iItemDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDetailDTO> getItemDetails(@PathVariable Long id) {
        ItemDetailDTO item = itemService.findItemDetailById(id);
        if (item == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
