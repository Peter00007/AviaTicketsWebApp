package com.aviatickets.view;


import com.aviatickets.controller.AircraftController;
import com.aviatickets.model.Aircraft;

import java.util.Scanner;

public class AircraftView {
    AircraftController aircraftController;
    Scanner in;

    private final String startMessage = "Enter 1, if you want save values into aircraft\n" +
            "Enter 2, if you want get by ID data with aircraft\n" +
            "Enter 3, if you want get all data with aircraft\n" +
            "Enter 4, if you want update values into aircraft\n" +
            "Enter 5, if you want delete by object values from aircraft\n" +
            "Enter 6, if you want delete by Id values from aircraft\n" +
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
                    System.out.println(idAircraftMessage);
                    int idAircraft = in.nextInt();
                    System.out.println(nameMessage);
                    String name = in.next();
                    Aircraft aircraft = new Aircraft(idAircraft, name);
                    System.out.println(aircraftController.save(aircraft));
                    break;
                case "2":
                    System.out.println(idAircraftMessage);
                    int idAir = in.nextInt();
                    System.out.println(aircraftController.getById(idAir));
                    break;
                case "3":
                    System.out.println(aircraftController.getAll());
                    break;
                case "4":
                    System.out.println(idAircraftMessage);
                    int identifier = in.nextInt();
                    System.out.println(nameMessage);
                    String newName = in.next();
                    Aircraft air = new Aircraft(identifier, newName);
                    System.out.println(aircraftController.update(air));
                    break;
                case "5":
                    System.out.println(idAircraftMessage);
                    int idDelete = in.nextInt();
                    System.out.println(nameMessage);
                    String nameDelete = in.next();
                    Aircraft deleteAircraft = new Aircraft(idDelete, nameDelete);
                    aircraftController.deleteByObject(deleteAircraft);
                    break;
                case "6":
                    System.out.println(idAircraftMessage);
                    int deleteById = in.nextInt();
                    aircraftController.delete(deleteById);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
