package com.aviatickets.controller;

import com.aviatickets.model.Flight;
import com.aviatickets.repository.FlightRepository;

import java.util.List;

public class FlightController {
    FlightRepository flightRepository;

    public FlightController() {
        flightRepository = new FlightRepository();
    }

    public Flight insert(int aircraftId, String flightDate) {
        return flightRepository.insert(aircraftId, flightDate);
    }

    public List<Flight> read() {
        return flightRepository.read();
    }

    public Flight update(int id, int aircraftId, String flightDate) {
        return flightRepository.update(id, aircraftId, flightDate);
    }

    public void delete(int id) {
        flightRepository.delete(id);
    }

    public List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport) {
        return flightRepository.searchByFlight(firstDate, secondDate, depAirport, arrAirport);
    }
}

