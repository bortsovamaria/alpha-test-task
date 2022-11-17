package com.example.demo.dao;

import com.example.demo.domain.Box;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoxRepository extends CrudRepository<Box, Long> {
    List<Box> getBoxesByContained(Integer id);
    List<Box> getBoxesById(Integer id);
}
