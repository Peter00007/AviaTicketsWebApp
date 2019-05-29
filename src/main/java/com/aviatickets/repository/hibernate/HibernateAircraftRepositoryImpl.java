package com.aviatickets.repository.hibernate;

import com.aviatickets.model.Aircraft;
import com.aviatickets.repository.AircraftRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernateAircraftRepositoryImpl implements AircraftRepository {
    private final String getByIdMessage = "SELECT * FROM aircraft WHERE id = :identifier";
    private final String getAllMessage = "SELECT * FROM aircraft";
    private final String deleteByIdMessage = "DELETE FROM aircraft WHERE id = :identifier";

    @Override
    public Aircraft save(Aircraft aircraft) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(aircraft);
        transaction.commit();
        session.close();
        return aircraft;
    }

    @Override
    public Aircraft getById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getByIdMessage);
        sqlQuery.setInteger("identifier", id);

        Aircraft aircraft = null;
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            aircraft = new Aircraft(identifier, name);
            break;
        }
        session.close();
        return aircraft;
    }

    @Override
    public List<Aircraft> getAll() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Aircraft> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String name = String.valueOf(obj[1]);
            Aircraft aircraft = new Aircraft(identifier, name);
            list.add(aircraft);
        }
        session.close();
        return list;
    }

    @Override
    public Aircraft update(Aircraft aircraft) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(aircraft);
        transaction.commit();
        session.close();
        return aircraft;
    }

    @Override
    public void deleteByObject(Aircraft aircraft) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(aircraft);
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
