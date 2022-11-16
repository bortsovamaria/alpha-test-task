package com.example.demo.dao;

import com.example.demo.domain.Box;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BoxRepository extends CrudRepository<Box, Long> {
    List<Box> getBoxesByContained(Integer id);
}
