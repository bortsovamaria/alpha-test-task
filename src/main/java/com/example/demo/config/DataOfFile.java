package com.example.demo.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Data
@Accessors(chain = true)
public class DataOfFile {
    private Map<String, String> type = new HashMap<>();
}
