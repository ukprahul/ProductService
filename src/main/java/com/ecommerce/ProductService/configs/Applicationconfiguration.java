package com.ecommerce.ProductService.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Applicationconfiguration {
    @Bean
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
