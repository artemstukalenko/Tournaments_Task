package com.artemstukalenko.tournaments.task.controller;

public class MainController {

    private UserRoleController userRoleController;

    public MainController() {
        this.userRoleController = new UserRoleController();
    }

    public void showAllUserRoles() {
        System.out.println(userRoleController.showAllUserRoles());
    }

}
