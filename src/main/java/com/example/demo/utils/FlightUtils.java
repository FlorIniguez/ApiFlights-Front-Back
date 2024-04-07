package com.example.demo.utils;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {
    //recibe un vuelo, el precio dolar y devuelve el vuelo con el precio convertido el FlightDTO
    public FlightDto flightMapper(Flight flight, double price ){
        return new FlightDto(flight.getId(), flight.getOrigin(), flight.getDestiny(),flight.getDepartureTime(),
                flight.getArrivingTime(),flight.getPrice() * price, flight.getFrequency());
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
                        flight.getPrice() * price, flight.getFrequency()))
                .collect(Collectors.toList());
    }


    public List<Flight> detectOffers(List<Flight> flights, double offerPrice) {
        return flights.stream()
                .filter(flight -> flight.getPrice() < offerPrice)
                .collect(Collectors.toList());
    }
}
