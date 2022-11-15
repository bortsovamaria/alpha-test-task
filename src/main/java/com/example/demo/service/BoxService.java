package com.example.demo.service;

import com.example.demo.dao.BoxRepository;
import com.example.demo.domain.Box;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoxService {

    private final BoxRepository boxRepository;

    public Optional<Box> getItem(long id) {
        return boxRepository.findById(id);
    }

    public Box createItem(Box box) {
        return boxRepository.save(box);
    }

}
