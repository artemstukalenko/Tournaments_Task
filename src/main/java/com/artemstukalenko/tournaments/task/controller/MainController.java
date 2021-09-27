package com.artemstukalenko.tournaments.task.controller;

import java.sql.SQLException;
import java.util.Scanner;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class MainController implements UserInputMatcher {

    private Scanner scanner = new Scanner(System.in);
    private UserRoleController userRoleController;
    private UserController userController;
    private UserChoice userCommand;


    public void processUser() {
        System.out.println(GREETING);

        listenToInput();

        switch (userCommand) {
            case SHOW_ALL_ROLES:
                showAllUserRoles();
                break;
            case SHOW_ALL_USERS:
                showAllUsers();
                break;
        }
    }

    private void listenToInput() {

        while (scanner.hasNext()) {
            String currentInput = scanner.next();

            if(inputMatchesCommand(currentInput)) {
                setUserCommand(currentInput.toUpperCase());
                break;
            } else {
                System.out.println(WRONG_INPUT);
            }
        }

    }

    private void setUserCommand(String input) {
        switch (input) {
            case "R":
                userCommand = UserChoice.SHOW_ALL_ROLES;
                break;
            case "U":
                userCommand = UserChoice.SHOW_ALL_USERS;
                break;
        }
    }

    public MainController() {
        this.userRoleController = new UserRoleController();
        this.userController = new UserController();
    }

    public void showAllUserRoles() {
        try {
            System.out.println(userRoleController.getAllUserRoles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showAllUsers() {
        try {
            System.out.println(userController.getAllUsers());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
