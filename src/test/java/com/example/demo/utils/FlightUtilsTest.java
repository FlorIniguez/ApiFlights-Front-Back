package com.example.demo.utils;

import com.example.demo.model.DolarCard;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FlightUtilsTest {
  //  private Flight flight;
    @Autowired
    FlightUtils flightUtils;

    @Test
    void flightMapperTest() {
        //verificar si el mapeo de un Flight a un  FlightDto se realiza ok
        //variables necesarias para la prueba
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

        //la lista dto llama a flightUtils el mapper, le paso el contexto de prueba
        flightDtoList = flightUtils.flightMapperDto(flightList, dolarPrice);

        FlightDto flightDto = flightDtoList.get(0);
        //espero que el id sea 1 y traigo el id del flight
        assertEquals(1, flightDto.getId());
        assertEquals(flight1.getPrice() * dolarPrice ,flightDto.getConvertedPrice());

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
    @Test
    void fetchDolarTest(){
        //genero contexto
        DolarCard dummyDolar = new DolarCard();
        dummyDolar.setMoneda("USD");
        dummyDolar.setCasa("tarjeta");
        dummyDolar.setNombre("Tarjeta");
        dummyDolar.setCompra(1200.0);
        dummyDolar.setVenta(1000.0);


        //crea un mock de flightUtils, (mock. clase que quiero, me crea simulación)
        FlightUtils mockedFlightUtils =  mock(FlightUtils.class);
        //Se configura el comportamiento del mock utilizando el método when().thenReturn(). Esto establece que cuando se llame
        // al método fetchDolar() en el mock,devolverá el objeto dummyDolar creado anteriormente.
        when(mockedFlightUtils.fetchDolar()).thenReturn(dummyDolar);
        //Ejecuto el metodo bajo prueba, se llama a fetchDolar en el obj mockedFlightUtils
        DolarCard dolarPrueba = mockedFlightUtils.fetchDolar();
        //verificaciones, se veruduca el valor promedio con JUnit
        assertEquals(1100,dolarPrueba.getPromedio());

    }

}