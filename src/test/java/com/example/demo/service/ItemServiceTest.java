package com.example.demo.service;

import com.example.demo.config.DataOfFile;
import com.example.demo.config.LoadArgumentsFromCommandLine;
import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.mapper.MapStructMapper;
import com.example.demo.model.BodyRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        when(boxRepository.save(box)).thenReturn(box);
        when(boxRepository.getBoxesByContained(anyInt())).thenReturn(List.of(box));;

        List<Item> items = new ArrayList<>();
        Item firstItem = new Item();
        firstItem.setId(1);
        firstItem.setContained(1);
        firstItem.setColor("red");

        Item secondItem = new Item();
        firstItem.setId(2);
        firstItem.setContained(1);
        firstItem.setColor("blue");

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