package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FlightDto {
    //con Flight y Dolar armo este DTO y construyo el vuelo que quiero mostrar
    private Long id;
    private String origin;
    private String destiny;
    private String departureTime;
    private String arrivingTime;
    private double convertedPrice;
    private String frequency;
    private Company company;

}
