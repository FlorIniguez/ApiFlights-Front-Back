package com.example.demo.controller;

import com.example.demo.model.Flight;
import com.example.demo.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Flight findFlightById(@PathVariable Long id) {
        return flightService.searchFlightId(id);
    }
    @PostMapping("/add")
    public void createFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
    }
    @PutMapping("/update")
    public Flight updateFlight(@RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }


}
