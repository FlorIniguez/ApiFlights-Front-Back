package com.example.demo.services;

import com.example.demo.config.FlightConfiguration;
import com.example.demo.model.DolarCard;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
import com.example.demo.utils.FlightUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.FlightRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    FlightConfiguration flightConfiguration;
    @Autowired
    FlightUtils flightUtils;

    //como puse component en flightUtils,aca con Autowired lo inyecto

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> searchFlightId(Long id) {
        return flightRepository.findById(id);
    }

    public void createFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public Optional<Flight> updateFlight(Flight flight) {
        flightRepository.save(flight);
        return flightRepository.findById(flight.getId());
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    public List<Flight> saleFlights(double offerPrice) {
        //traigo todos los vuelos
        List<Flight> flights = flightRepository.findAll();
      return flightUtils.detectOffers(flights,offerPrice);
    }
    public  List<Flight> getByOriginAndDestiny(String origin, String destiny){
        return flightRepository.findByOriginAndDestiny(origin, destiny);
    }
    public  List<Flight> getByOrigin(String origin){

        return flightRepository.findByOrigin(origin);
    }

    public double getDolar() {
        //traigo el dolar y hace el promedio
       // return flightConfiguration.fetchDolar().getPromedio();
        DolarCard dolar = flightConfiguration.fetchDolar();
        return dolar.getPromedio();
    }
    public List<FlightDto> findAllDto(){
       List<Flight> flightList = flightRepository.findAll();
        return flightList.stream()
                .map(flight -> flightUtils.flightMapper(flight, getDolar()))
                .collect(Collectors.toList());
    }
}

//Assertions.assertEquals(2, offers.size());