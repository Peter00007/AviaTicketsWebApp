package com.aviatickets.service;

import com.aviatickets.model.Route;
import com.aviatickets.repository.RouteRepository;
import com.aviatickets.repository.hibernate.HibernateRouteRepositoryImpl;

import java.util.List;

public class RouteService {
    RouteRepository routeRepository;

    public RouteService() {
        routeRepository = new HibernateRouteRepositoryImpl();
    }

    public Route save(Route route) {
        return routeRepository.save(route);
    }

    public Route getById(int id) {
        return routeRepository.getById(id);
    }

    public List<Route> getAll() {
        return routeRepository.getAll();
    }

    public Route update(Route route) {
        return routeRepository.update(route);
    }

    public void delete(int id) {
        routeRepository.delete(id);
    }

    public void deleteByObject(Route route) {
        routeRepository.deleteByObject(route);
    }

    public void addRouteAirport(int idRoute, int idAirport, String airportType) {
        routeRepository.addRouteAirport(idRoute, idAirport, airportType);
    }

    public void deleteRouteAirport(int idRoute, int idAirport, String airportType) {
        routeRepository.deleteRouteAirport(idRoute, idAirport, airportType);
    }
}
