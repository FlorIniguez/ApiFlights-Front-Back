package com.example.demo.utils;

import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightUtils {


    public FlightDto flightMapper(Flight flight, double price ){
        return new FlightDto(flight.getId(), flight.getOrigin(), flight.getDestiny(),flight.getDepartureTime(),
                flight.getArrivingTime(),flight.getPrice() * price, flight.getFrequency());
    }

    public List<Flight> detectOffers(List<Flight> flights, double offerPrice) {
        return flights.stream()
                .filter(flight -> flight.getPrice() < offerPrice)
                .collect(Collectors.toList());
    }
}
