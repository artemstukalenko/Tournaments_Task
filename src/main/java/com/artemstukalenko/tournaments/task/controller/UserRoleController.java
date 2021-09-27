package com.artemstukalenko.tournaments.task.controller;

import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.implementators.UserRoleServiceImpl;
import java.util.List;

public class UserRoleController {

    private UserRoleService userRoleService;

    public UserRoleController() {
        this.userRoleService = new UserRoleServiceImpl();
    }

    public List<UserRole> showAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }
}
