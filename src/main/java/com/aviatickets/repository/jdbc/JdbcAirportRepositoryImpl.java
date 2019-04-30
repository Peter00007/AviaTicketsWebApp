package com.aviatickets.repository.jdbc;


import com.aviatickets.model.Airport;
import com.aviatickets.repository.AirportRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcAirportRepositoryImpl implements AirportRepository {
    private final String sqlSave = "INSERT INTO airports(id, name) VALUES (?, ?)";
    private final String sqlGetById = "SELECT * FROM airports WHERE id = ?";
    private final String sqlGetAll = "SELECT * FROM airports";
    private final String sqlUpdate = "UPDATE airports SET name = ? WHERE id = ?";
    private final String sqlUpdateWhere = "SELECT * FROM airports where id = ";
    private final String sqlDelete = "DELETE FROM airports WHERE id = ? and name = ?";
    private final String sqlDeleteById = "DELETE FROM airports WHERE id = ?";


    @Override
    public Airport save(Airport airport) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSave);
            preparedStatement.setInt(1, airport.getId());
            preparedStatement.setString(2, airport.getName());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();

            Airport air = new Airport(resultSet.getInt(1), resultSet.getString(2));

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.getConnection().close();
            return air;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airport getById(Integer id) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Airport airport = null;

            if (resultSet.next()) {
                airport = new Airport(resultSet.getInt(1), resultSet.getString(2));
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.getConnection().close();
            return airport;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Airport> getAll() {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Airport> list = new ArrayList<>();

            while (resultSet.next()) {
                Airport airport = new Airport(resultSet.getInt(1), resultSet.getString(2));
                list.add(airport);
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.getConnection().close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Airport update(Airport airport) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlUpdate);
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setInt(2, airport.getId());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, airport.getId());

            ResultSet resultSet = preparedStatement.executeQuery();

            Airport air = null;
            if (resultSet.next()) {
                air = new Airport(resultSet.getInt(1), resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.getConnection().close();
            return air;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteByObject(Airport airport) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDelete);
            preparedStatement.setInt(1, airport.getId());
            preparedStatement.setString(2, airport.getName());
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDeleteById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
