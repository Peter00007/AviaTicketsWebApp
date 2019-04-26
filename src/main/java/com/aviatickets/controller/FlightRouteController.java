package com.aviatickets.controller;

import com.aviatickets.model.FlightRoute;
import com.aviatickets.repository.FlightRouteRepository;

import java.util.List;

public class FlightRouteController {
    FlightRouteRepository flightRouteRepository;

    public FlightRouteController() {
        flightRouteRepository = new FlightRouteRepository();
    }

    public FlightRoute insert(int flightId, int routeId) {
        return flightRouteRepository.insert(flightId, routeId);
    }

    public List<FlightRoute> read() {
        return flightRouteRepository.read();
    }

    public FlightRoute update(int flightId, int routeId) {
        return flightRouteRepository.update(flightId, routeId);
    }

    public void delete(int flightId) {
        flightRouteRepository.delete(flightId);
    }
}
