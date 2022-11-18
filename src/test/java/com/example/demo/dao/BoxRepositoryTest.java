package com.example.demo.dao;

import com.example.demo.domain.Box;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class BoxRepositoryTest {

    @Autowired
    private BoxRepository boxRepository;

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