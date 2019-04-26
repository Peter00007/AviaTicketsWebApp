package com.aviatickets.repository;


import com.aviatickets.model.Airport;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AirportRepository {
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    String sql;
    Airport airport;


    public AirportRepository() {
        connection = null;
        preparedStatement = null;
        resultSet = null;
        sql = "";
    }

    public Airport insert(String name) {

        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "INSERT INTO airports(name) VALUES (?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.execute();

            sql = "SELECT * FROM airports";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            airport = new Airport(resultSet.getInt(1), resultSet.getString(2));

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airport;
    }

    public List<Airport> read() {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "SELECT * FROM airports";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            List<Airport> list = new ArrayList<>();

            while (resultSet.next()) {
                airport = new Airport(resultSet.getInt(1), resultSet.getString(2));
                list.add(airport);
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

    public Airport update(int id, String name) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "UPDATE airports SET name = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

            String identifier = String.valueOf(id);

            sql = "SELECT * FROM airports where id = " + identifier;
            resultSet = preparedStatement.executeQuery(sql);

            if (resultSet.next()) {
                airport = new Airport(resultSet.getInt(1), resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airport;
    }

    public void delete(int id) {
        try {
            Class.forName(JDBCData.JDBC_DRIVER);

            connection = DriverManager.getConnection(JDBCData.DATABASE_URL, JDBCData.USER, JDBCData.PASSWORD);

            sql = "DELETE FROM airports WHERE id = ?";

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
}
