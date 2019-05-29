package com.aviatickets.repository.hibernate;

import com.aviatickets.model.Airport;
import com.aviatickets.repository.AirportRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateAirportRepositoryImpl implements AirportRepository {
    private final String getByIdMessage = "SELECT * FROM airports WHERE id = :identifier";
    private final String getAllMessage = "SELECT * FROM airports";
    private final String deleteByIdMessage = "DELETE FROM airports WHERE id = :identifier";

    @Override
    public Airport save(Airport airport) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(airport);
        transaction.commit();
        session.close();
        return airport;
    }

    @Override
    public Airport getById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getByIdMessage);
        sqlQuery.setInteger("identifier", id);

        Airport airport = null;
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            airport = new Airport(identifier, name);
            break;
        }
        session.close();
        return airport;
    }

    @Override
    public List<Airport> getAll() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Airport> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            Airport airport = new Airport(identifier, name);
            list.add(airport);
        }
        session.close();
        return list;
    }

    @Override
    public Airport update(Airport airport) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(airport);
        transaction.commit();
        session.close();
        return airport;
    }

    @Override
    public void deleteByObject(Airport airport) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(airport);
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
}
