package com.example.demo.repository;

import com.example.demo.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FlightRepositoryTest {
    @Autowired
    private FlightRepository flightRepository;
    private Flight flight;

    @BeforeEach
    void setup() {
        flight = new Flight("COR", "EZE", "8.00", "11.00", 200.0, "DIARIA");
    }

    @Test
    void saveFlightTest() {
        // config previa en el setup que crea un vuelo
        //puedo crear el vuelo con el BeforeEach o crearlo en cada mètodo
        //lamo funcionalidad, le paso el vuelo creado en el setup
        Flight flightBD = flightRepository.save(flight);
        //verifico salida o comportamiento
        assertThat(flightBD).isNotNull();
        assertThat(flightBD.getId()).isGreaterThan(0);
    }
    @Test
    void flightFindByIdTest() {
        //configuracion previa
        flightRepository.save(flight);
        //Llamar la funcionalidad
        Flight flightBD = flightRepository.findById(flight.getId()).get();
        // verificar la salida o el comportamiento
        assertThat(flightBD).isNotNull();
    }
    @Test
    void flightFindAllTest() {
        //configuracion previa
        Flight flight2 = new Flight("MAD", "COR", "8.00", "11.00", 200.0, "Diaria");

        flightRepository.save(flight);
        flightRepository.save(flight2);

        //Llamar la funcionalidad
        List<Flight> flightList = flightRepository.findAll();

        //verificar la salida o el comportamiento
        assertThat(flightList).isNotNull();
        assertThat(flightList.size()).isEqualTo(2);
    }
    @Test
    void flightDeleteById() {
        //configuracion previa
        flightRepository.save(flight);
        //llamar la funcionalidad
        flightRepository.deleteById(flight.getId());

        // verificar la salidad o el comportamiento
        Optional<Flight> deletedFlight = flightRepository.findById(flight.getId());
        assertThat(deletedFlight).isEmpty();
    }
    @Test
    void flightUpdateTest() {
        flightRepository.save(flight);
        Flight flightBD = flightRepository.findById(flight.getId()).get();

        flightBD.setOrigin("BRA");
        flightBD.setDestiny("ARG");

        Flight flightUpdated = flightRepository.save(flightBD);

        assertThat(flightUpdated.getOrigin()).isEqualTo("BRA");
        assertThat(flightUpdated.getDestiny()).isEqualTo("ARG");
    }
}
