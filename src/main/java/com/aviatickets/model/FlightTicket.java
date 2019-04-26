package com.aviatickets.model;


public class FlightTicket {
    private int ticketId;
    private int flightId;

    public FlightTicket(int ticketId, int flightId) {
        this.ticketId = ticketId;
        this.flightId = flightId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    @Override
    public String toString() {
        return "FlightsTickets{" +
                "ticketId=" + ticketId +
                ", flightId=" + flightId +
                '}';
    }
}
