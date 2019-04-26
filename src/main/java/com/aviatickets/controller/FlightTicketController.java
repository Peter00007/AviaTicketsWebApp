package com.aviatickets.controller;


import com.aviatickets.model.FlightTicket;
import com.aviatickets.repository.FlightTicketRepository;

import java.util.List;

public class FlightTicketController {
    FlightTicketRepository flightTicketRepository;

    public FlightTicketController() {
        flightTicketRepository = new FlightTicketRepository();
    }

    public FlightTicket insert(int ticketId, int flightId) {
        return flightTicketRepository.insert(ticketId, flightId);
    }

    public List<FlightTicket> read() {
        return flightTicketRepository.read();
    }

    public FlightTicket update(int ticketId, int flightId) {
        return flightTicketRepository.update(ticketId, flightId);
    }

    public void delete(int ticketId) {
        flightTicketRepository.delete(ticketId);
    }
}
