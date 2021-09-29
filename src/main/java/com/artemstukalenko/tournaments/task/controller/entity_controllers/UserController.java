package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserRoleServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.SQLException;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class UserController extends EntityController {

    private UserService userService;
    private UserRoleService userRoleService;

    public UserController() {
        this.userService = new UserServiceImpl();
        this.userRoleService = new UserRoleServiceImpl();
    }

    @Override
    protected void processEntityAddition() {
        if (userService.addNewUser(constructNewUser())) {
            System.out.println(ENTITY_ADDED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    private String listenToInputForPassword() {

        while (scanner.hasNext()) {
            String currentInput = scanner.next();

            if(currentInput.length() >= 4) {
                return currentInput;
            } else {
                System.out.println(PASSWORD_LENGTH_MESSAGE);
            }
        }

        return "";
    }

    @Override
    protected void processEntityDeletion() {
        System.out.println(DELETE_BY_ID);

        if (userService.deleteUserById(listenToInputForID())) {
            System.out.println(ENTITY_DELETED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityUpdate() {
        System.out.println(UPDATE_ENTITY_REQUEST);

        int userToUpdateId = listenToInputForID();

        System.out.println(UPDATE_ENTITY_OBJECT + userService.findUserById(userToUpdateId));

        User updatedUser = constructNewUser();

        if (userService.updateUser(userToUpdateId, updatedUser)) {
            System.out.println(UPDATED_SUCCESSFULLY + userService.findUserById(userToUpdateId));
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }

    }

    @Override
    protected void readAll() {
        System.out.println(userService.getAllUsers());
    }

    private User constructNewUser() {
        System.out.println(ROLE_FOR_NEW_USER);
        UserRole newUsersRole = userRoleService.findRoleById(listenToInputForID());

        System.out.println(NAME_FOR_NEW_USER);
        String newUsersName = listenToInputForString();

        System.out.println(USERNAME_FOR_NEW_USER);
        String newUsersUsername = listenToInputForString();

        System.out.println(PASSWORD_FOR_NEW_USER);
        String newUsersPassword = listenToInputForPassword();

        return new User (newUsersRole, newUsersName, newUsersUsername, newUsersPassword);
    }

}
