package com.medical.appointmentservice.configuration;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate()
    {
        return new RestTemplate();
    }

    @Bean
    @LoadBalanced
    RestClient.Builder restClientBuilder()
    {
        return RestClient.builder();
    }

    @Bean
    RestClient restClient(RestClient.Builder builder)
    {
        return builder.build();
    }
}
