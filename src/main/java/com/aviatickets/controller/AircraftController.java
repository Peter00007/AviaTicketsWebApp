package com.aviatickets.controller;


import com.aviatickets.model.Aircraft;
import com.aviatickets.repository.AircraftRepository;
import com.aviatickets.repository.hibernate.HibernateAircraftRepositoryImpl;

import java.util.List;

public class AircraftController {
    AircraftRepository aircraftRepository;

    public AircraftController() {
        aircraftRepository = new HibernateAircraftRepositoryImpl();
    }

    public Aircraft save(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    public Aircraft getById(int id) {
        return aircraftRepository.getById(id);
    }

    public List<Aircraft> getAll() {
        return aircraftRepository.getAll();
    }

    public Aircraft update(Aircraft aircraft) {
        return aircraftRepository.update(aircraft);
    }

    public void delete(int id) {
        aircraftRepository.delete(id);
    }

    public void deleteByObject(Aircraft aircraft) {
        aircraftRepository.deleteByObject(aircraft);
    }
}
