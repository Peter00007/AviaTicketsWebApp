package com.aviatickets.repository;

import com.aviatickets.model.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sql;
    Flight flight;


    public FlightRepository() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        sql = "";
        flight = null;
    }

    public Flight insert(int aircraftId, String flightDate) {

        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "INSERT INTO flights(aircraft_id, flight_date) VALUES (?, DATE(?))";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, aircraftId);
            preparedStatement.setString(2, flightDate);
            preparedStatement.execute();

            sql = "SELECT * FROM flights";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            flight = new Flight(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public List<Flight> read() {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM flights";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<Flight> list = new ArrayList<>();

            while (resultSet.next()) {
                flight = new Flight(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
                list.add(flight);
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

    public Flight update(int id, int aircraftId, String flightDate) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "UPDATE flights SET aircraft_id = ?, flight_date = DATE(?) WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, aircraftId);
            preparedStatement.setString(2, flightDate);
            preparedStatement.setInt(3, id);
            preparedStatement.execute();

            String identifier = String.valueOf(id);

            sql = "SELECT * FROM flights where id = " + identifier;
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {
                String date = resultSet.getDate(3).toString();
                flight = new Flight(resultSet.getInt(1), resultSet.getInt(2), date);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flight;
    }

    public void delete(int id) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "DELETE FROM flights WHERE id = ?";

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

    public List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM flights WHERE date(?) < flights.flight_date and date(?) > flights.flight_date and id in (\n" +
                    "  SELECT flight_id FROM flight_routes where route_id in (\n" +
                    "    SELECT id FROM routes where routes.id in (\n" +
                    "      SELECT route_id FROM route_airports where airport_type = 'Departure' and airport_id in (\n" +
                    "      select id from airports where airports.name = ? and id in (\n" +
                    "      select airport_id from route_airports where route_airports.route_id in (\n" +
                    "      select route_id from route_airports where route_airports.airport_type = 'Arrival' and route_airports.airport_id in (\n" +
                    "      select id from airports where airports.name = ?)))))))";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstDate);
            preparedStatement.setString(2, secondDate);
            preparedStatement.setString(3, depAirport);
            preparedStatement.setString(4, arrAirport);
            preparedStatement.execute();

            resultSet = preparedStatement.executeQuery();

            List<Flight> list = new ArrayList<>();

            while (resultSet.next()) {
                flight = new Flight(resultSet.getInt(1), resultSet.getInt(2), resultSet.getDate(3).toString());
                list.add(flight);
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
}
