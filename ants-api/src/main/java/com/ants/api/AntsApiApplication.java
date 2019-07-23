package com.ants.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by betterfly
 * Date : 2019.09.17
 */
@SpringBootApplication(scanBasePackages = "com.ants")
public class AntsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AntsApiApplication.class, args);
    }
}
