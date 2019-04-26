package com.aviatickets.model;

public class Flight {
    private int id;
    private int aircraftId;
    private String flightDate;

    public Flight(int id, int aircraftId, String flightDate) {
        this.id = id;
        this.aircraftId = aircraftId;
        this.flightDate = flightDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(String flightDate) {
        this.flightDate = flightDate;
    }

    @Override
    public String toString() {
        return "Flights{" +
                "id=" + id +
                ", aircraftId=" + aircraftId +
                ", flightDate='" + flightDate + '\'' +
                '}';
    }
}
