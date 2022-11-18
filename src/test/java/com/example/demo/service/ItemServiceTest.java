package com.example.demo.service;

import com.example.demo.config.LoadArgumentsFromCommandLine;
import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.BodyRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase
class ItemServiceTest {

    @Autowired
    private ItemService itemService;;
    @Autowired
    MapStructMapper mapStructMapper;

    @MockBean
    LoadArgumentsFromCommandLine commandLineRunner;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    BoxRepository boxRepository;
    @MockBean
    private XmlService xmlService;

    @Test
    void getIdsOfItemsByColor() {
        List<Box> boxes = new ArrayList<>();
        Box box = new Box();
        box.setId(1);
        boxes.add(box);
        Box secondBox = new Box();
        box.setId(2);
        boxes.add(box);
        when(boxRepository.save(box)).thenReturn(box);
        when(boxRepository.getBoxes(anyInt())).thenReturn(new ArrayList<>());
        when(boxRepository.getBoxes(anyInt())).thenReturn(new ArrayList<>());

        List<Item> items = new ArrayList<>();
        Item firstItem = new Item();
        firstItem.setId(1);
        firstItem.setContained(1);
        firstItem.setColor("red");

        Item secondItem = new Item();
        firstItem.setId(2);
        firstItem.setContained(1);
        firstItem.setColor("blue");

        when(itemRepository.save(firstItem)).thenReturn(firstItem);
        when(itemRepository.save(secondItem)).thenReturn(secondItem);

        items.add(firstItem);
        items.add(secondItem);

        Item item = items.get(1);
        when(itemRepository.getItemsByColorAndContained("red", 1))
                .thenReturn(List.of(item));

        BodyRequest bodyRequest = new BodyRequest().setBox(1).setColor("red");
        List<Integer> response = itemService.getIdsOfItemsByColor(bodyRequest);

        assertEquals(response.get(0), items.get(0).getId());
    }

}