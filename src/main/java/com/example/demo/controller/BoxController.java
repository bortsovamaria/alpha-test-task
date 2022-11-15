package com.example.demo.controller;

import com.example.demo.domain.Box;
import com.example.demo.service.BoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoxController {

    private final BoxService boxService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Box getBox(Integer id) {
        return boxService.getItem(id).get();
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public Box createBox(Box box) {
        return boxService.createItem(box);
    }

}
