package com.aviatickets.view;

import com.aviatickets.controller.FlightTicketController;

import java.util.Scanner;

public class FlightTicketView {
    FlightTicketController flightTicketController;

    Scanner in;

    private final String startMessage = "Enter 1, if you want create values into flights_tickets\n" +
            "Enter 2, if you want read data with flights_tickets\n" +
            "Enter 3, if you want update values into flights_tickets\n" +
            "Enter 4, if you want delete values from flights_tickets\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String idFlightMessage = "Input Id Flight";
    private final String idTicketMessage = "Input  Id Ticket";

    public FlightTicketView() {
        flightTicketController = new FlightTicketController();
        in = new Scanner(System.in);
    }

    public void CRUDFlightsTickets() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(idFlightMessage);
                    int idFlight = in.nextInt();
                    System.out.println(idTicketMessage);
                    int idTicket = in.nextInt();
                    System.out.println(flightTicketController.insert(idTicket, idFlight));
                    break;
                case "2":
                    System.out.println(flightTicketController.read());
                    break;
                case "3":
                    System.out.println(idFlightMessage);
                    int idFl = in.nextInt();
                    System.out.println(idTicketMessage);
                    int idTi = in.nextInt();
                    System.out.println(flightTicketController.update(idTi, idFl));
                    break;
                case "4":
                    System.out.println(idTicketMessage);
                    int identifier = in.nextInt();
                    flightTicketController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
