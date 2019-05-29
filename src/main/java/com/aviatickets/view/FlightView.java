package com.aviatickets.view;


import com.aviatickets.controller.FlightController;
import com.aviatickets.model.Flight;

import java.util.Scanner;

public class FlightView {

    private final String firstDateMessage = "Input min departude date in format 'yyyy-mm-dd' please";
    private final String lastDateMessage = "Input max departude date in format 'yyyy-mm-dd' please";
    private final String depAirportMessage = "Input departure airport name";
    private final String arrAirportMessage = "Input arrival airport name";

    private final String startMessage = "Enter 1, if you want save values into flight\n" +
            "Enter 2, if you want get by ID data with flight\n" +
            "Enter 3, if you want get all data with flight\n" +
            "Enter 4, if you want update values into flight\n" +
            "Enter 5, if you want delete by object values from flight\n" +
            "Enter 6, if you want delete by Id values from flight\n" +
            "Enter 'Exit', if you want exit from this menu";

    private final String idAircraftMessage = "Input Id aircraft";
    private final String idFlightMessage = "Input Id flight";
    private final String flightDateMessage = "Input  flight date";

    private final String flightRouteMessage = "Input 1, if if you want save values into flights_routes\n" +
            "Input 2, if if you want delete values from flights_routes\n" +
            "Input 'exit', if if you want exit\n";
    private final String routeIdMessage = "Input route Id";

    Scanner in;
    AircraftView aircraftView;
    FlightController flightController;
    RouteView routeView;

    public FlightView() {
        flightController = new FlightController();
        in = new Scanner(System.in);
        aircraftView = new AircraftView();
        routeView = new RouteView();
    }

    public void searchFlight() {
        System.out.println(firstDateMessage);
        String firstDate = in.next();
        System.out.println(lastDateMessage);
        String lastDate = in.next();
        System.out.println(depAirportMessage);
        String depAirport = in.next();
        System.out.println(arrAirportMessage);
        String arrAirport = in.next();
        System.out.println();
        for (Flight flight : flightController.searchByFlight(firstDate, lastDate, depAirport, arrAirport)) {
            System.out.println(flight);
        }
        System.out.println();
    }

    public void CRUDFlight() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(idAircraftMessage);
                    int idAircraft = in.nextInt();
                    System.out.println(routeIdMessage);
                    int idRoute = in.nextInt();
                    System.out.println(flightDateMessage);
                    String dateFlight = in.next();
                    Flight flight = new Flight(aircraftView.aircraftController.getById(idAircraft),
                            routeView.routeController.getById(idRoute), dateFlight);
                    System.out.println(flightController.save(flight));
                    break;
                case "2":
                    System.out.println(idFlightMessage);
                    int id = in.nextInt();
                    System.out.println(flightController.getById(id));
                    break;
                case "3":
                    for (Flight flights : flightController.getAll()) {
                        System.out.println(flights);
                    }
                    break;
                case "4":
                    System.out.println(idFlightMessage);
                    int identifier = in.nextInt();
                    System.out.println(idAircraftMessage);
                    int newIdFlight = in.nextInt();
                    System.out.println(routeIdMessage);
                    int idRouteUpdate = in.nextInt();
                    System.out.println(flightDateMessage);
                    String dateForFlight = in.next();
                    Flight updateFlight = new Flight(identifier, aircraftView.aircraftController.getById(newIdFlight),
                            routeView.routeController.getById(idRouteUpdate), dateForFlight);
                    System.out.println(flightController.update(updateFlight));
                    break;
                case "5":
                    System.out.println(idFlightMessage);
                    int idFlightDelete = in.nextInt();
                    System.out.println(idAircraftMessage);
                    int idAircraftDelete = in.nextInt();
                    System.out.println(routeIdMessage);
                    int idRouteDelete = in.nextInt();
                    System.out.println(flightDateMessage);
                    String flightDateDelete = in.next();
                    Flight deleteFlight = new Flight(idFlightDelete, aircraftView.aircraftController.getById(idAircraftDelete),
                            routeView.routeController.getById(idAircraftDelete), flightDateDelete);
                    flightController.deleteByObject(deleteFlight);
                    break;
                case "6":
                    System.out.println(idFlightMessage);
                    int idDelete = in.nextInt();
                    flightController.delete(idDelete);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
