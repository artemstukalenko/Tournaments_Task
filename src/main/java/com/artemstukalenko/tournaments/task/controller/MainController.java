package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.controller.entity_controllers.*;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class MainController extends Controller {

    private UserRoleController userRoleController;
    private UserController userController;
    private PlayerController playerController;
    private TeamController teamController;
    private TeamPlayerController teamPlayerController;

    public MainController() {
        this.userRoleController = new UserRoleController();
        this.userController = new UserController();
        this.playerController = new PlayerController();
        this.teamController = new TeamController();
        this.teamPlayerController = new TeamPlayerController();
    }

    @Override
    public void processUser() {
        System.out.println(GREETING);

        listenToInputCommand();

        responseToCommand();
    }

    @Override
    protected void responseToCommand() {
        switch (userCommand) {
            case WORK_WITH_ROLES:
                userRoleController.processUser();
                break;
            case WORK_WITH_USERS:
                userController.processUser();
                break;
            case WORK_WITH_PLAYERS:
                playerController.processUser();
                break;
            case WORK_WITH_TEAMS:
                teamController.processUser();
                break;
            case WORK_WITH_TEAMPLAYERS:
                teamPlayerController.processUser();
                break;
        }
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

        }
    }
}
