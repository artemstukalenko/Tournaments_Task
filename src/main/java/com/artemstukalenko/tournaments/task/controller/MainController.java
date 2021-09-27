package com.artemstukalenko.tournaments.task.controller;

import java.sql.SQLException;

public class MainController {

    private UserRoleController userRoleController;
    private UserController userController;

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
