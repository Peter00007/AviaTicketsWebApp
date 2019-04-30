package com.aviatickets.repository;

import com.aviatickets.model.Route;

public interface RouteRepository extends GenericRepository<Route, Integer> {
    void addRouteAirport(int idRoute, int idAirport, String airportType);

    void deleteRouteAirport(int idRoute, int idAirport, String airportType);
}
