package com.artemstukalenko.tournaments.task;

import com.artemstukalenko.tournaments.task.controller.MainController;

public class TournamentsConsoleAppMain {
    public static void main(String[] args) {
        MainController controller1 = new MainController();
        controller1.showAllUserRoles();
        System.out.println("--------------");
        controller1.showAllUsers();
    }
}
