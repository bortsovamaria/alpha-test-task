package com.example.demo.controller;

import com.example.demo.model.BodyRequest;
import com.example.demo.service.ItemService;
import com.example.demo.service.XmlService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class CommonController {

    private final XmlService xmlService;
    private final ItemService itemService;

    @GetMapping(value = "/test", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Integer> getItemsIds(@RequestBody BodyRequest bodyRequest) throws IOException, SAXException, ParserConfigurationException {
//        xmlService.loadDataFromXml();
        return itemService.getIdsOfItemsByColor(bodyRequest);
    }

    @GetMapping(value = "/load", produces = MediaType.APPLICATION_XML_VALUE)
    public void loadXml() throws IOException, SAXException, ParserConfigurationException {
        xmlService.loadDataFromXml();
    }

}
