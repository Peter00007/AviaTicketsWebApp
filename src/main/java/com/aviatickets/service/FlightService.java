package com.aviatickets.service;


import com.aviatickets.model.Flight;
import com.aviatickets.repository.FlightRepository;
import com.aviatickets.repository.hibernate.HibernateFlightRepositoryImpl;

import java.util.List;

public class FlightService {
    FlightRepository flightRepository;

    public FlightService() {
        flightRepository = new HibernateFlightRepositoryImpl();
    }

    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight getById(int id) {
        return flightRepository.getById(id);
    }

    public List<Flight> getAll() {
        return flightRepository.getAll();
    }

    public Flight update(Flight flight) {
        return flightRepository.update(flight);
    }

    public void delete(int id) {
        flightRepository.delete(id);
    }

    public void deleteByObject(Flight flight) {
        flightRepository.deleteByObject(flight);
    }

    public List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport) {
        return flightRepository.searchByFlight(firstDate, secondDate, depAirport, arrAirport);
    }
}
