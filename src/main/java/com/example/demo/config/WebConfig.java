package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.lang.NonNull;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("https://prompt-engineering-982d3a6ceefe.herokuapp.com")
                .allowedMethods("GET", "POST")
                .allowedHeaders("*");
    }

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        // Serve all static files from classpath:/static/ (where React gets built into)
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
