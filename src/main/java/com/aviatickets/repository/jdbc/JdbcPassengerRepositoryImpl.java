package com.aviatickets.repository.jdbc;

import com.aviatickets.model.Passenger;
import com.aviatickets.repository.PassengerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPassengerRepositoryImpl implements PassengerRepository {

    private final String sqlSave = "INSERT INTO passengers(first_name, last_name, birthday_day) VALUES (?, ?, DATE (?))";
    private final String sqlGetById = "SELECT * FROM passengers WHERE id = ?";
    private final String sqlGetAll = "SELECT * FROM passengers";
    private final String sqlUpdate = "UPDATE passengers SET first_name = ?, last_name = ?, birthday_day = DATE(?) WHERE id = ?";
    private final String sqlDelete = "DELETE FROM passengers WHERE id = ? and first_name = ? and last_name = ? and birthday_day = DATE(?)";
    private final String sqlDeleteById = "DELETE FROM passengers WHERE id = ?";

    @Override
    public Passenger save(Passenger passenger) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSave);
            preparedStatement.setString(1, passenger.getFirstName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setString(3, passenger.getBirthday());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();

            passenger = new Passenger(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getDate(4).toString());

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public Passenger getById(Integer id) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Passenger passenger = null;

            while (resultSet.next()) {
                passenger = new Passenger(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4).toString());
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return passenger;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Passenger> getAll() {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Passenger> list = new ArrayList<>();

            while (resultSet.next()) {
                Passenger passenger = new Passenger(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4).toString());
                list.add(passenger);
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
    public Passenger update(Passenger passenger) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlUpdate);
            preparedStatement.setString(1, passenger.getFirstName());
            preparedStatement.setString(2, passenger.getLastName());
            preparedStatement.setString(3, passenger.getBirthday());
            preparedStatement.setInt(4, passenger.getId());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, passenger.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                passenger = new Passenger(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getDate(4).toString());
            }
            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    @Override
    public void deleteByObject(Passenger passenger) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDelete);
            preparedStatement.setInt(1, passenger.getId());
            preparedStatement.setString(2, passenger.getFirstName());
            preparedStatement.setString(3, passenger.getLastName());
            preparedStatement.setString(4, passenger.getBirthday());
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
