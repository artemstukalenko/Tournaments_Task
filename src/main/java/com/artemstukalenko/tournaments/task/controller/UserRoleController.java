package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.implementators.UserRoleServiceImpl;

import java.sql.SQLException;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class UserRoleController extends Controller {

    private UserRoleService userRoleService;

    public UserRoleController() {
        this.userRoleService = new UserRoleServiceImpl();
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
            case SHOW_ALL_ROLES:
                showAllUserRoles();
                break;
        }
    }

    @Override
    protected void setUserCommand(String input) {
        switch (input) {
            case "R":
                userCommand = UserChoice.SHOW_ALL_ROLES;
                break;
        }
    }

    public void showAllUserRoles() {
        try {
            System.out.println(userRoleService.getAllUserRoles());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
