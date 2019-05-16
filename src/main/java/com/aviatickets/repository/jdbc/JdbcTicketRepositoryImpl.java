package com.aviatickets.repository.jdbc;

import com.aviatickets.model.Ticket;
import com.aviatickets.repository.PassengerRepository;
import com.aviatickets.repository.TicketRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTicketRepositoryImpl implements TicketRepository {

    private final String sqlSave = "INSERT INTO tickets(status, passenger_id, created, seat_type, price) VALUES (?, ?, Date(?), ?, ?)";
    private final String sqlGetById = "SELECT * FROM tickets WHERE id = ?";
    private final String sqlGetAll = "SELECT * FROM tickets";
    private final String sqlUpdate = "UPDATE tickets SET status = ?, passenger_id = ?, created = Date(?), seat_type = ?, price = ? WHERE id = ?";
    private final String sqlDelete = "DELETE FROM tickets WHERE id = ? and status = ? and passenger_id = ? and created = Date(?) and seat_type = ? and  price = ?";
    private final String sqlDeleteById = "DELETE FROM tickets WHERE id = ?";
    private final String sqlAddFlightTicket = "INSERT INTO flights_tickets(ticket_id, flight_id) VALUES (?, ?)";
    private final String sqlDeleteFlightTicket = "DELETE FROM flights_tickets WHERE ticket_id = ? and flight_id = ?";

    private final String sqlSearchTicket = "SELECT * FROM tickets WHERE tickets.passenger_id in (\n" +
            "select id from passengers WHERE passengers.first_name = ? and passengers.last_name = ?)";

    PassengerRepository passengerRepository;

    public JdbcTicketRepositoryImpl() {
        passengerRepository = new JdbcPassengerRepositoryImpl();
    }

    @Override
    public Ticket save(Ticket ticket) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSave);
            preparedStatement.setString(1, ticket.getStatus());
            preparedStatement.setInt(2, ticket.getPassenger().getId());
            preparedStatement.setString(3, ticket.getCreated());
            preparedStatement.setString(4, ticket.getSeatType());
            preparedStatement.setDouble(5, ticket.getPrice());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, ticket.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Ticket newTicket = null;
            if (resultSet.next()) {
                newTicket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        passengerRepository.getById(resultSet.getInt(3)),
                        resultSet.getDate(4).toString(), resultSet.getString(5),
                        resultSet.getDouble(6));
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return newTicket;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ticket getById(Integer id) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Ticket ticket = null;
            if (resultSet.next()) {
                ticket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        passengerRepository.getById(resultSet.getInt(3)), resultSet.getDate(4).toString(),
                        resultSet.getString(5), resultSet.getDouble(6));
            }

            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return ticket;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> getAll() {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetAll);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Ticket> list = new ArrayList<>();

            while (resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        passengerRepository.getById(resultSet.getInt(3)), resultSet.getDate(4).toString(),
                        resultSet.getString(5), resultSet.getDouble(6));
                list.add(ticket);
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
    public Ticket update(Ticket ticket) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlUpdate);
            preparedStatement.setString(1, ticket.getStatus());
            preparedStatement.setInt(2, ticket.getPassenger().getId());
            preparedStatement.setString(3, ticket.getCreated());
            preparedStatement.setString(4, ticket.getSeatType());
            preparedStatement.setDouble(5, ticket.getPrice());
            preparedStatement.setInt(6, ticket.getId());
            preparedStatement.execute();

            preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlGetById);
            preparedStatement.setInt(1, ticket.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            Ticket newTicket = null;
            if (resultSet.next()) {
                newTicket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        passengerRepository.getById(resultSet.getInt(3)), resultSet.getDate(4).toString(),
                        resultSet.getString(5), resultSet.getDouble(6));
            }
            resultSet.close();
            preparedStatement.close();
            ConnectionUtil.closeConnection();
            return newTicket;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteByObject(Ticket ticket) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDelete);
            preparedStatement.setInt(1, ticket.getId());
            preparedStatement.setString(2, ticket.getStatus());
            preparedStatement.setInt(3, ticket.getPassenger().getId());
            preparedStatement.setString(4, ticket.getCreated());
            preparedStatement.setString(5, ticket.getSeatType());
            preparedStatement.setDouble(6, ticket.getPrice());
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

    public List<Ticket> searchTicketByPassenger(String firstName, String lastName) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlSearchTicket);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Ticket> list = new ArrayList<>();

            while (resultSet.next()) {
                Ticket ticket = new Ticket(resultSet.getInt(1), resultSet.getString(2),
                        passengerRepository.getById(resultSet.getInt(3)), resultSet.getDate(4).toString(),
                        resultSet.getString(5), resultSet.getDouble(6));
                list.add(ticket);
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

    public void addFlightTicket(int idFlight, int idTicket) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlAddFlightTicket);
            preparedStatement.setInt(1, idTicket);
            preparedStatement.setInt(2, idFlight);
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlightTicket(int idFlight, int idTicket) {
        try {
            PreparedStatement preparedStatement = ConnectionUtil.getConnection().prepareStatement(sqlDeleteFlightTicket);
            preparedStatement.setInt(1, idTicket);
            preparedStatement.setInt(2, idFlight);
            preparedStatement.execute();

            preparedStatement.close();
            ConnectionUtil.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
