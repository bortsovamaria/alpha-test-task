package com.example.demo.service;

import com.example.demo.config.DataOfFile;
import com.example.demo.utils.UtilsHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class XmlService {

    private final ParseService parseService;
    private final DataOfFile dataOfFile;
    private final UtilsHelper utilsHelper;

    public void loadDataFromXml() throws ParserConfigurationException, IOException, SAXException {

        String filePath = utilsHelper.getFilePath(dataOfFile);
        if (isNull(filePath)) {
            throw new IllegalArgumentException("Unknown file path");
        }

        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement().normalize();

        parseService.parse(document);
    }

}
