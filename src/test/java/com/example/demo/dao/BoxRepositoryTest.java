package com.example.demo.dao;

import com.example.demo.domain.Box;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BoxRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private BoxRepository boxRepository;

    @Test
    void contextLoads() {
        assertNotNull(entityManager);
        assertNotNull(dataSource);
    }

    @Test
    void getBoxesContained() {
        Box box = new Box();
        box.setId(1);
        box.setContained(2);
        boxRepository.save(box);
        List<Integer> boxes = boxRepository.getBoxes(1);
        assertTrue(boxes.contains(box.getId()));
    }

    @Test
    void getBoxesById() {
        Box box = new Box();
        box.setId(1);
        box.setContained(2);
        boxRepository.save(box);
        List<Integer> boxes = boxRepository.getBoxes(1);
        assertTrue(boxes.contains(box.getId()));
    }

    @Test
    void getBoxesContainedFail() {
        Box box = new Box();
        box.setId(1);
        box.setContained(2);
        boxRepository.save(box);
        List<Integer> boxesIds = boxRepository.getBoxes(3);
        assertFalse(boxesIds.contains(box.getId()));
    }

    @Test
    void getBoxesByIdFail() {
        Box box = new Box();
        box.setId(1);
        box.setContained(2);
        boxRepository.save(box);
        List<Integer> boxesIds = boxRepository.getBoxes(3);
        assertFalse(boxesIds.contains(box.getId()));
    }

}