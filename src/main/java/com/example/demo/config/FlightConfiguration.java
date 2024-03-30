package com.example.demo.config;

import com.example.demo.model.DolarCard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//las @configuration se ejecutan antes que lo demàs
@Configuration
public class FlightConfiguration {
    //instancio un componente (unidad basica bean)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    //me devuelve el dolar como objeto
    public DolarCard fetchDolar() {
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares/tarjeta";
        //solicitud a esa ruta y la clase que voy a usar para es consulta
        return restTemplate.getForObject(apiUrl, DolarCard.class);

    }
    public DolarCard[] fetchAllDolars(){
        RestTemplate restTemplate = restTemplate();
        String apiUrl = "https://dolarapi.com/v1/dolares";
        return restTemplate.getForEntity(apiUrl, DolarCard[].class).getBody();

    }

}
