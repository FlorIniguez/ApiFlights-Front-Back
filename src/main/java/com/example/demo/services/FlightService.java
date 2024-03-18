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
    FlightRepository vuelosRepository;

    public List<Flight> getAllFlights() {

        return vuelosRepository.findAll();
    }

    public Optional<Flight> searchFlightId(Long id) {

        return vuelosRepository.findById(id);
    }

    public void createFlight(Flight flight) {
        vuelosRepository.save(flight);
    }

    public Optional<Flight> updateFlight(Flight flight) {
        vuelosRepository.save(flight);
        return vuelosRepository.findById(flight.getId());
    }

    public void deleteFlight(Long id) {

        vuelosRepository.deleteById(id);
    }
    public List<Flight> saleFlights (double offerPrice){
        //traigo todos los vuelos
       List <Flight> flights = vuelosRepository.findAll();
        //Armo lista donde voy a guardar los vuelos en oferta
       List<Flight> saleFlights = new ArrayList<>();

       //Hago un forEach si el precio es menor o igual al
        //precioMax lo agrega a la lista
        for(Flight flight : flights) {
            if(flight.getPrice() <= offerPrice) {
                saleFlights.add(flight);
            }
        }
        //retorno la lista
        return saleFlights;
    }

}
