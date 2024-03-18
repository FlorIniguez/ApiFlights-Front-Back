package com.example.demo.utils;

import com.example.demo.model.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightUtils {
    public List<Flight> detectOffers(List<Flight> flightList, double offerPrice){
        List<Flight> offers = new ArrayList<>();
        return offers;
    }
}
