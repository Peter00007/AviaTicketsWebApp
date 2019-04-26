package com.aviatickets.controller;


import com.aviatickets.model.Ticket;
import com.aviatickets.repository.TicketRepository;

import java.util.List;

public class TicketController {
    TicketRepository ticketRepository;

    public TicketController() {
        ticketRepository = new TicketRepository();
    }

    public Ticket insert(String status, int passengerId, String created, String seatType, double price) {
        return ticketRepository.insert(status, passengerId, created, seatType, price);
    }

    public List<Ticket> read() {
        return ticketRepository.read();
    }

    public Ticket update(int id, String status, int passengerId, String created, String seatType, double price) {
        return ticketRepository.update(id, status, passengerId, created, seatType, price);
    }

    public void delete(int id) {
        ticketRepository.delete(id);
    }

    public List<Ticket> searchTicketByPassenger(String firstName, String lastName) {
        return ticketRepository.searchTicketByPassenger(firstName, lastName);
    }

    public int getIdTicket() {
        return ticketRepository.getIdTicket();
    }
}
