package com.aviatickets.controller;

import com.aviatickets.model.Flight;
import com.aviatickets.repository.FlightRepository;
import com.aviatickets.repository.jdbc.JdbcFlightRepositoryImpl;

import java.util.List;

public class FlightController {
    FlightRepository flightRepository;

    public FlightController() {
        flightRepository = new JdbcFlightRepositoryImpl();
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getById(int id) {
        return flightRepository.getById(id);
    }

    public List<Flight> getAll() {
        return flightRepository.getAll();
    }

    public Flight update(Flight flight) {
        return flightRepository.update(flight);
    }

    public void delete(int id) {
        flightRepository.delete(id);
    }

    public void deleteByObject(Flight flight) {
        flightRepository.deleteByObject(flight);
    }

    public List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport) {
        return flightRepository.searchByFlight(firstDate, secondDate, depAirport, arrAirport);
    }

    public void deleteFlightRoute(int idFlight, int idRoute) {
        flightRepository.deleteFlightRoute(idFlight, idRoute);
    }

    public void addFlightRoute(int idFlight, int idRoute) {
        flightRepository.addFlightRoute(idFlight, idRoute);
    }
}

