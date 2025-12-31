package com.gsoc.gsocwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GsocwatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(GsocwatcherApplication.class, args);
    }
}