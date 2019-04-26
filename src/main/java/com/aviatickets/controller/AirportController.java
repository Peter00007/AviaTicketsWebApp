package com.aviatickets.controller;


import com.aviatickets.model.Airport;
import com.aviatickets.repository.AirportRepository;

import java.util.List;

public class AirportController {
    AirportRepository airportRepository;

    public AirportController() {
        airportRepository = new AirportRepository();
    }

    public Airport insert(String name) {
        return airportRepository.insert(name);
    }

    public List<Airport> read() {
        return airportRepository.read();
    }

    public Airport update(int id, String name) {
        return airportRepository.update(id, name);
    }

    public void delete(int id) {
        airportRepository.delete(id);
    }
}
