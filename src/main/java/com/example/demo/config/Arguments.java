package com.example.demo.config;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Data
public class Arguments {
    private final Map<String, Map.Entry<String, String>> map = new LinkedHashMap<>();
    private final Table<String, String, String> table = HashBasedTable.create();
}
