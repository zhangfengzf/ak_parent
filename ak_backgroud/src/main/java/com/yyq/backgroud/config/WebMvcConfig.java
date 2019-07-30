package com.yyq.backgroud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedHeaders("Content-Type","X-Requested-With","accept,Origin","Access-Control-Request-Method","Access-Control-Request-Headers","token")
                .allowedOrigins("*")
                .allowedMethods("OPTION","HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
                .maxAge(3600)
                .allowCredentials(true);
    }
}