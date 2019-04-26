package com.aviatickets.controller;


import com.aviatickets.model.RouteAirport;
import com.aviatickets.repository.RouteAirportRepository;

import java.util.List;

public class RouteAirportController {
    RouteAirportRepository routeAirportRepository;

    public RouteAirportController () {
        routeAirportRepository = new RouteAirportRepository();
    }

    public RouteAirport insert(int routeId, int airportId, String airportType) {
        return routeAirportRepository.insert(routeId, airportId, airportType);
    }

    public List<RouteAirport> read() {
        return routeAirportRepository.read();
    }

    public void delete(int routeId, int airprotId, String airportType) {
        routeAirportRepository.delete(routeId, airprotId, airportType);
    }
}
