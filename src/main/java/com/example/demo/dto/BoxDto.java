package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BoxDto {

    private Integer id;

    private Integer contained;
}
