package com.example.demo.service;

import com.example.demo.config.DataOfFile;
import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import com.example.demo.dto.BoxDto;
import com.example.demo.dto.ItemDto;
import com.example.demo.mapper.MapStructMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.internal.util.IOUtils;
import org.slf4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static java.util.Objects.isNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class XmlService {

    private final ItemRepository itemRepository;
    private final BoxRepository boxRepository;
    private final DataOfFile dataOfFile;
    private final MapStructMapper mapStructMapper;

    // non thread safe
    Optional<Node> boxIdOpt = Optional.empty();
    Integer boxId = null;

    public void loadDataFromXml() throws ParserConfigurationException, IOException, SAXException {

        Set<Map.Entry<String, String>> entries = dataOfFile.getType().entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        String path = null;
        String type = null;

        while (iterator.hasNext()) {
            Map.Entry<String, String> m = iterator.next();
            if (m.getKey().equals("path")) {
                path = m.getValue();
            }
            if (m.getKey().equals("type")) {
                type = m.getValue();
            }

        }
        String filePath = getFilePath(path, type);
        if (isNull(filePath)) {
            throw new IllegalArgumentException("Unknown file path");
        }

        File xmlFile = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);

        document.getDocumentElement().normalize();

        parse(document);
    }

    private String getFilePath(String path, String type) throws MalformedURLException {
        if (isNull(type)) {
            return "";
        }
        String filePath = null;

        switch (type) {
            case "file":
                filePath = path;
                break;
            case "classpath":
                URL resource = this.getClass().getClassLoader().getResource(path);
                if (isNull(resource)) {
                    throw new IllegalArgumentException("Unknown file path");
                }

                filePath = resource.getFile();
                break;
            case "url":
                URL url = new URL(path);
                filePath = url.getFile();
                break;
        }
        return filePath;
    }

    private void parse(Document document) {
        parseBoxes(document.getDocumentElement());
        parseItems(document.getDocumentElement());
    }

    private void parseItems(final Element element) {

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {

            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("Item")) {
                    Node nodeId = node.getParentNode().getAttributes().getNamedItem("id");
                    boxIdOpt = Optional.ofNullable(nodeId);
                    boxIdOpt.ifPresentOrElse(s ->
                            boxId = Integer.parseInt(s.getNodeValue()), () -> boxId = null);

                    String id = node.getAttributes().getNamedItem("id").getTextContent();
                    Optional<Node> optColor = Optional.ofNullable(node.getAttributes().getNamedItem("color"));
                    String color = "";
                    if (optColor.isPresent()) {
                        color = optColor.get().getNodeValue();
                    }

                    ItemDto itemDto =
                            new ItemDto()
                                    .setId(Integer.parseInt(id))
                                    .setContained(boxId)
                                    .setColor(color);
                    Item entity = mapStructMapper.dtoToItem(itemDto);
                    itemRepository.save(entity);
                }
                parseItems((Element) node);
            }
        }
    }

    private void parseBoxes(final Element element) {
        NodeList children = element.getChildNodes();

        for (int i = 0; i < children.getLength(); i++) {

            Node node = children.item(i);
            if (node.getNodeName().equals("Box")) {
                String id = node.getAttributes().getNamedItem("id").getNodeValue();
                Node nodeId = node.getParentNode().getAttributes().getNamedItem("id");
                boxIdOpt = Optional.ofNullable(nodeId);
                boxIdOpt.ifPresent(s ->
                        boxId = Integer.parseInt(s.getNodeValue()));
                BoxDto boxDto = new BoxDto()
                        .setId(Integer.parseInt(id))
                        .setContained(boxId);
                Box entity = mapStructMapper.dtoToBox(boxDto);
                boxRepository.save(entity);

                parseBoxes((Element) node);

            }
        }
    }

}
