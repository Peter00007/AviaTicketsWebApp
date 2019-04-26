package com.aviatickets.repository;


import com.aviatickets.model.FlightRoute;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRouteRepository {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sql;
    FlightRoute flightRoute;


    public FlightRouteRepository() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        sql = "";
        flightRoute = null;
    }

    public FlightRoute insert(int flightId, int routeId) {

        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "INSERT INTO flight_routes(flight_id, route_id) VALUES (?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, flightId);
            preparedStatement.setInt(2, routeId);
            preparedStatement.execute();

            sql = "SELECT * FROM flight_routes";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            flightRoute = new FlightRoute(resultSet.getInt(1), resultSet.getInt(2));

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightRoute;
    }

    public List<FlightRoute> read() {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM flight_routes";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<FlightRoute> list = new ArrayList<>();

            while (resultSet.next()) {
                flightRoute = new FlightRoute(resultSet.getInt(1), resultSet.getInt(2));
                list.add(flightRoute);
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

    public FlightRoute update(int flightId, int routeId) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "UPDATE flight_routes SET route_id = ? WHERE flight_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, routeId);
            preparedStatement.setInt(2, flightId);
            preparedStatement.execute();

            String identifier = String.valueOf(flightId);

            sql = "SELECT * FROM flight_routes where flight_id = " + identifier;
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {
                flightRoute = new FlightRoute(resultSet.getInt(1), resultSet.getInt(2));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flightRoute;
    }

    public void delete(int flightId) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "DELETE FROM flight_routes WHERE flight_id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, flightId);
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
