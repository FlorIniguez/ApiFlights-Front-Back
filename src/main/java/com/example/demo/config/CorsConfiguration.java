package com.example.demo.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CorsConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //que direcciones
        registry.addMapping("/**")

                .allowedOrigins("/*")
                //que mètodos http
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders();
    }
}
