package com.aviatickets.repository;

import com.aviatickets.model.FlightTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightTicketRepository {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sql;
    FlightTicket flightTicket;


    public FlightTicketRepository() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        sql = "";
        flightTicket = null;
    }

    public FlightTicket insert(int ticketId, int flightId) {

        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "INSERT INTO flights_tickets(ticket_id, flight_id) VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketId);
            preparedStatement.setInt(2, flightId);
            preparedStatement.execute();

            sql = "SELECT * FROM flights_tickets";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            flightTicket = new FlightTicket(resultSet.getInt(1), resultSet.getInt(2));

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightTicket;
    }

    public List<FlightTicket> read() {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM flights_tickets";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<FlightTicket> list = new ArrayList<>();

            while (resultSet.next()) {
                flightTicket = new FlightTicket(resultSet.getInt(1), resultSet.getInt(2));
                list.add(flightTicket);
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

    public FlightTicket update(int ticketId, int flightId) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "UPDATE flights_tickets SET flight_id = ? WHERE ticket_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, flightId);
            preparedStatement.setInt(2, ticketId);
            preparedStatement.execute();

            String identifier = String.valueOf(ticketId);

            sql = "SELECT * FROM flights_tickets where ticket_id = " + identifier;
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {
                flightTicket = new FlightTicket(resultSet.getInt(1), resultSet.getInt(2));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightTicket;
    }

    public void delete(int ticketId) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "DELETE FROM flights_tickets WHERE ticket_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ticketId);
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
