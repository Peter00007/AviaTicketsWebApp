package com.aviatickets.repository.hibernate;


import com.aviatickets.model.Flight;
import com.aviatickets.repository.AircraftRepository;
import com.aviatickets.repository.FlightRepository;
import com.aviatickets.repository.RouteRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateFlightRepositoryImpl implements FlightRepository {
    AircraftRepository aircraftRepository;
    RouteRepository routeRepository;

    private final String getByIdMessage = "SELECT * FROM flights WHERE id = :identifier";
    private final String getAllMessage = "SELECT * FROM flights";
    private final String deleteByIdMessage = "DELETE FROM flights WHERE id = :identifier";

    private final String searchFlight = "SELECT * FROM flights WHERE date(:firstDate) < flights.flight_date \n" +
            "and date(:secondDate) > flights.flight_date and route_id in (\n" +
            "SELECT id FROM routes where id in (\n" +
            "SELECT route_id FROM route_airports where airport_type = 'Departure' and airport_id in (\n" +
            "select id from airports where airports.name = :firstAirport and id in (\n" +
            "select airport_id from route_airports where route_airports.route_id in (\n" +
            "select route_id from route_airports where route_airports.airport_type = 'Arrival' \n" +
            "and route_airports.airport_id in (select id from airports where airports.name = :secondAirport))))))";

    public HibernateFlightRepositoryImpl() {
        aircraftRepository = new HibernateAircraftRepositoryImpl();
        routeRepository = new HibernateRouteRepositoryImpl();
    }

    @Override
    public Flight save(Flight flight) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(flight);
        transaction.commit();
        session.close();
        return flight;
    }

    @Override
    public Flight getById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(getByIdMessage);
        sqlQuery.setInteger("identifier", id);

        Flight flight = null;
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            Integer idAircraft = Integer.parseInt(String.valueOf(obj[1]));
            Integer idRoute = Integer.parseInt(String.valueOf(obj[2]));
            String fligthDate = String.valueOf(obj[3]);
            flight = new Flight(identifier, aircraftRepository.getById(idAircraft),
                    routeRepository.getById(idRoute), fligthDate);
            break;
        }
        transaction.commit();
        session.close();
        return flight;
    }

    @Override
    public List<Flight> getAll() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Flight> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            Integer idAircraft = Integer.parseInt(String.valueOf(obj[1]));
            Integer idRoute = Integer.parseInt(String.valueOf(obj[2]));
            String fligthDate = String.valueOf(obj[3]);
            Flight flight = new Flight(identifier, aircraftRepository.getById(idAircraft),
                    routeRepository.getById(idRoute), fligthDate);
            list.add(flight);
        }
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Flight update(Flight flight) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(flight);
        transaction.commit();
        session.close();
        return flight;
    }

    @Override
    public void deleteByObject(Flight flight) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(flight);
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
    public List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(searchFlight);
        sqlQuery.setString("firstDate", firstDate);
        sqlQuery.setString("secondDate", secondDate);
        sqlQuery.setString("firstAirport", depAirport);
        sqlQuery.setString("secondAirport", arrAirport);
        List<Flight> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            Integer idAircraft = Integer.parseInt(String.valueOf(obj[1]));
            Integer idRoute = Integer.parseInt(String.valueOf(obj[2]));
            String fligthDate = String.valueOf(obj[3]);
            Flight flight = new Flight(identifier, aircraftRepository.getById(idAircraft),
                    routeRepository.getById(idRoute), fligthDate);
            list.add(flight);
        }
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public void deleteFlightRoute(int idFlight, int idRoute) {

    }

    @Override
    public void addFlightRoute(int idFlight, int idRoute) {

    }
}
