package com.aviatickets.repository.hibernate;

import com.aviatickets.model.Route;
import com.aviatickets.repository.RouteRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateRouteRepositoryImpl implements RouteRepository {
    private final String getByIdMessage = "SELECT * FROM routes WHERE id = :identifier";
    private final String getAllMessage = "SELECT * FROM routes";
    private final String deleteByIdMessage = "DELETE FROM routes WHERE id = :identifier";

    private final String addRouteAirport = "INSERT INTO route_airports (route_id, airport_id, airport_type) VALUES (:idRoute, :idAirport, :airportType)";
    private final String deleteRouteAirport = "DELETE FROM route_airports WHERE route_id = :idRoute and airport_id = :idAirport and airport_type = :airportType";

    @Override
    public Route save(Route route) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(route);
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public Route getById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getByIdMessage);
        sqlQuery.setInteger("identifier", id);

        Route route = null;
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            route = new Route(identifier, name);
            break;
        }
        session.close();
        return route;
    }

    @Override
    public List<Route> getAll() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Route> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            Route route = new Route(identifier, name);
            list.add(route);
        }
        session.close();
        return list;
    }

    @Override
    public Route update(Route route) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(route);
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public void deleteByObject(Route route) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(route);
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
    public void addRouteAirport(int idRoute, int idAirport, String airportType) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(addRouteAirport);
        sqlQuery.setInteger("idRoute", idRoute);
        sqlQuery.setInteger("idAirport", idAirport);
        sqlQuery.setString("airportType", airportType);
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteRouteAirport(int idRoute, int idAirport, String airportType) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(deleteRouteAirport);
        sqlQuery.setInteger("idRoute", idRoute);
        sqlQuery.setInteger("idAirport", idAirport);
        sqlQuery.setString("airportType", airportType);
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }
}
