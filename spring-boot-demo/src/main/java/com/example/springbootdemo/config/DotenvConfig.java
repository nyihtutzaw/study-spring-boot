package com.example.springbootdemo.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory(".")
                .ignoreIfMissing()
                .load();
    }

    @Bean
    public MapPropertySource dotenvPropertySource(Dotenv dotenv, ConfigurableEnvironment environment) {
        Map<String, Object> propertySource = new HashMap<>();
        
        // Add all properties from dotenv to our property source
        dotenv.entries().forEach(entry -> propertySource.put(entry.getKey(), entry.getValue()));
        
        MapPropertySource mapPropertySource = new MapPropertySource("dotenv", propertySource);
        environment.getPropertySources().addFirst(mapPropertySource);
        
        return mapPropertySource;
    }
}
