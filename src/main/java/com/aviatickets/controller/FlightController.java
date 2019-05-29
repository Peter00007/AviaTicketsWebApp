package com.aviatickets.controller;

import com.aviatickets.model.Flight;
import com.aviatickets.service.FlightService;

import java.util.List;

public class FlightController {
    FlightService flightService;

    public FlightController() {
        flightService = new FlightService();
    }

    public Flight save(Flight flight) {
        return flightService.save(flight);
    }

    public Flight getById(int id) {
        return flightService.getById(id);
    }

    public List<Flight> getAll() {
        return flightService.getAll();
    }

    public Flight update(Flight flight) {
        return flightService.update(flight);
    }

    public void delete(int id) {
        flightService.delete(id);
    }

    public void deleteByObject(Flight flight) {
        flightService.deleteByObject(flight);
    }

    public List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport) {
        return flightService.searchByFlight(firstDate, secondDate, depAirport, arrAirport);
    }
}

