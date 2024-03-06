package com.example.demo.services;

import com.example.demo.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.FlightRepository;


import java.util.List;

@Service
public class FlightService {
    @Autowired
    FlightRepository vuelosRepository;

    public List<Flight> getAllFlights() {
        return vuelosRepository.findAll();
    }

    public Flight searchFlightId(Long id) {
        return vuelosRepository.findById(id).orElse(null);
    }

    public void createFlight(Flight flight) {
        vuelosRepository.save(flight);
    }

    public Flight updateFlight(Flight flight) {
        vuelosRepository.save(flight);
        return vuelosRepository.findById(flight.getId()).orElse(null);
    }

    public void deleteFlight(Long id) {
        vuelosRepository.deleteById(id);
    }

}
