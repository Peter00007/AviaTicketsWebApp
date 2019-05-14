package com.aviatickets.repository.hibernate;

import com.aviatickets.model.Aircraft;
import com.aviatickets.repository.AircraftRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateAircraftRepositoryImpl implements AircraftRepository {

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
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM aircraft WHERE id = ?");
        sqlQuery.setInteger(1, id);
        sqlQuery.executeUpdate();
        List<Aircraft> aircraft = sqlQuery.list();
        transaction.commit();
        session.close();
        return aircraft.get(0);
    }

    @Override
    public List<Aircraft> getAll() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery("SELECT * FROM aircraft");
        List<Aircraft> aircraft = sqlQuery.list();
        transaction.commit();
        session.close();
        return aircraft;
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

        SQLQuery sqlQuery = session.createSQLQuery("DELETE FROM aircraft WHERE id = ?");
        sqlQuery.setInteger(1, id);
        sqlQuery.executeUpdate();
        transaction.commit();
        session.close();
    }
}
