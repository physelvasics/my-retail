package com.myretail.product.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfig {

    @Bean
    RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    ObjectMapper getObjectMapper(){

        ObjectMapper objectMapper = new ObjectMapper();
        return new ObjectMapper();
    }
}
