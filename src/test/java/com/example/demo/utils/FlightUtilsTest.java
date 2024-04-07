package com.example.demo.utils;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
import com.example.demo.repository.FlightRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightUtilsTest {
  //  private Flight flight;
    @Autowired
    FlightUtils flightUtils;

  /*  @BeforeEach
    public void setUp() {
        List<Flight> flightList;
        List<FlightDto> flightDtoList;
        double dolarPrice = 1015;
    } */

    @Test
    void flightMapperTest() {
        List<Flight> flightList = new ArrayList<>();
        double dolarPrice = 1015;
        List<FlightDto> flightDtoList;

        //creo y seteo un vuelo
        Flight flight1 = new Flight();
        flight1.setId(1L);
        flight1.setOrigin("COR");
        flight1.setDestiny("EZE");
        flight1.setArrivingTime("SomeDate");
        flight1.setArrivingTime("AnotherTime");
        flight1.setPrice(100);
        flight1.setFrequency("Weekly");

        //agrego el vuelo a la lista
        flightList.add(flight1);

        //la lista dto llama a flightUtils el mapper
        flightDtoList = flightUtils.flightMapperDto(flightList, dolarPrice);

        FlightDto flightDto = flightDtoList.get(0);
        //espero que el id sea 1 y traigo el id del flight
        assertEquals(1, flightDto.getId());
        assertEquals(101500 ,flightDto.getConvertedPrice());

    }

    @Test
    void detectOffersTest() {
        //creo lista de vuelos
        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight("AEP", "COL", "09.00", "11.00", 150.00, "Semanal"));
        flightList.add(new Flight("MAD", "COR", "8.00", "11.00", 200.0, "Diaria"));
        flightList.add(new Flight("EZE", "SAL", "8.00", "11.00", 180.0, "Diaria"));

        //defino precio oferta
        double offerPrice = 190.0;

        //lamo al metodo que detecta las ofertas
        List<Flight> offers = flightUtils.detectOffers(flightList, offerPrice);

        // Verificar si el tamaño de la lista de ofertas es el esperado
        assertEquals(2, offers.size());

    }

}