package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.implementators.UserRoleServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserRoleController {

    private UserRoleService userRoleService;

    public UserRoleController() {
        this.userRoleService = new UserRoleServiceImpl();
    }

    public List<UserRole> getAllUserRoles() throws SQLException {
        return userRoleService.getAllUserRoles();
    }
}
