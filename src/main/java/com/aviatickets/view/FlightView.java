package com.aviatickets.view;


import com.aviatickets.controller.FlightController;
import com.aviatickets.model.Flight;

import java.util.Scanner;

public class FlightView {
    FlightController flightController;
    private final String firstDateMessage = "Input min departude date in format 'yyyy-mm-dd' please";
    private final String lastDateMessage = "Input max departude date in format 'yyyy-mm-dd' please";
    private final String depAirportMessage = "Input departure airport name";
    private final String arrAirportMessage = "Input arrival airport name";

    private final String startMessage = "Enter 1, if you want create values into flights\n" +
            "Enter 2, if you want read data with flights\n" +
            "Enter 3, if you want update values into flights\n" +
            "Enter 4, if you want delete values from flights\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String idAircraftMessage = "Input Id aircraft";
    private final String idFlightMessage = "Input Id flight";
    private final String flightDateMessage = "Input  flight date";

    Scanner in;

    public FlightView() {
        flightController = new FlightController();
        in = new Scanner(System.in);
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
                    System.out.println(flightDateMessage);
                    String dateFlight = in.next();
                    System.out.println(flightController.insert(idAircraft, dateFlight));
                    break;
                case "2":
                    System.out.println(flightController.read());
                    break;
                case "3":
                    System.out.println(idFlightMessage);
                    int idFlight = in.nextInt();
                    System.out.println(idAircraftMessage);
                    int idAir = in.nextInt();
                    System.out.println(flightDateMessage);
                    String dateFl = in.next();
                    System.out.println(flightController.update(idFlight, idAir, dateFl));
                    break;
                case "4":
                    System.out.println(idFlightMessage);
                    int identifier = in.nextInt();
                    flightController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
