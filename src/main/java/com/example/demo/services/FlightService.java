package com.example.demo.services;

import com.example.demo.config.FlightConfiguration;
import com.example.demo.model.Company;
import com.example.demo.model.DolarCard;
import com.example.demo.model.Flight;
import com.example.demo.model.FlightDto;
import com.example.demo.repository.CompanyRepository;
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
    CompanyRepository companyRepository;
    @Autowired
    FlightUtils flightUtils;

    //como puse component en flightUtils,aca con Autowired lo inyecto

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public List<FlightDto> findAllDto() {
        List<Flight> flightList = flightRepository.findAll();
        //pide a config el precio del dolar
        double dolarPrice = getDolar();
        //en utils digo que el mapper recibe una lista y el precio dolar
        //traigo todos los vuelos del repository y el valor del dolar
        return flightUtils.flightMapperDto(flightList, dolarPrice);
    }

    public Optional<Flight> searchFlightId(Long id) {

        return flightRepository.findById(id);
    }

    public List<Flight> saleFlights(Integer offerPrice) {
        //traigo todos los vuelos
        List<Flight> flights = flightRepository.findAll();
        //le paso por la lista de flights del findAll
        //y el double del offerPrice que entra por parametro
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
        DolarCard dolar = flightUtils.fetchDolar();
        return dolar.getPromedio();
    }

    public void createFlight(Flight flight, Long companyId) {

        // Buscar la compañía por su ID
        Optional<Company> searchCompany = companyRepository.findById(companyId);

        //si encuentra la compania con ese id,  "trae la compania"
        if (searchCompany.isPresent()) {
            Company company = searchCompany.get();

            // Setea la compania al vuelo
            flight.setCompany(company);

            // Guardar el vuelo en la base de datos
            flightRepository.save(flight);
    }
}
    public Optional<Flight> updateFlight(Flight flight) {
        flightRepository.save(flight);
        return flightRepository.findById(flight.getId());
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}

