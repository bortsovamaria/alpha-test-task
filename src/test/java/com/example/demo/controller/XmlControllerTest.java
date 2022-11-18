package com.example.demo.controller;

import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Box;
import com.example.demo.dto.ItemDto;
import com.example.demo.model.BodyRequest;
import com.example.demo.service.ItemService;
import com.example.demo.service.XmlService;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = XmlController.class)
@EnableJpaRepositories()
class XmlControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private Gson gson;

    @MockBean
    private ItemRepository itemRepository;
    @MockBean
    private BoxRepository boxRepository;
    @MockBean
    private ItemService itemService;
    @MockBean
    private XmlService xmlService;

    @Test
    void loadXml() throws Exception {

        List<Box> boxes = new ArrayList<>();
        Box box = new Box();
        box.setId(2);
        boxes.add(box);
        when(boxRepository.save(box)).thenReturn(box);

        List<ItemDto> items = new ArrayList<>();
        items.add(new ItemDto().setId(1).setContained(2).setColor("red"));
        items.add(new ItemDto().setId(2).setContained(2).setColor("blue"));

        when(itemRepository.getItemsByColorAndContained("color", 2)).thenReturn(new ArrayList<>());

        BodyRequest bodyRequest = new BodyRequest().setBox(2).setColor("red");
        when(itemService.getIdsOfItemsByColor(
                bodyRequest))
                .thenReturn(
                        items.stream()
                                .map(ItemDto::getId).collect(Collectors.toList())
                );

        mockMvc.perform(get("/load")
                .content(gson.toJson(bodyRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

}