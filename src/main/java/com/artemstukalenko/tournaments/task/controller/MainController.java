package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.controller.entity_controllers.PlayerController;
import com.artemstukalenko.tournaments.task.controller.entity_controllers.UserController;
import com.artemstukalenko.tournaments.task.controller.entity_controllers.UserRoleController;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class MainController extends Controller {

    private UserRoleController userRoleController;
    private UserController userController;
    private PlayerController playerController;

    public MainController() {
        this.userRoleController = new UserRoleController();
        this.userController = new UserController();
        this.playerController = new PlayerController();
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
        }
    }
}
