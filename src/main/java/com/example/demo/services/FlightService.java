package com.example.demo.services;

import com.example.demo.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.FlightRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    FlightRepository flightRepository;

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
        List<Flight> offerFlights = new ArrayList<>();


        for(Flight flight : flights){
            if(flight.getPrice() < offerPrice){
                offerFlights.add(flight);
            }
        }

        return offerFlights;
    }
    public  List<Flight> getByOriginAndDestiny(String origin, String destiny){
        return flightRepository.findByOriginAndDestiny(origin, destiny);
    }
    public  List<Flight> getByOrigin(String origin){
        return flightRepository.findByOrigin(origin);
    }

    }

//Assertions.assertEquals(2, offers.size());