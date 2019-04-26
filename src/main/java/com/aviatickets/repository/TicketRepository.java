package com.aviatickets.repository;

import com.aviatickets.model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sql;
    Ticket ticket;


    public TicketRepository() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        sql = "";
        ticket = null;
    }

    public Ticket insert(String status, int passengerId, String created, String seatType, double price) {

        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "INSERT INTO tickets(status, passenger_id, created, seat_type, price) VALUES (?, ?, Date(?), ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, passengerId);
            preparedStatement.setString(3, created);
            preparedStatement.setString(4, seatType);
            preparedStatement.setDouble(5, price);
            preparedStatement.execute();

            sql = "SELECT * FROM tickets";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            ticket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getDate(4).toString(),
                    resultSet.getString(5), resultSet.getDouble(6));

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public List<Ticket> read() {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM tickets";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<Ticket> list = new ArrayList<>();

            while (resultSet.next()) {
                ticket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getDate(4).toString(),
                        resultSet.getString(5), resultSet.getDouble(6));
                list.add(ticket);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Ticket update(int id, String status, int passengerId, String created, String seatType, double price) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "UPDATE tickets SET status = ?, passenger_id = ?, created = ?, seat_type = ?, price = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, passengerId);
            preparedStatement.setString(3, created);
            preparedStatement.setString(4, seatType);
            preparedStatement.setDouble(5, price);
            preparedStatement.setInt(6, id);
            preparedStatement.execute();

            String identifier = String.valueOf(id);

            sql = "SELECT * FROM tickets where id = " + identifier;
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {
                ticket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getDate(4).toString(),
                        resultSet.getString(5), resultSet.getDouble(6));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    public void delete(int id) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "DELETE FROM tickets WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> searchTicketByPassenger(String firstName, String lastName) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "select * from tickets where tickets.passenger_id in (\n" +
                    "select id from passengers where passengers.first_name = ? and passengers.last_name = ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.execute();

            resultSet = preparedStatement.executeQuery();

            List<Ticket> list = new ArrayList<>();

            while (resultSet.next()) {
                ticket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getDate(4).toString(),
                        resultSet.getString(5), resultSet.getDouble(6));
                list.add(ticket);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return list;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getIdTicket() {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM tickets";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            resultSet = preparedStatement.executeQuery();
            resultSet.last();
            int identifier = resultSet.getInt(1);

            resultSet.close();
            preparedStatement.close();
            connection.close();
            return identifier;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}