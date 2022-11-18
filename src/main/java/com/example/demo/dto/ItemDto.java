package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ItemDto {

    private Integer id;

    private Integer contained;

    private String color;
}
