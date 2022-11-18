package com.example.demo.utils;

import com.example.demo.config.DataOfFile;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Objects.isNull;

@Component
public class UtilsHelper {

    public String getFilePath(DataOfFile dataOfFile) throws MalformedURLException {
        if (isNull(dataOfFile.getType())) {
            return "";
        }

        switch (dataOfFile.getType()) {
            case "file":
                return dataOfFile.getPath();
            case "classpath":
                URL resource = this.getClass().getClassLoader().getResource(dataOfFile.getPath());
                if (isNull(resource)) {
                    throw new IllegalArgumentException("Unknown file path");
                }
                return resource.getFile();
            case "url":
                URL url = new URL(dataOfFile.getPath());
                return url.getFile();
        }

        return "";
    }
}
