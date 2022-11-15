package com.example.demo.dao;

import com.example.demo.domain.Box;
import org.springframework.data.repository.CrudRepository;

public interface BoxRepository extends CrudRepository<Box, Long> {
}
