package com.aviatickets.model;

public class FlightRoute {
    private int flightId;
    private int routeId;

    public FlightRoute(int flightId, int route_id) {
        this.flightId = flightId;
        this.routeId = route_id;
    }

    public int getFlight_id() {
        return flightId;
    }

    public void setFlight_id(int flight_id) {
        this.flightId = flight_id;
    }

    public int getRoute_id() {
        return routeId;
    }

    public void setRoute_id(int route_id) {
        this.routeId = route_id;
    }

    @Override
    public String toString() {
        return "Flight_Routes{" +
                "flight_id=" + flightId +
                ", route_id=" + routeId +
                '}';
    }
}
