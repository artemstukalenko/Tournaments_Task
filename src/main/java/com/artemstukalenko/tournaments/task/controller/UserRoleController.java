package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.implementators.UserRoleServiceImpl;

import java.sql.SQLException;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;
import static com.artemstukalenko.tournaments.task.controller.RegexContainer.*;

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
            case SHOW_ALL:
                showAllUserRoles();
                break;
            case ADD_NEW_ENTITY:
                System.out.println(ENTER_NAME_FOR_NEW_ENTITY);

                if(userRoleService.addNewRole(constructNewRole())) {
                    System.out.println(ENTITY_ADDED);
                } else {
                    System.out.println(UNEXPECTED_ERROR_OCCURRED);
                }

                break;
        }
    }

    private UserRole constructNewRole() {
        UserRole newRole = null;

        while (scanner.hasNext()) {
            String roleName = scanner.next();

            if(roleName.matches(ONLY_LETTERS)) {
                newRole = new UserRole(roleName);
                break;
            } else {
                System.out.println(WRONG_INPUT);
            }
        }

        return newRole;
    }

    @Override
    protected void setUserCommand(String input) {
        switch (input) {
            case "R":
                userCommand = UserChoice.SHOW_ALL;
                break;
            case "A":
                userCommand = UserChoice.ADD_NEW_ENTITY;
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
