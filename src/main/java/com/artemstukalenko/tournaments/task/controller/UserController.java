package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.SQLException;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class UserController extends EntityController {

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
    protected void processEntityAddition() {

    }

    @Override
    protected void processEntityDeletion() {

    }

    @Override
    protected void processEntityUpdate() {

    }

    @Override
    protected void readAll() {

        try {
            System.out.println(userService.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
