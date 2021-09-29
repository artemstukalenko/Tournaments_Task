package com.artemstukalenko.tournaments.task;

import com.artemstukalenko.tournaments.task.controller.Controller;
import com.artemstukalenko.tournaments.task.controller.MainController;

public class TournamentsConsoleAppMain {
    public static void main(String[] args) {
        Controller controller = new MainController();
        controller.processUser();
    }
}
