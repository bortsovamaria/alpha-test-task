package com.example.demo.controller;

import com.example.demo.service.XmlService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class XmlController {

    private final XmlService xmlService;

    @GetMapping(value = "/load", produces = MediaType.APPLICATION_XML_VALUE)
    public void loadXml() throws IOException, SAXException, ParserConfigurationException {
        xmlService.loadDataFromXml();
    }

}
