package com.aviatickets.controller;


import com.aviatickets.model.Route;
import com.aviatickets.service.RouteService;

import java.util.List;

public class RouteController {
    RouteService routeService;

    public RouteController() {
        routeService = new RouteService();
    }

    public Route save(Route route) {
        return routeService.save(route);
    }

    public Route getById(int id) {
        return routeService.getById(id);
    }

    public List<Route> getAll() {
        return routeService.getAll();
    }

    public Route update(Route route) {
        return routeService.update(route);
    }

    public void delete(int id) {
        routeService.delete(id);
    }

    public void deleteByObject(Route route) {
        routeService.deleteByObject(route);
    }

    public void addRouteAirport(int idRoute, int idAirport, String airportType) {
        routeService.addRouteAirport(idRoute, idAirport, airportType);
    }

    public void deleteRouteAirport(int idRoute, int idAirport, String airportType) {
        routeService.deleteRouteAirport(idRoute, idAirport, airportType);
    }
}
