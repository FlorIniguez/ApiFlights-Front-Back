package com.example.demo.controller;

import com.example.demo.model.Flight;
import com.example.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping("")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }
    @GetMapping("/{id}")
    public Optional <Flight> findFlightById(@PathVariable Long id) {
        return flightService.searchFlightId(id);
    }
    @PostMapping("/add")
    public void createFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
    }
    @PutMapping("/update")
    public Optional<Flight> updateFlight(@RequestBody Flight flight) {
       return flightService.updateFlight(flight);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
    @GetMapping ("/sale")
    public List<Flight> saleFlights(@RequestParam double offerPrice){
        return flightService.saleFlights(offerPrice);
    }


}
