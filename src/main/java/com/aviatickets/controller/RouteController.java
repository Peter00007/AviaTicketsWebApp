package com.aviatickets.controller;


import com.aviatickets.model.Route;
import com.aviatickets.repository.RouteRepository;

import java.util.List;

public class RouteController {
    RouteRepository routeRepository;

    public RouteController() {
        routeRepository = new RouteRepository();
    }

    public Route insert(String name) {
        return routeRepository.insert(name);
    }

    public List<Route> read() {
        return routeRepository.read();
    }

    public Route update (int id, String name) {
        return routeRepository.update(id, name);
    }

    public void delete (int id) {
        routeRepository.delete(id);
    }
}
