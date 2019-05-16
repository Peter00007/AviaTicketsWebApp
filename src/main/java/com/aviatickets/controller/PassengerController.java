package com.aviatickets.controller;

import com.aviatickets.model.Passenger;
import com.aviatickets.service.PassengerService;

import java.util.List;

public class PassengerController {
    PassengerService passengerService;

    public PassengerController() {
        passengerService = new PassengerService();
    }

    public Passenger save(Passenger passenger) {
        return passengerService.save(passenger);
    }

    public Passenger getById(int id) {
        return passengerService.getById(id);
    }

    public List<Passenger> getAll() {
        return passengerService.getAll();
    }

    public Passenger update(Passenger passenger) {
        return passengerService.update(passenger);
    }

    public void delete(int id) {
        passengerService.delete(id);
    }

    public void deleteByObject(Passenger passenger) {
        passengerService.deleteByObject(passenger);
    }
}
