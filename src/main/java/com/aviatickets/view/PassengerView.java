package com.aviatickets.view;


import com.aviatickets.controller.PassengerController;

import java.util.Scanner;

public class PassengerView {
    PassengerController passengerController;

    Scanner in;

    private final String startMessage = "Enter 1, if you want create values into passengers\n" +
            "Enter 2, if you want read data with passengers\n" +
            "Enter 3, if you want update values into passengers\n" +
            "Enter 4, if you want delete values from passengers\n" +
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
                    System.out.println(passengerController.insert(firstName, lastName, birthday));
                    break;
                case "2":
                    System.out.println(passengerController.read());
                    break;
                case "3":
                    System.out.println(idPassengerMessage);
                    int id = in.nextInt();
                    System.out.println(firstNameMessage);
                    String fName = in.next();
                    System.out.println(lastNameMessage);
                    String lName = in.next();
                    System.out.println(birthdayMessage);
                    String birth = in.next();
                    System.out.println(passengerController.update(id, fName, lName, birth));
                    break;
                case "4":
                    System.out.println(idPassengerMessage);
                    int identifier = in.nextInt();
                    passengerController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
