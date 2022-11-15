package com.example.demo.controller;

import com.example.demo.domain.Item;
import com.example.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Item getBox(Integer id) {
        return itemService.getItem(id).get();
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Item createBox(Item item) {
        return itemService.createItem(item);
    }
}
