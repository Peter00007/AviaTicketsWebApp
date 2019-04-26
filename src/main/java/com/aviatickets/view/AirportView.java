package com.aviatickets.view;


import com.aviatickets.controller.AirportController;

import java.util.Scanner;

public class AirportView {
    AirportController airportController;
    Scanner in;

    private final String startMessage = "Enter 1, if you want create values into airport\n" +
            "Enter 2, if you want read data with airport\n" +
            "Enter 3, if you want update values into airport\n" +
            "Enter 4, if you want delete values from airport\n" +
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
                    System.out.println(nameMessage);
                    String name = in.next();
                    System.out.println(airportController.insert(name));
                    break;
                case "2":
                    System.out.println(airportController.read());
                    break;
                case "3":
                    System.out.println(idAirportMessage);
                    int id = in.nextInt();
                    System.out.println(nameMessage);
                    String nameAirport = in.next();
                    System.out.println(airportController.update(id, nameAirport));
                    break;
                case "4":
                    System.out.println(idAirportMessage);
                    int identifier = in.nextInt();
                    airportController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
