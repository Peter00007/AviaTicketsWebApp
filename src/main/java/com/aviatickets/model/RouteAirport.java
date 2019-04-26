package com.aviatickets.model;


public class RouteAirport {
    private int routeId;
    private int airportId;
    private String airportType;

    public RouteAirport(int routeId, int airportId, String airportType) {
        this.routeId = routeId;
        this.airportId = airportId;
        this.airportType = airportType;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getAirportType() {
        return airportType;
    }

    public void setAirportType(String airportType) {
        this.airportType = airportType;
    }

    @Override
    public String toString() {
        return "RouteAirport{" +
                "routeId=" + routeId +
                ", airportId=" + airportId +
                ", airportType='" + airportType + '\'' +
                '}';
    }
}
