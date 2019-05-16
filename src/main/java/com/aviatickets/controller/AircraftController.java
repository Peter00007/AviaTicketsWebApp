package com.aviatickets.controller;


import com.aviatickets.model.Aircraft;
import com.aviatickets.service.AircraftService;

import java.util.List;

public class AircraftController {
    AircraftService aircraftService;

    public AircraftController() {
        aircraftService = new AircraftService();
    }

    public Aircraft save(Aircraft aircraft) {
        return aircraftService.save(aircraft);
    }

    public Aircraft getById(int id) {
        return aircraftService.getById(id);
    }

    public List<Aircraft> getAll() {
        return aircraftService.getAll();
    }

    public Aircraft update(Aircraft aircraft) {
        return aircraftService.update(aircraft);
    }

    public void delete(int id) {
        aircraftService.delete(id);
    }

    public void deleteByObject(Aircraft aircraft) {
        aircraftService.deleteByObject(aircraft);
    }
}
