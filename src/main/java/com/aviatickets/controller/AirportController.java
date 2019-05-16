package com.aviatickets.controller;


import com.aviatickets.model.Airport;
import com.aviatickets.service.AirportService;

import java.util.List;

public class AirportController {
    AirportService airportService;

    public AirportController() {
        airportService = new AirportService();
    }

    public Airport save(Airport airport) {
        return airportService.save(airport);
    }

    public Airport getById(int id) {
        return airportService.getById(id);
    }

    public List<Airport> getAll() {
        return airportService.getAll();
    }

    public Airport update(Airport airport) {
        return airportService.update(airport);
    }

    public void delete(int id) {
        airportService.delete(id);
    }

    public void deleteByObject(Airport airport) {
        airportService.deleteByObject(airport);
    }
}
