package com.example.demo.controller;

import com.example.demo.config.Arguments;
import com.example.demo.service.XmlService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class CommonController {

    private final XmlService xmlService;

    @GetMapping("/test")
    public void print() throws IOException, SAXException, ParserConfigurationException {
        Arguments fromXml = xmlService.loadDataFromXml();
        xmlService.loadXmlToDB(fromXml);
    }

}
