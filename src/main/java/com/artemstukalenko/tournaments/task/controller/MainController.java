package com.artemstukalenko.tournaments.task.controller;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class MainController extends Controller {

    private UserRoleController userRoleController;
    private UserController userController;

    @Override
    public void processUser() {
        System.out.println(GREETING);

        listenToInput();

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
        }
    }

    public MainController() {
        this.userRoleController = new UserRoleController();
        this.userController = new UserController();
    }
}
