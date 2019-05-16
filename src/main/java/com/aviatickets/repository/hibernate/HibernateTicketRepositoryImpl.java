package com.aviatickets.repository.hibernate;


import com.aviatickets.model.Ticket;
import com.aviatickets.repository.FlightRepository;
import com.aviatickets.repository.PassengerRepository;
import com.aviatickets.repository.TicketRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateTicketRepositoryImpl implements TicketRepository {
    PassengerRepository passengerRepository;
    FlightRepository flightRepository;

    private final String getByIdMessage = "SELECT * FROM tickets WHERE id = :identifier";
    private final String getAllMessage = "SELECT * FROM tickets";
    private final String deleteByIdMessage = "DELETE FROM tickets WHERE id = :identifier";

    private final String searchTicket = "SELECT * FROM tickets WHERE tickets.passenger_id in (\n" +
            "select id from passengers WHERE passengers.first_name = :firstName and passengers.last_name = :lastName)";

    public HibernateTicketRepositoryImpl() {
        passengerRepository = new HibernatePassengerRepositoryImpl();
        flightRepository = new HibernateFlightRepositoryImpl();
    }

    @Override
    public Ticket save(Ticket ticket) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
        return ticket;
    }

    @Override
    public Ticket getById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(getByIdMessage);
        sqlQuery.setInteger("identifier", id);

        Ticket ticket = null;
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String status = String.valueOf(obj[1]);
            Integer idPassenger = Integer.parseInt(String.valueOf(obj[2]));
            Integer idFlight = Integer.parseInt(String.valueOf(obj[3]));
            String created = String.valueOf(obj[4]);
            String seatType = String.valueOf(obj[5]);
            Double price = Double.parseDouble(String.valueOf(obj[6]));
            ticket = new Ticket(identifier, status, passengerRepository.getById(idPassenger),
                    flightRepository.getById(idFlight), created, seatType, price);
            break;
        }
        transaction.commit();
        session.close();
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Ticket> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String status = String.valueOf(obj[1]);
            Integer idPassenger = Integer.parseInt(String.valueOf(obj[2]));
            Integer idFlight = Integer.parseInt(String.valueOf(obj[3]));
            String created = String.valueOf(obj[4]);
            String seatType = String.valueOf(obj[5]);
            Double price = Double.parseDouble(String.valueOf(obj[6]));
            Ticket ticket = new Ticket(identifier, status, passengerRepository.getById(idPassenger),
                    flightRepository.getById(idFlight), created, seatType, price);
            list.add(ticket);
        }
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Ticket update(Ticket ticket) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(ticket);
        transaction.commit();
        session.close();
        return ticket;
    }

    @Override
    public void deleteByObject(Ticket ticket) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(ticket);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(deleteByIdMessage);
        sqlQuery.setInteger("identifier", id);
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public List<Ticket> searchTicketByPassenger(String firstName, String lastName) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(searchTicket);
        sqlQuery.setString("firstName", firstName);
        sqlQuery.setString("lastName", lastName);
        List<Ticket> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String status = String.valueOf(obj[1]);
            Integer idPassenger = Integer.parseInt(String.valueOf(obj[2]));
            Integer idFlight = Integer.parseInt(String.valueOf(obj[3]));
            String created = String.valueOf(obj[4]);
            String seatType = String.valueOf(obj[5]);
            Double price = Double.parseDouble(String.valueOf(obj[6]));
            Ticket ticket = new Ticket(identifier, status, passengerRepository.getById(idPassenger),
                    flightRepository.getById(idFlight), created, seatType, price);
            list.add(ticket);
        }
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void addFlightTicket(int idFlight, int idTicket) {

    }

    @Override
    public void deleteFlightTicket(int idFlight, int idTicket) {

    }
}
