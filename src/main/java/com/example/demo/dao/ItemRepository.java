package com.example.demo.dao;

import com.example.demo.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> getItemsByColorAndContained(String color, Integer box);
}
