package com.aviatickets.repository.jdbc;


import com.aviatickets.model.Flight;
import com.aviatickets.repository.AircraftRepository;
import com.aviatickets.repository.FlightRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcFlightRepositoryImpl implements FlightRepository {
    private final String sqlSave = "INSERT INTO flights(id, aircraft_id, flight_date) VALUES (?, ?, DATE(?))";
    private final String sqlGetById = "SELECT * FROM flights WHERE id = ?";
    private final String sqlGetAll = "SELECT * FROM flights";
    private final String sqlUpdate = "UPDATE flights SET aircraft_id = ?, flight_date = DATE(?) WHERE id = ?";
    private final String sqlDelete = "DELETE FROM flights WHERE id = ? and aircraft_id = ? and flight_date = ?";
    private final String sqlDeleteById = "DELETE FROM flights WHERE id = ?";
    private final String sqlAddFlightRoute = "INSERT INTO flight_routes (flight_id, route_id) VALUES (?, ?)";
    private final String sqlDeleteFlightTicket = "DELETE FROM flight_routes WHERE flight_id = ? and route_id = ?";

    private final String sqlSearchFlight = "SELECT * FROM flights WHERE date(?) < flights.flight_date and date(?) > flights.flight_date and id in (\n" +
            "  SELECT flight_id FROM flight_routes where route_id in (\n" +
            "    SELECT id FROM routes where routes.id in (\n" +
            "      SELECT route_id FROM route_airports where airport_type = 'Departure' and airport_id in (\n" +
            "      select id from airports where airports.name = ? and id in (\n" +
            "      select airport_id from route_airports where route_airports.route_id in (\n" +
            "      select route_id from route_airports where route_airports.airport_type = 'Arrival' and route_airports.airport_id in (\n" +
            "      select id from airports where airports.name = ?)))))))";

    AircraftRepository aircraftRepository;

    public JdbcFlightRepositoryImpl() {
        aircraftRepository = new JdbcAircraftRepositoryImpl();
    }


    @Override
    public Flight save(Flight flight) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSave);
            preparedStatement.setInt(1, flight.getId());
            preparedStatement.setInt(2, flight.getAircraft().getId());
            preparedStatement.setString(3, flight.getFlightDate());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.last();

            Flight newFlight = new Flight(resultSet.getInt(1), aircraftRepository.getById(resultSet.getInt(2)), resultSet.getString(3));

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.getConnection().close();
            return newFlight;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Flight getById(Integer id) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            Flight flight = null;
            if (resultSet.next()) {
                flight = new Flight(resultSet.getInt(1),
                        aircraftRepository.getById(resultSet.getInt(2)), resultSet.getString(3));
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.getConnection().close();
            return flight;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Flight> getAll() {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Flight> list = new ArrayList<>();

            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt(1),
                        aircraftRepository.getById(resultSet.getInt(2)), resultSet.getString(3));
                list.add(flight);
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
    public Flight update(Flight flight) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlUpdate);
            preparedStatement.setInt(1, flight.getAircraft().getId());
            preparedStatement.setString(2, flight.getFlightDate());
            preparedStatement.setInt(3, flight.getId());
            preparedStatement.execute();


            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, flight.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Flight newFlight = null;
            if (resultSet.next()) {
                newFlight = new Flight(resultSet.getInt(1),
                        aircraftRepository.getById(resultSet.getInt(2)), resultSet.getString(3));
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.getConnection().close();
            return newFlight;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteByObject(Flight flight) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDelete);
            preparedStatement.setInt(1, flight.getId());
            preparedStatement.setInt(2, flight.getAircraft().getId());
            preparedStatement.setString(3, flight.getFlightDate());
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

    @Override
    public List<Flight> searchByFlight(String firstDate, String secondDate, String depAirport, String arrAirport) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSearchFlight);
            preparedStatement.setString(1, firstDate);
            preparedStatement.setString(2, secondDate);
            preparedStatement.setString(3, depAirport);
            preparedStatement.setString(4, arrAirport);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Flight> list = new ArrayList<>();

            while (resultSet.next()) {
                Flight flight = new Flight(resultSet.getInt(1),
                        aircraftRepository.getById(resultSet.getInt(2)), resultSet.getString(3));
                list.add(flight);
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
    public void addFlightRoute(int idFlight, int idRoute) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlAddFlightRoute);
            preparedStatement.setInt(1, idFlight);
            preparedStatement.setInt(2, idRoute);
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFlightRoute(int idFlight, int idRoute) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDeleteFlightTicket);
            preparedStatement.setInt(1, idFlight);
            preparedStatement.setInt(2, idRoute);
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
