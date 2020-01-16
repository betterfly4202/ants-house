package com.ants.batch.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by betterfly
 * Date : 2019.07.10
 */

@Configuration
public class WebConfiguration {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}