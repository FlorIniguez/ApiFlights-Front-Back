package com.example.demo.utils;

import com.example.demo.model.DolarCard;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {
    //recibe un vuelo, el precio dolar y devuelve el vuelo con el precio convertido el FlightDTO
    public FlightDto flightMapper(Flight flight, double price ){
        return new FlightDto(flight.getId(), flight.getOrigin(), flight.getDestiny(),flight.getDepartureTime(),
                flight.getArrivingTime(),flight.getPrice() * price, flight.getFrequency(), flight.getCompany());
    }

    public List<FlightDto> flightMapperDto(List <Flight> flights, double price){

       /* List<FlightDto> flightDtos = new ArrayList<>();
        //recorro la lista de vuelos
        for(Flight flight : flights) {
            // por cada vuelta a la lista le agrego un nuevo FlightDTO
            flightDtos.add(new FlightDto(flight.getId(), flight.getOrigin(), flight.getDestiny(),flight.getDepartureTime(),
                    flight.getArrivingTime(),flight.getPrice() * price, flight.getFrequency()));
        }
        return flightDtos; */

        //convierto la lista flights que viene por parametro en flujo
        //la mapeo para que por cada vuelo cree un nuevo flightDTO
        return flights.stream()
                .map(flight -> new FlightDto(flight.getId(), flight.getOrigin(), flight.getDestiny(),
                        flight.getDepartureTime(), flight.getArrivingTime(),
                        flight.getPrice() * price, flight.getFrequency(), flight.getCompany()))
                .collect(Collectors.toList());
    }

    public List<Flight> detectOffers(List<Flight> flights, double offerPrice) {
        return flights.stream()
                .filter(flight -> flight.getPrice() < offerPrice)
                .collect(Collectors.toList());
    }
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
