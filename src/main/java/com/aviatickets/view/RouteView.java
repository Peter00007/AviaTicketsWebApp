package com.aviatickets.view;


import com.aviatickets.controller.RouteController;

import java.util.Scanner;

public class RouteView {
    RouteController routeController;

    Scanner in;

    private final String startMessage = "Enter 1, if you want create values into routes\n" +
            "Enter 2, if you want read data with routes\n" +
            "Enter 3, if you want update values into routes\n" +
            "Enter 4, if you want delete values from routes\n" +
            "Enter 'Exit', if you want exit from this menu";
    private final String nameMessage = "Input name";
    private final String idRouteMessage = "Input route Id";

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
                    System.out.println(nameMessage);
                    String name = in.next();
                    System.out.println(routeController.insert(name));
                    break;
                case "2":
                    System.out.println(routeController.read());
                    break;
                case "3":
                    System.out.println(idRouteMessage);
                    int id = in.nextInt();
                    System.out.println(nameMessage);
                    String nameRoute = in.next();
                    System.out.println(routeController.update(id, nameRoute));
                    break;
                case "4":
                    System.out.println(idRouteMessage);
                    int identifier = in.nextInt();
                    routeController.delete(identifier);
                    break;
            }
        } while (!choice.equalsIgnoreCase("Exit"));
    }
}
