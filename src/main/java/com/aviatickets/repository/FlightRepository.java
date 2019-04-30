package com.aviatickets.repository;

import com.aviatickets.model.Flight;

import java.util.List;

public interface FlightRepository extends GenericRepository<Flight, Integer> {
    List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport);

    void deleteFlightRoute(int idFlight, int idRoute);

    void addFlightRoute(int idFlight, int idRoute);
}
