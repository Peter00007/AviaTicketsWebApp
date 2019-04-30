package com.aviatickets.model;

public class Flight {
    private int id;
    private Aircraft aircraft;
    private String flightDate;

    public Flight(int id, Aircraft aircraft, String flightDate) {
        this.id = id;
        this.aircraft = aircraft;
        this.flightDate = flightDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", aircraft=" + aircraft +
                ", flightDate='" + flightDate + '\'' +
                '}';
    }
}
