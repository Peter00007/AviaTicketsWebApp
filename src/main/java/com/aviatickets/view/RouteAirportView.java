package com.aviatickets.view;


import com.aviatickets.controller.RouteAirportController;

import java.util.Scanner;

public class RouteAirportView {
    RouteAirportController routeAirportController;

    Scanner in;

    private final String startMessage = "Enter 1, if you want create values into route_airports\n" +
            "Enter 2, if you want read data with route_airports\n" +
            "Enter 3, if you want delete values from route_airports\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String idAirportMessage = "Input Id Airport";
    private final String idRouteMessage = "Input  Id Route";
    private final String airportTypeMessage = "Input airport type";

    public RouteAirportView() {
        routeAirportController = new RouteAirportController();
        in = new Scanner(System.in);
    }

    public void CRUDRouteAirport() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(idRouteMessage);
                    int idRoute = in.nextInt();
                    System.out.println(idAirportMessage);
                    int idAirport = in.nextInt();
                    System.out.println(airportTypeMessage);
                    String airportType = in.next();
                    System.out.println(routeAirportController.insert(idRoute, idAirport, airportType));
                    break;
                case "2":
                    System.out.println(routeAirportController.read());
                    break;
                case "3":
                    System.out.println(idRouteMessage);
                    int routeId = in.nextInt();
                    System.out.println(idAirportMessage);
                    int airportId = in.nextInt();
                    System.out.println(airportTypeMessage);
                    String typeOfAir = in.next();
                    routeAirportController.delete(routeId, airportId, typeOfAir);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
