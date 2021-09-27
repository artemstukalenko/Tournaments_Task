package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.SQLException;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class UserController extends Controller {

    private UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public void processUser() {
        System.out.println(WHAT_TO_DO_WITH);

        listenToInput();

        responseToCommand();
    }

    @Override
    protected void responseToCommand() {
        switch (userCommand) {
            case SHOW_ALL_USERS:
                showAllUsers();
                break;
        }
    }

    @Override
    protected void setUserCommand(String input) {
        switch (input) {
            case "R":
                userCommand = UserChoice.SHOW_ALL_USERS;
                break;
        }
    }

    public void showAllUsers() {

        try {
            System.out.println(userService.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
