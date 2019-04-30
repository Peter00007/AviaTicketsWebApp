package com.aviatickets.controller;


import com.aviatickets.model.Ticket;
import com.aviatickets.repository.TicketRepository;
import com.aviatickets.repository.jdbc.JdbcTicketRepositoryImpl;

import java.util.List;

public class TicketController {
    TicketRepository ticketRepository;

    public TicketController() {
        ticketRepository = new JdbcTicketRepositoryImpl();
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getById(int id) {
        return ticketRepository.getById(id);
    }

    public List<Ticket> getAll() {
        return ticketRepository.getAll();
    }

    public Ticket update(Ticket ticket) {
        return ticketRepository.update(ticket);
    }

    public void delete(int id) {
        ticketRepository.delete(id);
    }

    public void deleteByObject(Ticket ticket) {
        ticketRepository.deleteByObject(ticket);
    }

    public List<Ticket> searchTicketByPassenger(String firstName, String lastName) {
        return ticketRepository.searchTicketByPassenger(firstName, lastName);
    }

    public void addFlightTicket(int idFlight, int idTicket) {
        ticketRepository.addFlightTicket(idFlight, idTicket);
    }

    public void deleteFlightTicket(int idFlight, int idTicket) {
        ticketRepository.deleteFlightTicket(idFlight, idTicket);
    }
}
