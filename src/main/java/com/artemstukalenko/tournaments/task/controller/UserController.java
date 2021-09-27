package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.entity.User;
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
    public void processUser() {
        System.out.println(WHAT_TO_DO_WITH);

        listenToInputCommand();

        responseToCommand();
    }

    @Override
    protected void processEntityAddition() {
        User newUser = new User();

        System.out.println(ROLE_FOR_NEW_USER);
        newUser.setUserRole(userRoleService.findRoleById(listenToInputForID()));

        System.out.println(NAME_FOR_NEW_USER);
        newUser.setName(listenToInputForString());

        System.out.println(USERNAME_FOR_NEW_USER);
        newUser.setUsername(listenToInputForString());

        System.out.println(PASSWORD_FOR_NEW_USER);
        newUser.setPassword(listenToInputForPassword());

        userService.addNewUser(newUser);
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
