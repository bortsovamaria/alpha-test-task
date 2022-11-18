package com.example.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadArgumentsFromCommandLine implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(LoadArgumentsFromCommandLine.class);
    private final DataOfFile dataOfFile;

    public LoadArgumentsFromCommandLine(DataOfFile dataOfFile) {
        this.dataOfFile = dataOfFile;
    }

    @Override
    public void run(ApplicationArguments args) {
        logger.info("------------------------------------");

        String s = args.getOptionValues("type.path").get(0);
        String type = s.substring(0, s.indexOf(":"));
        String path = s.substring(s.indexOf(":") + 1);
        dataOfFile.setType(type);
        dataOfFile.setPath(path);
        logger.info(String.valueOf(dataOfFile));

        logger.info("------------------------------------");
    }
}
