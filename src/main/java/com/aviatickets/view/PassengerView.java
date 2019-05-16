package com.aviatickets.view;


import com.aviatickets.controller.PassengerController;
import com.aviatickets.model.Passenger;

import java.util.Scanner;

public class PassengerView {
    PassengerController passengerController;

    Scanner in;

    private final String startMessage = "Enter 1, if you want save values into passenger\n" +
            "Enter 2, if you want get by ID data with passenger\n" +
            "Enter 3, if you want get all data with passenger\n" +
            "Enter 4, if you want update values into passenger\n" +
            "Enter 5, if you want delete by object values from passenger\n" +
            "Enter 6, if you want delete by Id values from passenger\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String idPassengerMessage = "Input id passenger";
    private final String firstNameMessage = "Input first name passengers";
    private final String lastNameMessage = "Input last name passengers";
    private final String birthdayMessage = "Input birthday passengers in format 'yyyy-mm-dd' please";

    public PassengerView() {
        passengerController = new PassengerController();
        in = new Scanner(System.in);
    }

    public void CRUDPassenger() {
        String choice;
        do {
            System.out.println(startMessage);
            choice = in.next();
            switch (choice) {
                case "1":
                    System.out.println(firstNameMessage);
                    String firstName = in.next();
                    System.out.println(lastNameMessage);
                    String lastName = in.next();
                    System.out.println(birthdayMessage);
                    String birthday = in.next();
                    Passenger passenger = new Passenger(firstName, lastName, birthday);
                    System.out.println(passengerController.save(passenger));
                    break;
                case "2":
                    System.out.println(idPassengerMessage);
                    int getIdPassenger = in.nextInt();
                    System.out.println(passengerController.getById(getIdPassenger));
                    break;
                case "3":
                    for (Passenger passengers : passengerController.getAll()) {
                        System.out.println(passengers);
                    }
                    break;
                case "4":
                    System.out.println(idPassengerMessage);
                    int id = in.nextInt();
                    System.out.println(firstNameMessage);
                    String fName = in.next();
                    System.out.println(lastNameMessage);
                    String lName = in.next();
                    System.out.println(birthdayMessage);
                    String birth = in.next();
                    Passenger updatePassenger = new Passenger(id, fName, lName, birth);
                    System.out.println(passengerController.update(updatePassenger));
                    break;
                case "5":
                    System.out.println(idPassengerMessage);
                    int idDelete = in.nextInt();
                    System.out.println(firstNameMessage);
                    String fNameDelete = in.next();
                    System.out.println(lastNameMessage);
                    String lNameDelete = in.next();
                    System.out.println(birthdayMessage);
                    String birthDelete = in.next();
                    Passenger passengerDelete = new Passenger(idDelete, fNameDelete, lNameDelete, birthDelete);
                    passengerController.deleteByObject(passengerDelete);
                    break;
                case "6":
                    System.out.println(idPassengerMessage);
                    int idPassengerDelete = in.nextInt();
                    passengerController.delete(idPassengerDelete);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
