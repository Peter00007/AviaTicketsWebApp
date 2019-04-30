package com.aviatickets.controller;


import com.aviatickets.model.Airport;
import com.aviatickets.repository.AirportRepository;
import com.aviatickets.repository.jdbc.JdbcAirportRepositoryImpl;

import java.util.List;

public class AirportController {
    AirportRepository airportRepository;

    public AirportController() {
        airportRepository = new JdbcAirportRepositoryImpl();
    }

    public Airport save(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport getById(int id) {
        return airportRepository.getById(id);
    }

    public List<Airport> getAll() {
        return airportRepository.getAll();
    }

    public Airport update(Airport airport) {
        return airportRepository.update(airport);
    }

    public void delete(int id) {
        airportRepository.delete(id);
    }

    public void deleteByObject(Airport airport) {
        airportRepository.deleteByObject(airport);
    }
}
