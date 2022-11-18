package com.example.demo.service;

import com.example.demo.dao.BoxRepository;
import com.example.demo.dao.ItemRepository;
import com.example.demo.domain.Box;
import com.example.demo.domain.Item;
import com.example.demo.dto.BoxDto;
import com.example.demo.dto.ItemDto;
import com.example.demo.mapper.MapStructMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParseService {

    private final ItemRepository itemRepository;
    private final BoxRepository boxRepository;
    private final MapStructMapper mapStructMapper;

    public void parse(Document document) {
        parseBoxes(document.getDocumentElement());
        parseItems(document.getDocumentElement());
    }

    private void parseItems(final Element element) {
        final Integer[] boxId = new Integer[1];

        NodeList children = element.getChildNodes();
        List<Item> items = new ArrayList<>();

        for (int i = 0; i < children.getLength(); i++) {
            Node node = children.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (node.getNodeName().equals("Item")) {
                    Node nodeId = node.getParentNode().getAttributes().getNamedItem("id");
                    boxId[0] = Optional.ofNullable(nodeId).map(Node::getNodeValue).map(Integer::new).orElse(null);

                    String itemId = Optional.ofNullable(node.getAttributes().getNamedItem("id").getTextContent())
                            .orElse(null);

                    String color = Optional.ofNullable(node.getAttributes().getNamedItem("color"))
                            .map(Node::getNodeValue)
                            .orElse(null);

                    ItemDto itemDto = new ItemDto()
                            .setId(Integer.parseInt(itemId))
                            .setContained(boxId[0])
                            .setColor(color);
                    Item entity = mapStructMapper.dtoToItem(itemDto);
                    items.add(entity);
                }
                parseItems((Element) node);
            }
        }

        itemRepository.saveAll(items);
    }

    private void parseBoxes(final Element element) {
        Integer boxId;
        NodeList children = element.getChildNodes();

        List<Box> boxes = new ArrayList<>();
        for (int i = 0; i < children.getLength(); i++) {

            Node node = children.item(i);
            if (node.getNodeName().equals("Box")) {
                String id = node.getAttributes().getNamedItem("id").getNodeValue();

                Node nodeId = node.getParentNode().getAttributes().getNamedItem("id");
                boxId = Optional.ofNullable(nodeId).map(Node::getNodeValue).map(Integer::new).orElse(null);

                BoxDto boxDto = new BoxDto()
                        .setId(Integer.parseInt(id))
                        .setContained(boxId);
                Box entity = mapStructMapper.dtoToBox(boxDto);
                boxes.add(entity);
                parseBoxes((Element) node);

            }
        }
        boxRepository.saveAll(boxes);
    }
}
