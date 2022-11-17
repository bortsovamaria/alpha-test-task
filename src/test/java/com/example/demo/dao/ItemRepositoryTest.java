package com.example.demo.dao;

import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private BoxRepository boxRepository;

    @Test
    void getItemsByColor() {
        Item item = new Item();
        item.setId(1);
        item.setContained(2);
        item.setColor("red");

        Box box = new Box();
        box.setId(2);
        boxRepository.save(box);

        itemRepository.save(item);
        List<Item> items = itemRepository.getItemsByColorAndContained("red", 2);
        assertTrue(items.contains(item));
    }

    @Test
    void getItemsByColorFail() {
        Item item = new Item();
        item.setId(1);
        item.setContained(2);
        item.setColor("red");

        Box box = new Box();
        box.setId(2);
        boxRepository.save(box);

        itemRepository.save(item);
        List<Item> items = itemRepository.getItemsByColorAndContained("blue", 2);
        assertFalse(items.contains(item));
    }

}