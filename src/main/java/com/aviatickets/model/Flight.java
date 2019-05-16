package com.aviatickets.model;

import javax.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "route_id")
    private Route route;
    @Column(name = "flight_date")
    private String flightDate;


    public Flight() {
    }

    public Flight(Aircraft aircraft, String flightDate) {
        this.aircraft = aircraft;
        this.flightDate = flightDate;
    }

    public Flight(int id, Aircraft aircraft, String flightDate) {
        this.id = id;
        this.aircraft = aircraft;
        this.flightDate = flightDate;
    }

    public Flight(Aircraft aircraft, Route route, String flightDate) {
        this.aircraft = aircraft;
        this.route = route;
        this.flightDate = flightDate;
    }

    public Flight(int id, Aircraft aircraft, Route route, String flightDate) {
        this.id = id;
        this.aircraft = aircraft;
        this.route = route;
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

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
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
                ", route=" + route +
                ", flightDate='" + flightDate + '\'' +
                '}';
    }
}
