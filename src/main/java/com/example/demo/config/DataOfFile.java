package com.example.demo.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
public class DataOfFile {
    private final Map<String, String> type = new HashMap<>();
}
