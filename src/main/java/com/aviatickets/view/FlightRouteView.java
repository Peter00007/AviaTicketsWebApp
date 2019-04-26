package com.aviatickets.view;


import com.aviatickets.controller.FlightRouteController;

import java.util.Scanner;

public class FlightRouteView {
    FlightRouteController flightRouteController;

    Scanner in;

    private final String startMessage = "Enter 1, if you want create values into flight_routes\n" +
            "Enter 2, if you want read data with flight_routes\n" +
            "Enter 3, if you want update values into flight_routes\n" +
            "Enter 4, if you want delete values from flight_routes\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String idFlightMessage = "Input Id Flight";
    private final String idRouteMessage = "Input  Id Route";

    public FlightRouteView() {
        flightRouteController = new FlightRouteController();
        in = new Scanner(System.in);
    }

    public void CRUDFlightRoute() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(idFlightMessage);
                    int idFlight = in.nextInt();
                    System.out.println(idRouteMessage);
                    int idRoute = in.nextInt();
                    System.out.println(flightRouteController.insert(idFlight, idRoute));
                    break;
                case "2":
                    System.out.println(flightRouteController.read());
                    break;
                case "3":
                    System.out.println(idFlightMessage);
                    int idFl = in.nextInt();
                    System.out.println(idRouteMessage);
                    int idRo = in.nextInt();
                    System.out.println(flightRouteController.update(idFl, idRo));
                    break;
                case "4":
                    System.out.println(idFlightMessage);
                    int identifier = in.nextInt();
                    flightRouteController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
