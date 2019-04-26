package com.aviatickets.controller;

import com.aviatickets.model.Passenger;
import com.aviatickets.repository.PassengerRepository;

import java.util.List;

public class PassengerController {
    PassengerRepository passengerRepository;

    public PassengerController() {
        passengerRepository = new PassengerRepository();
    }

    public Passenger insert(String firstName, String lastName, String birthday) {
        return passengerRepository.insert(firstName, lastName, birthday);
    }

    public List<Passenger> read() {
        return passengerRepository.read();
    }

    public Passenger update(int id, String firstName, String lastName, String birthday) {
        return passengerRepository.update(id, firstName, lastName, birthday);
    }

    public void delete(int id) {
        passengerRepository.delete(id);
    }

    public int getIdPassenger(String firstName, String lastName, String birthday) {
        return passengerRepository.getIdPassenger(firstName, lastName, birthday);
    }
}
