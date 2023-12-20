package com.lovelycat.shinkaibackend.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {

        return jacksonObjectMapperBuilder ->
                jacksonObjectMapperBuilder.simpleDateFormat(DATETIME_FORMAT);
    }
}
