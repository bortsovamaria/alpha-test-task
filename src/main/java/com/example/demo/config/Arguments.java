package com.example.demo.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Data
public class Arguments {
    private final Map<String, Map.Entry<String, Integer>> data = new LinkedHashMap<>();
    private final Map<String, String> type = new HashMap<>();
}
