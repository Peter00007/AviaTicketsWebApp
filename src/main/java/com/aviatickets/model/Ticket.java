package com.aviatickets.model;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private String status;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "flight_id")
    private Flight flight;
    @Column(name = "created")
    private String created;
    @Column(name = "seat_type")
    private String seatType;
    @Column(name = "price")
    private double price;

    public Ticket() {
    }

    public Ticket(int id, String status, Passenger passenger, String created, String seatType, double price) {
        this.id = id;
        this.status = status;
        this.passenger = passenger;
        this.created = created;
        this.seatType = seatType;
        this.price = price;
    }

    public Ticket(int id, String status, Passenger passenger, Flight flight, String created, String seatType, double price) {
        this.id = id;
        this.status = status;
        this.passenger = passenger;
        this.flight = flight;
        this.created = created;
        this.seatType = seatType;
        this.price = price;
    }

    public Ticket(String status, Passenger passenger, Flight flight, String created, String seatType, double price) {
        this.status = status;
        this.passenger = passenger;
        this.flight = flight;
        this.created = created;
        this.seatType = seatType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", created='" + created + '\'' +
                ", seatType='" + seatType + '\'' +
                ", price=" + price +
                '}';
    }
}
