package com.aviatickets.repository;


import com.aviatickets.model.RouteAirport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteAirportRepository {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sql;
    RouteAirport routeAirport;


    public RouteAirportRepository() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        sql = "";
        routeAirport = null;
    }

    public RouteAirport insert(int routeId, int airportId, String airportType) {

        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "INSERT INTO route_airports(route_id, airport_id, airport_type) VALUES (?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, routeId);
            preparedStatement.setInt(2, airportId);
            preparedStatement.setString(3, airportType);
            preparedStatement.execute();

            sql = "SELECT * FROM route_airports";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            routeAirport = new RouteAirport(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routeAirport;
    }

    public List<RouteAirport> read() {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM route_airports";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<RouteAirport> list = new ArrayList<>();

            while (resultSet.next()) {
                routeAirport = new RouteAirport(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3));
                list.add(routeAirport);
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


    public void delete(int routeId, int airportId, String airportType) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "DELETE FROM route_airports WHERE route_id = ? and airport_id = ? and airport_type = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, routeId);
            preparedStatement.setInt(2, airportId);
            preparedStatement.setString(3, airportType);
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
