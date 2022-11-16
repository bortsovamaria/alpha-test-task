package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {

    private Integer id;
    private Integer containedIn;
    private String color;
}
