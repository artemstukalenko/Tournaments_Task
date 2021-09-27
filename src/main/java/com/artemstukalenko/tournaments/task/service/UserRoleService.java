package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UserRoleService {

    public List<UserRole> getAllUserRoles() throws SQLException;

    UserRole findRoleById(int roleId) throws SQLException;

    boolean addNewRole(UserRole roleToAdd);
}
