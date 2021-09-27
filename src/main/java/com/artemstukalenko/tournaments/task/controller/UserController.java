package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserController {

    private UserService userService;

    public UserController() {
        this.userService = new UserServiceImpl();
    }

    public List<User> getAllUsers() throws SQLException {
        return userService.getAllUsers();
    }

}
