package com.aviatickets.view;


import com.aviatickets.controller.AirportController;
import com.aviatickets.model.Airport;

import java.util.Scanner;

public class AirportView {
    AirportController airportController;
    Scanner in;

    private final String startMessage = "Enter 1, if you want save values into airports\n" +
            "Enter 2, if you want get by ID data with airports\n" +
            "Enter 3, if you want get all data with airports\n" +
            "Enter 4, if you want update values into airports\n" +
            "Enter 5, if you want delete by object values from airports\n" +
            "Enter 6, if you want delete by Id values from airports\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String nameMessage = "Input name";
    private final String idAirportMessage = "Input airport Id";

    public AirportView() {
        airportController = new AirportController();
        in = new Scanner(System.in);
    }

    public void CRUDAirport() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(idAirportMessage);
                    int id = in.nextInt();
                    System.out.println(nameMessage);
                    String name = in.next();
                    Airport airport = new Airport(id, name);
                    System.out.println(airportController.save(airport));
                    break;
                case "2":
                    System.out.println(idAirportMessage);
                    int idAirport = in.nextInt();
                    System.out.println(airportController.getById(idAirport));
                    break;
                case "3":
                    System.out.println(airportController.getAll());
                    break;
                case "4":
                    System.out.println(idAirportMessage);
                    int identifier = in.nextInt();
                    System.out.println(nameMessage);
                    String nameAirport = in.next();
                    Airport air = new Airport(identifier, nameAirport);
                    airportController.update(air);
                    break;
                case "5":
                    System.out.println(idAirportMessage);
                    int idForDelete = in.nextInt();
                    System.out.println(nameMessage);
                    String nameForDelete = in.next();
                    Airport airForDelete = new Airport(idForDelete, nameForDelete);
                    airportController.deleteByObject(airForDelete);
                    break;
                case "6":
                    System.out.println(idAirportMessage);
                    int idAir = in.nextInt();
                    airportController.delete(idAir);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
