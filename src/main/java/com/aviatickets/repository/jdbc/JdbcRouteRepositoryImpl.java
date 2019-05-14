package com.aviatickets.repository.jdbc;


import com.aviatickets.model.Route;
import com.aviatickets.repository.RouteRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRouteRepositoryImpl implements RouteRepository {

    private final String sqlSave = "INSERT INTO routes(id, name) VALUES (?, ?)";
    private final String sqlGetById = "SELECT * FROM routes WHERE id = ?";
    private final String sqlGetAll = "SELECT * FROM routes";
    private final String sqlUpdate = "UPDATE routes SET name = ? WHERE id = ?";
    private final String sqlDelete = "DELETE FROM routes WHERE id = ? and name = ?";
    private final String sqlDeleteById = "DELETE FROM routes WHERE id = ?";

    private final String sqlAddRouteAirport = "INSERT INTO route_airports (route_id, airport_id, airport_type) VALUES (?, ?, ?)";
    private final String sqlDeleteRouteAirport = "DELETE FROM route_airports WHERE route_id = ? and airport_id = ? and airport_type = ?";


    @Override
    public Route save(Route route) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSave);
            preparedStatement.setInt(1, route.getId());
            preparedStatement.setString(2, route.getName());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, route.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Route newRoute = null;
            if (resultSet.next()) {
                newRoute = new Route(resultSet.getInt(1), resultSet.getString(2));
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return newRoute;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Route getById(Integer id) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Route route = null;
            if (resultSet.next()) {
                route = new Route(resultSet.getInt(1), resultSet.getString(2));
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return route;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Route> getAll() {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Route> list = new ArrayList<>();

            while (resultSet.next()) {
                Route route = new Route(resultSet.getInt(1), resultSet.getString(2));
                list.add(route);
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
    public Route update(Route route) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlUpdate);
            preparedStatement.setString(1, route.getName());
            preparedStatement.setInt(2, route.getId());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, route.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Route newRoute = null;
            if (resultSet.next()) {
                route = new Route(resultSet.getInt(1), resultSet.getString(2));
            }
            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return newRoute;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteByObject(Route route) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDelete);
            preparedStatement.setInt(1, route.getId());
            preparedStatement.setString(2, route.getName());
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

    @Override
    public void addRouteAirport(int idRoute, int idAirport, String airportType) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlAddRouteAirport);
            preparedStatement.setInt(1, idRoute);
            preparedStatement.setInt(2, idAirport);
            preparedStatement.setString(3, airportType);
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRouteAirport(int idRoute, int idAirport, String airportType) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDeleteRouteAirport);
            preparedStatement.setInt(1, idRoute);
            preparedStatement.setInt(2, idAirport);
            preparedStatement.setString(3, airportType);
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
