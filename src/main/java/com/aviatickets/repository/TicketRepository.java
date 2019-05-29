package com.aviatickets.repository;

import com.aviatickets.model.Ticket;

import java.util.List;

public interface TicketRepository extends GenericRepository<Ticket, Integer> {
    List<Ticket> searchTicketByPassenger(String firstName, String lastName);
}