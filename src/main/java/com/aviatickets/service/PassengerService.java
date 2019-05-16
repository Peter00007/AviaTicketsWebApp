package com.aviatickets.service;


import com.aviatickets.model.Passenger;
import com.aviatickets.repository.PassengerRepository;
import com.aviatickets.repository.hibernate.HibernatePassengerRepositoryImpl;

import java.util.List;

public class PassengerService {
    PassengerRepository passengerRepository;

    public PassengerService() {
        passengerRepository = new HibernatePassengerRepositoryImpl();
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
