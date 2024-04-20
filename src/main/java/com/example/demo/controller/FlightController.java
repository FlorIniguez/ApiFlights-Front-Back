package com.example.demo.controller;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
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

    @CrossOrigin
    @GetMapping("")
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/dolar-flights")
    public List<FlightDto> flightsDolar() {
        return flightService.findAllDto();
    }

    @GetMapping("/{id}")
    public Optional<Flight> findFlightById(@PathVariable Long id) {
        return flightService.searchFlightId(id);
    }

    @GetMapping("/sale")
    public List<Flight> saleFlights(@RequestParam Integer offerPrice) {
        return flightService.saleFlights(offerPrice);
    }

    @GetMapping("/locations")
    public List<Flight> getFlightsByLocations(@RequestParam String origin, @RequestParam String destiny) {
        return flightService.getByOriginAndDestiny(origin, destiny);
    }

    @GetMapping("/origin")
    public List<Flight> getFlightsByLocations(@RequestParam String origin) {
        return flightService.getByOrigin(origin);
    }

    @GetMapping("/dolarprice")
    public double getDolar() {
        return flightService.getDolar();
    }

    @PostMapping("/add/{companyId}")
    public Optional<Flight> createFlight(@PathVariable Long companyId, @RequestBody Flight flight) {
        return flightService.createFlight(companyId, flight);
    }

    public Optional<Flight> updateFlight(@RequestBody Flight flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }

}
