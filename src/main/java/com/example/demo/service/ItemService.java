package com.example.demo.service;

import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import com.example.demo.model.BodyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final BoxRepository boxRepository;

    public List<Integer> getIdsOfItemsByColor(BodyRequest bodyRequest) {
        List<Box> boxes = boxRepository.getBoxesByContained(bodyRequest.getBox());
        boxes.addAll(boxRepository.getBoxesById((bodyRequest.getBox())));

        return boxes.stream()
                .map(m -> itemRepository.getItemsByColorAndContained(bodyRequest.getColor(), m.getId()))
                .map(l -> l.stream().map(Item::getId).collect(Collectors.toList()))
                .flatMap(List::stream)
                .sorted()
                .collect(Collectors.toList());
    }
}
