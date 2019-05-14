package com.aviatickets.repository.jdbc;


import com.aviatickets.model.Aircraft;
import com.aviatickets.repository.AircraftRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcAircraftRepositoryImpl implements AircraftRepository {
    private final String sqlSave = "INSERT INTO aircraft(id, name) VALUES (?, ?)";
    private final String sqlGetById = "SELECT * FROM aircraft WHERE id = ?";
    private final String sqlGetAll = "SELECT * FROM aircraft";
    private final String sqlUpdate = "UPDATE aircraft SET name = ? WHERE id = ?";
    private final String sqlDelete = "DELETE FROM aircraft WHERE id = ? and name = ?";
    private final String sqlDeleteById = "DELETE FROM aircraft WHERE id = ?";

    @Override
    public Aircraft save(Aircraft aircraft) {
        try {
            PreparedStatement preparedStatement;

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSave);
            preparedStatement.setInt(1, aircraft.getId());
            preparedStatement.setString(2, aircraft.getName());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);

            ResultSet resultSet;

            resultSet = preparedStatement.executeQuery();
            resultSet.last();

            Aircraft air = new Aircraft(resultSet.getInt(1), resultSet.getString(2));

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return air;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Aircraft getById(Integer id) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            Aircraft aircraft = null;
            if (resultSet.next()) {
                aircraft = new Aircraft(resultSet.getInt(1), resultSet.getString(2));
                return aircraft;
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return aircraft;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Aircraft> getAll() {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Aircraft> list = new ArrayList<>();

            while (resultSet.next()) {
                Aircraft aircraft = new Aircraft(resultSet.getInt(1), resultSet.getString(2));
                list.add(aircraft);
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Aircraft update(Aircraft aircraft) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlUpdate);
            preparedStatement.setString(1, aircraft.getName());
            preparedStatement.setInt(2, aircraft.getId());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, aircraft.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Aircraft air = null;
            if (resultSet.next()) {
                air = new Aircraft(resultSet.getInt(1), resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return air;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteByObject(Aircraft aircraft) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDelete);
            preparedStatement.setInt(1, aircraft.getId());
            preparedStatement.setString(2, aircraft.getName());
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.closeConnection();
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
            ConnectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
