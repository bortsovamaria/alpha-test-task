package com.example.demo.mapper;

import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import com.example.demo.dto.BoxDto;
import com.example.demo.dto.ItemDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface MapStructMapper {

    ItemDto itemToDto(Item item);

    Item dtoToItem(ItemDto dto);

    Box dtoToBox(BoxDto dto);
}
