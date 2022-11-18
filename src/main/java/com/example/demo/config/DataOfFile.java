package com.example.demo.config;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

@Component
@Data
@Accessors(chain = true)
public class DataOfFile {
    private String type;
    private String path;
}
