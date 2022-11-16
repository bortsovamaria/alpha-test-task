package com.example.demo.service;

import com.example.demo.config.Arguments;
import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Item;
import com.example.demo.dto.ItemDto;
import com.example.demo.mapper.MapStructMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class XmlService {

    private final ItemRepository itemRepository;
    private final BoxRepository boxRepository;
    private final Arguments arguments;
    private final MapStructMapper mapStructMapper;
    // non thread safe
    String idBox = "";

    public Arguments loadDataFromXml() throws ParserConfigurationException, IOException, SAXException {
        String filePath = "/home/marialutteur/code/alfa-test/src/main/resources/example.xml";
        File xmlFile = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);

        document.getDocumentElement().normalize();

        final List<String> l = new ArrayList<>();
        return parse(document, l, document.getDocumentElement());
    }

    private Arguments parse(final Document document, final List<String> list, final Element element) {

        NodeList children = element.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {

            Node node = children.item(i);
            if (node.getNodeName().equals("Box")) {
                idBox = node.getAttributes().getNamedItem("id").getNodeValue();
            }
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("Item")) {
                    String id = node.getAttributes().getNamedItem("id").getTextContent();
                    Optional<Node> optColor = Optional.ofNullable(node.getAttributes().getNamedItem("color"));
                    String color = "";
                    if (optColor.isPresent()) {
                        color = optColor.get().getNodeValue();
                    }
                    arguments.getMap().put(id, new AbstractMap.SimpleEntry<>(color, idBox));
                }
                list.add(node.getNodeName() + " " + node.getAttributes().getNamedItem("id"));
                parse(document, list, (Element) node);
            }
        }
        return arguments;
    }

    public void loadXmlToDB(Arguments arguments) {
        Map<String, Map.Entry<String, String>> map = arguments.getMap();
        for (int i = 1; i < map.size(); i++) {
            Map.Entry<String, String> entry = map.get(String.valueOf(i));
            String color = entry.getKey();
            String idBox = entry.getValue();

            ItemDto itemDto = new ItemDto(i, Integer.parseInt(idBox), color);
            Item entity = mapStructMapper.dtoToItem(itemDto);
            itemRepository.save(entity);
        }
    }

}
