package com.example.demo.service;

import com.example.demo.config.DataOfFile;
import com.example.demo.config.LoadArgumentsFromCommandLine;
import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Item;
import com.example.demo.mapper.MapStructMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class XmlServiceTest {

    @Autowired
    private XmlService xmlService;
    @Autowired
    MapStructMapper mapStructMapper;

    @Autowired
    DataOfFile dataOfFile;

    @MockBean
    LoadArgumentsFromCommandLine commandLineRunner;
    @MockBean
    ItemRepository itemRepository;
    @MockBean
    BoxRepository boxRepository;

    @Test
    void loadXml() throws IOException, SAXException, ParserConfigurationException {
        when(boxRepository.getBoxes(anyInt())).thenReturn(List.of(1));

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

        when(itemRepository.saveAll(items)).thenReturn(new ArrayList<>());
        when(itemRepository.getItemsByColorAndContained("red", 1))
                .thenReturn(List.of(firstItem));

        Path resourceDirectory = Paths.get("src","test", "resources", "test.xml");
        String absolutePath = resourceDirectory.toFile().getAbsolutePath();
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "file");
        map.put("path", absolutePath);
        dataOfFile.setType(map);

        xmlService.loadDataFromXml();

    }

}