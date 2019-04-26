package com.aviatickets.controller;


import com.aviatickets.model.Aircraft;
import com.aviatickets.repository.AircraftRepository;

import java.util.List;

public class AircraftController {
    AircraftRepository aircraftRepository;

    public AircraftController() {
        aircraftRepository = new AircraftRepository();
    }

    public Aircraft insert(String name) {
        return aircraftRepository.insert(name);
    }

    public List<Aircraft> read() {
        return aircraftRepository.read();
    }

    public Aircraft update (int id, String name) {
        return aircraftRepository.update(id, name);
    }

    public void delete (int id) {
        aircraftRepository.delete(id);
    }
}
