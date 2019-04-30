package com.aviatickets.controller;

import com.aviatickets.model.Passenger;
import com.aviatickets.repository.PassengerRepository;
import com.aviatickets.repository.jdbc.JdbcPassengerRepositoryImpl;

import java.util.List;

public class PassengerController {
    PassengerRepository passengerRepository;

    public PassengerController() {
        passengerRepository = new JdbcPassengerRepositoryImpl();
    }

    public Passenger save(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    public Passenger getById(int id) {
        return passengerRepository.getById(id);
    }

    public List<Passenger> getAll() {
        return passengerRepository.getAll();
    }

    public Passenger update(Passenger passenger) {
        return passengerRepository.update(passenger);
    }

    public void delete(int id) {
        passengerRepository.delete(id);
    }

    public void deleteByObject(Passenger passenger) {
        passengerRepository.deleteByObject(passenger);
    }
}
