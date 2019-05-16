package com.aviatickets.view;


import com.aviatickets.controller.TicketController;
import com.aviatickets.model.Ticket;

import java.util.Scanner;

public class TicketView {
    TicketController ticketController;
    PassengerView passengerView;
    FlightView flightView;
    Scanner in;

    private final String firstNameMessage = "Input customer first name";
    private final String lastNameMessage = "Input customer last name";
    private final String birthdayMessage = "Input customer birthday in format 'yyyy-mm-dd' please";
    private final String seatTypeMessage = "Input customer seatType";
    private final String priceMessage = "Input ticket price";
    private final String flightIdMessage = "Input flight Id";
    private final String dateMessage = "Input day of buy ticket in format 'yyyy-mm-dd' please";
    private final String statusTicketMessage = "Input ticket status";
    private final String passengerIdMessage = "Input passenger Id";
    private final String ticketIdMessage = "Input ticket Id";

    private final String startMessage = "Enter 1, if you want save values into ticket\n" +
            "Enter 2, if you want get by ID data from ticket\n" +
            "Enter 3, if you want get all data from ticket\n" +
            "Enter 4, if you want update values into ticket\n" +
            "Enter 5, if you want delete by object values from ticket\n" +
            "Enter 6, if you want delete by Id values from ticket\n" +
            "Enter 'Exit', if you want exit from this menu";

    public TicketView() {
        ticketController = new TicketController();
        in = new Scanner(System.in);
        passengerView = new PassengerView();
        flightView = new FlightView();
    }

    public void searchTicket() {
        System.out.println(firstNameMessage);
        String firstName = in.next();
        System.out.println(lastNameMessage);
        String lastName = in.next();
        System.out.println();
        for (Ticket ticket : ticketController.searchTicketByPassenger(firstName, lastName)) {
            System.out.println(ticket);
        }
        System.out.println();
    }

    public void buyTicket() {
        System.out.println(statusTicketMessage);
        String status = in.next();
        System.out.println(passengerIdMessage);
        int idPassenger = in.nextInt();
        System.out.println(flightIdMessage);
        int idFlight = in.nextInt();
        System.out.println(dateMessage);
        String dateBuy = in.next();
        System.out.println(seatTypeMessage);
        String seatType = in.next();
        System.out.println(priceMessage);
        double price = in.nextDouble();
        Ticket ticket = new Ticket(status, passengerView.passengerController.getById(idPassenger),
                flightView.flightController.getById(idFlight), dateBuy, seatType, price);
        System.out.println(ticketController.save(ticket));
        System.out.println("\nGood work!\n");
    }

    public void returnTicket() {
        System.out.println(ticketIdMessage);
        int idTicket = in.nextInt();
        ticketController.delete(idTicket);
        ticketController.delete(idTicket);
        System.out.println("Good work!");
    }

    public void CRUDTicket() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(statusTicketMessage);
                    String status = in.next();
                    System.out.println(passengerIdMessage);
                    int idPassenger = in.nextInt();
                    System.out.println(flightIdMessage);
                    int idFlight = in.nextInt();
                    System.out.println(dateMessage);
                    String date = in.next();
                    System.out.println(seatTypeMessage);
                    String seatType = in.next();
                    System.out.println(priceMessage);
                    double price = in.nextDouble();
                    Ticket ticket = new Ticket(status, passengerView.passengerController.getById(idPassenger),
                            flightView.flightController.getById(idFlight), date, seatType, price);
                    System.out.println(ticketController.save(ticket));
                    break;
                case "2":
                    System.out.println(ticketIdMessage);
                    int getId = in.nextInt();
                    System.out.println(ticketController.getById(getId));
                    break;
                case "3":
                    for (Ticket tickets : ticketController.getAll()) {
                        System.out.println(tickets);
                    }
                    break;
                case "4":
                    System.out.println(ticketIdMessage);
                    int idTicket = in.nextInt();
                    System.out.println(statusTicketMessage);
                    String statusTicket = in.next();
                    System.out.println(passengerIdMessage);
                    int passengerId = in.nextInt();
                    System.out.println(flightIdMessage);
                    int idFlightUpdate = in.nextInt();
                    System.out.println(dateMessage);
                    String dateTicket = in.next();
                    System.out.println(seatTypeMessage);
                    String seatTypeTicket = in.next();
                    System.out.println(priceMessage);
                    double priceTicket = in.nextDouble();
                    Ticket updateTicket = new Ticket(idTicket, statusTicket, passengerView.passengerController.getById(passengerId),
                            flightView.flightController.getById(idFlightUpdate), dateTicket, seatTypeTicket, priceTicket);
                    System.out.println(ticketController.update(updateTicket));
                    break;
                case "5":
                    System.out.println(ticketIdMessage);
                    int idTicketDelete = in.nextInt();
                    System.out.println(statusTicketMessage);
                    String statusTicketDelete = in.next();
                    System.out.println(passengerIdMessage);
                    int passengerIdDelete = in.nextInt();
                    System.out.println(flightIdMessage);
                    int idFlightDelete = in.nextInt();
                    System.out.println(dateMessage);
                    String dateTicketDelete = in.next();
                    System.out.println(seatTypeMessage);
                    String seatTypeTicketDelete = in.next();
                    System.out.println(priceMessage);
                    double priceTicketDelete = in.nextDouble();
                    Ticket deleteTicket = new Ticket(idTicketDelete, statusTicketDelete, passengerView.passengerController.getById(passengerIdDelete),
                            flightView.flightController.getById(idFlightDelete), dateTicketDelete, seatTypeTicketDelete, priceTicketDelete);
                    ticketController.deleteByObject(deleteTicket);
                    break;
                case "6":
                    System.out.println(ticketIdMessage);
                    int identifier = in.nextInt();
                    ticketController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
