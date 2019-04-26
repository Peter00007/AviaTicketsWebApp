package com.aviatickets.view;


import com.aviatickets.controller.TicketController;
import com.aviatickets.model.Ticket;

import java.util.Scanner;

public class TicketView {
    TicketController ticketController;
    PassengerView passengerView;
    FlightTicketView flightTicketView;
    Scanner in;

    private final String firstNameMessage = "Input customer first name";
    private final String lastNameMessage = "Input customer last name";
    private final String birthdayMessage = "Input customer birthday in format 'yyyy-mm-dd' please";
    private final String seatTypeMessage = "Input customer seatType";
    private final String priceMessage = "Input ticket price";
    private final String flightIdMessage = "Input flight Id";
    private final String dateMessage = "Input day of buy ticket in format 'yyyy-mm-dd' please";
    private final String returnTicketMessage = "Input ticket Id ";
    private final String statusTicketMessage = "Input ticket status";
    private final String passengerIdMessage = "Input passenger Id";

    private final String startMessage = "Enter 1, if you want create values into tickets\n" +
            "Enter 2, if you want read data with tickets\n" +
            "Enter 3, if you want update values into tickets\n" +
            "Enter 4, if you want delete values from tickets\n" +
            "Enter 'Exit', if you want exit from this menu";

    public TicketView() {
        ticketController = new TicketController();
        in = new Scanner(System.in);
        passengerView = new PassengerView();
        flightTicketView = new FlightTicketView();
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
        System.out.println(firstNameMessage);
        String firstName = in.next();
        System.out.println(lastNameMessage);
        String lastName = in.next();
        System.out.println(birthdayMessage);
        String birthday = in.next();
        System.out.println(seatTypeMessage);
        String seatType = in.next();
        System.out.println(dateMessage);
        String dateBuy = in.next();
        System.out.println(priceMessage);
        double price = in.nextDouble();
        System.out.println(flightIdMessage);
        int flightId = in.nextInt();
        System.out.println();

        System.out.println(passengerView.passengerController.insert(firstName, lastName, birthday));
        int identifier = passengerView.passengerController.getIdPassenger(firstName, lastName, birthday);
        System.out.println(ticketController.insert("Reserved", identifier, dateBuy, seatType, price));
        int ticketId = ticketController.getIdTicket();
        System.out.println(flightTicketView.flightTicketController.insert(ticketId, flightId));
        System.out.println("\nGood work!\n");
    }

    public void returnTicket() {
        System.out.println(returnTicketMessage);
        int id = in.nextInt();
        flightTicketView.flightTicketController.delete(id);
        ticketController.delete(id);
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
                    System.out.println(dateMessage);
                    String date = in.next();
                    System.out.println(seatTypeMessage);
                    String seatType = in.next();
                    System.out.println(priceMessage);
                    double price = in.nextDouble();
                    System.out.println(ticketController.insert(status, idPassenger, date, seatType, price));
                    break;
                case "2":
                    System.out.println(ticketController.read());
                    break;
                case "3":
                    System.out.println(returnTicketMessage);
                    int id = in.nextInt();
                    System.out.println(statusTicketMessage);
                    String statusTicket = in.next();
                    System.out.println(passengerIdMessage);
                    int passengerId = in.nextInt();
                    System.out.println(dateMessage);
                    String dateTicket = in.next();
                    System.out.println(seatTypeMessage);
                    String seatTypeTicket = in.next();
                    System.out.println(priceMessage);
                    double priceTicket = in.nextDouble();
                    System.out.println(ticketController.update(id, statusTicket, passengerId, dateTicket,
                            seatTypeTicket, priceTicket));
                    break;
                case "4":
                    System.out.println(returnTicketMessage);
                    int identifier = in.nextInt();
                    ticketController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
