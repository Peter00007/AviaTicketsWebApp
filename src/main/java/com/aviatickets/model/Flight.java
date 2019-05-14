package com.aviatickets.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;
    @Column(name = "flight_date")
    private String flightDate;

    @OneToMany(mappedBy = "flight", fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "flight_routes",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "route_id"))
    private Route route;

    public Flight() {
    }

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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
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
