package com.ants.messaging;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by betterfly
 * Date : 2019.09.17
 */
@SpringBootApplication(scanBasePackages = "com.ants")
public class MessagingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args);
    }
}
