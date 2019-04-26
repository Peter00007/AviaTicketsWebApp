package com.aviatickets.view;


import com.aviatickets.controller.AircraftController;

import java.util.Scanner;

public class AircraftView {
    AircraftController aircraftController;
    Scanner in;

    private final String startMessage = "Enter 1, if you want create values into aircrafr\n" +
            "Enter 2, if you want read data with aircrafr\n" +
            "Enter 3, if you want update values into aircrafr\n" +
            "Enter 4, if you want delete values from aircrafr\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String nameMessage = "Input name";
    private final String idAircraftMessage = "Input aircraft Id";

    public AircraftView() {
        aircraftController = new AircraftController();
        in = new Scanner(System.in);
    }

    public void CRUDAircraft() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(nameMessage);
                    String name = in.next();
                    System.out.println(aircraftController.insert(name));
                    break;
                case "2":
                    System.out.println(aircraftController.read());
                    break;
                case "3":
                    System.out.println(idAircraftMessage);
                    int id = in.nextInt();
                    System.out.println(nameMessage);
                    String nameAircraft = in.next();
                    System.out.println(aircraftController.update(id, nameAircraft));
                    break;
                case "4":
                    System.out.println(idAircraftMessage);
                    int identifier = in.nextInt();
                    aircraftController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
