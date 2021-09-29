package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.controller.entity_controllers.*;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class MainController extends Controller {

    private EntityController entityController;

    boolean continueCommand = true;

    @Override
    public void processUser() {

        while (continueCommand) {
            System.out.println(GREETING);
            
            listenToInputCommand();

            responseToCommand();

            System.out.println(ASK_FOR_CONTINUE);

            askForContinueCommand();
        }
    }

    @Override
    protected void responseToCommand() {
        switch (userCommand) {
            case WORK_WITH_ROLES:
                entityController = new UserRoleController();
                break;
            case WORK_WITH_USERS:
                entityController = new UserController();
                break;
            case WORK_WITH_PLAYERS:
                entityController = new PlayerController();
                break;
            case WORK_WITH_TEAMS:
                entityController = new TeamController();
                break;
            case WORK_WITH_TEAMPLAYERS:
                entityController = new TeamPlayerController();
                break;
            case WORK_WITH_TOURNAMENTS:
                entityController = new TournamentController();
                break;
            case WORK_WITH_SCHEDULES:
                entityController = new ScheduleController();
                break;
        }

        entityController.processUser();
    }

    @Override
    protected void setUserCommand(String input) {
        switch (input) {
            case "R":
                userCommand = UserChoice.WORK_WITH_ROLES;
                break;
            case "U":
                userCommand = UserChoice.WORK_WITH_USERS;
                break;
            case "P":
                userCommand = UserChoice.WORK_WITH_PLAYERS;
                break;
            case "T":
                userCommand = UserChoice.WORK_WITH_TEAMS;
                break;
            case "TP":
                userCommand = UserChoice.WORK_WITH_TEAMPLAYERS;
                break;
            case "TR":
                userCommand = UserChoice.WORK_WITH_TOURNAMENTS;
                break;
            case "S":
                userCommand = UserChoice.WORK_WITH_SCHEDULES;
                break;
        }
    }

    private void askForContinueCommand() {

        while (scanner.hasNext()) {
            String input = scanner.next();

            if(input.equalsIgnoreCase("Y")) {
                continueCommand = true;
                break;
            } else if (input.equalsIgnoreCase("N")) {
                continueCommand = false;
                break;
            } else {
                System.out.println(WRONG_INPUT);
            }

        }

    }
}
