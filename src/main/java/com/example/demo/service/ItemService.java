package com.example.demo.service;

import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import com.example.demo.model.BodyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final BoxRepository boxRepository;

    public List<Integer> getIdsOfItemsByColor(BodyRequest bodyRequest) {
        List<Box> boxes = boxRepository.getBoxesByContained(bodyRequest.getBox());
        List<List<Item>> lists = boxes.stream()
                .map(m -> itemRepository.getItemsByColorAndContained(bodyRequest.getColor(), m.getId()))
                .flatMap(Stream::of)
                .collect(Collectors.toList());

//        List<Item> items = itemRepository.getItemsByColorAndContained(bodyRequest.getColor(), boxes);
//        return items.stream()
//                .map(Item::getId)
//                .collect(Collectors.toList());
        return new ArrayList<>();
    }
}
