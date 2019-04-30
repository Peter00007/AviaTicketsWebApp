package com.aviatickets.model;


public class Ticket {
    private int id;
    private String status;
    private Passenger passenger;
    private String created;
    private String seatType;
    private double price;

    public Ticket(int id, String status, Passenger passenger, String created, String seatType, double price) {
        this.id = id;
        this.status = status;
        this.passenger = passenger;
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
                ", created='" + created + '\'' +
                ", seatType='" + seatType + '\'' +
                ", price=" + price +
                '}';
    }
}
