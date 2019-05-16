package com.aviatickets.repository.hibernate;


import com.aviatickets.model.Passenger;
import com.aviatickets.repository.PassengerRepository;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HibernatePassengerRepositoryImpl implements PassengerRepository {
    private final String getByIdMessage = "SELECT * FROM passengers WHERE id = :identifier";
    private final String getAllMessage = "SELECT * FROM passengers";
    private final String deleteByIdMessage = "DELETE FROM passengers WHERE id = :identifier";

    @Override
    public Passenger save(Passenger passenger) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passenger);
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public Passenger getById(Integer id) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(getByIdMessage);
        sqlQuery.setInteger("identifier", id);

        Passenger passenger = null;
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String firstName = String.valueOf(obj[1]);
            String lastName = String.valueOf(obj[2]);
            String birthday = String.valueOf(obj[3]);
            passenger = new Passenger(identifier, firstName, lastName, birthday);
            break;
        }
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public List<Passenger> getAll() {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        SQLQuery sqlQuery = session.createSQLQuery(getAllMessage);
        List<Passenger> list = new ArrayList<>();
        List<Object> result = (List<Object>) sqlQuery.list();
        Iterator itr = result.iterator();
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            Integer identifier = Integer.parseInt(String.valueOf(obj[0]));
            String firstName = String.valueOf(obj[1]);
            String lastName = String.valueOf(obj[2]);
            String birthday = String.valueOf(obj[3]);
            Passenger passenger = new Passenger(identifier, firstName, lastName, birthday);
            list.add(passenger);
        }
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public Passenger update(Passenger passenger) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.update(passenger);
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public void deleteByObject(Passenger passenger) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.delete(passenger);
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
