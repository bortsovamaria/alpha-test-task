//package com.example.demo.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class LoadArgumentsFromCommandLine implements ApplicationRunner {
//
//    private static final Logger logger = LoggerFactory.getLogger(LoadArgumentsFromCommandLine.class);
//    private final Arguments arguments;
//
//    public LoadArgumentsFromCommandLine(Arguments arguments) {
//        this.arguments = arguments;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        logger.info("------------------------------------");
//
//        String s = args.getOptionValues("type.path").get(0);
//        String[] split = s.split(":");
//        arguments.getMap().put("type", split[0]);
//        arguments.getMap().put("path", split[1]);
//        logger.info(String.valueOf(arguments));
//
//        logger.info("------------------------------------");
//    }
//}
