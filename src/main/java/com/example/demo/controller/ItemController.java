package com.example.demo.controller;

import com.example.demo.model.BodyRequest;
import com.example.demo.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getItemsIds(@RequestBody BodyRequest bodyRequest) {
        return itemService.getIdsOfItemsByColor(bodyRequest);
    }
}
