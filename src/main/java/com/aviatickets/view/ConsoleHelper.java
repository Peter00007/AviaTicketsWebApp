package com.aviatickets.view;


import java.util.Scanner;

public class ConsoleHelper {
    private final String startMessage = "Hello customer!";
    private final String menuMessage = "Enter 1, if you want search flight by route, date and type aircraft salon\n" +
            "Enter 2, if you want search ticket by passenger\n" +
            "Enter 3, if you want buy ticket\n" +
            "Enter 4, if you want return ticket\n" +
            "Enter 5, if you want doing CRUD with tables\n" +
            "Enter 'Exit', if you want exit from AviaTicketsApp";
    private final String CRUDMessage = "Enter 1, if you want do crud operation with table aircraft\n" +
            "Enter 2, if you want do crud operation with table airports\n" +
            "Enter 3, if you want do crud operation with table flight_routes\n" +
            "Enter 4, if you want do crud operation with table flights\n" +
            "Enter 5, if you want do crud operation with table flights_tickets\n" +
            "Enter 6, if you want do crud operation with table passengers\n" +
            "Enter 7, if you want do crud operation with table route_airports\n" +
            "Enter 8, if you want do crud operation with table routes\n" +
            "Enter 9, if you want do crud operation with table tickets\n" +
            "Enter 'Exit', if you want exit from this menu";

    Scanner in;
    FlightView flightView;
    TicketView ticketView;
    AircraftView aircraftView;
    AirportView airportView;
    FlightRouteView flightRouteView;
    FlightTicketView flightTicketView;
    PassengerView passengerView;
    RouteAirportView routeAirportView;
    RouteView routeView;

    public ConsoleHelper() {
        in = new Scanner(System.in);
        flightView = new FlightView();
        ticketView = new TicketView();
        aircraftView = new AircraftView();
        airportView = new AirportView();
        flightRouteView = new FlightRouteView();
        flightTicketView = new FlightTicketView();
        passengerView = new PassengerView();
        routeAirportView = new RouteAirportView();
        routeView = new RouteView();
    }

    public void startApp() {
        System.out.println(startMessage);
        String choice;
        do {
            System.out.println(menuMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    flightView.searchFlight();
                    break;
                case "2":
                    ticketView.searchTicket();
                    break;
                case "3":
                    ticketView.buyTicket();
                    break;
                case "4":
                    ticketView.returnTicket();
                    break;
                case "5":
                    String crudChoice;
                    do {
                        System.out.println(CRUDMessage);
                        crudChoice = in.next();
                        switch (crudChoice) {
                            case "1":
                                aircraftView.CRUDAircraft();
                                break;
                            case "2":
                                airportView.CRUDAirport();
                                break;
                            case "3":
                                flightRouteView.CRUDFlightRoute();
                                break;
                            case "4":
                                flightView.CRUDFlight();
                                break;
                            case "5":
                                flightTicketView.CRUDFlightsTickets();
                                break;
                            case "6":
                                passengerView.CRUDPassenger();
                                break;
                            case "7":
                                routeAirportView.CRUDRouteAirport();
                                break;
                            case "8":
                                routeView.CRUDRoute();
                                break;
                            case "9":
                                ticketView.CRUDTicket();
                                break;
                        }
                    } while (!crudChoice.equalsIgnoreCase("Exit"));
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
