package com.aviatickets.view;


import com.aviatickets.controller.RouteController;
import com.aviatickets.model.Route;

import java.util.Scanner;

public class RouteView {
    RouteController routeController;

    Scanner in;

    private final String startMessage = "Enter 1, if you want save values into route\n" +
            "Enter 2, if you want get by ID data from route\n" +
            "Enter 3, if you want get all data from route\n" +
            "Enter 4, if you want update values into route\n" +
            "Enter 5, if you want delete by object values from route\n" +
            "Enter 6, if you want delete by Id values from route\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String nameMessage = "Input name";
    private final String idRouteMessage = "Input route Id";

    private final String routeAirportMessage = "Input 1, if if you want save values into route_airports\n" +
            "Input 2, if if you want delete values from route_airports\n" +
            "Input 'exit', if if you want exit\n";
    private final String airportIdMessage = "Input airport Id";
    private final String airportTypeMessage = "Input airport type";

    public RouteView() {
        routeController = new RouteController();
    }

    public void CRUDRoute() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(idRouteMessage);
                    int idRoute = in.nextInt();
                    System.out.println(nameMessage);
                    String nameRoute = in.next();
                    Route route = new Route(idRoute, nameRoute);
                    System.out.println(routeController.update(route));
                    break;
                case "2":
                    System.out.println(idRouteMessage);
                    int idAdd = in.nextInt();
                    System.out.println(routeController.getById(idAdd));
                    break;
                case "3":
                    System.out.println(routeController.getAll());
                    break;
                case "4":
                    System.out.println(idRouteMessage);
                    int id = in.nextInt();
                    System.out.println(nameMessage);
                    String name = in.next();
                    Route updateRoute = new Route(id, name);
                    System.out.println(routeController.update(updateRoute));
                    break;
                case "5":
                    System.out.println(idRouteMessage);
                    int idDelete = in.nextInt();
                    System.out.println(nameMessage);
                    String nameDelete = in.next();
                    Route deleteRoute = new Route(idDelete, nameDelete);
                    routeController.deleteByObject(deleteRoute);
                    break;
                case "6":
                    System.out.println(idRouteMessage);
                    int idDeleteRoute = in.nextInt();
                    routeController.delete(idDeleteRoute);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }

    public void CRRouteAirport() {
        String choice;
        do {
            System.out.println(routeAirportMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(idRouteMessage);
                    int idRoute = in.nextInt();
                    System.out.println(airportIdMessage);
                    int idAirport = in.nextInt();
                    System.out.println(airportTypeMessage);
                    String airportType = in.next();
                    routeController.addRouteAirport(idRoute, idAirport, airportType);
                    break;
                case "2":
                    System.out.println(idRouteMessage);
                    int route = in.nextInt();
                    System.out.println(airportIdMessage);
                    int airport = in.nextInt();
                    System.out.println(airportTypeMessage);
                    String type = in.next();
                    routeController.addRouteAirport(route, airport, type);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
