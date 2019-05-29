package com.aviatickets.controller;


import com.aviatickets.model.Ticket;
import com.aviatickets.service.TicketService;

import java.util.List;

public class TicketController {
    TicketService ticketService;

    public TicketController() {
        ticketService = new TicketService();
    }

    public Ticket save(Ticket ticket) {
        return ticketService.save(ticket);
    }

    public Ticket getById(int id) {
        return ticketService.getById(id);
    }

    public List<Ticket> getAll() {
        return ticketService.getAll();
    }

    public Ticket update(Ticket ticket) {
        return ticketService.update(ticket);
    }

    public void delete(int id) {
        ticketService.delete(id);
    }

    public void deleteByObject(Ticket ticket) {
        ticketService.deleteByObject(ticket);
    }

    public List<Ticket> searchTicketByPassenger(String firstName, String lastName) {
        return ticketService.searchTicketByPassenger(firstName, lastName);
    }
}
