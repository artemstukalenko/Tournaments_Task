package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UserRoleDAO {

    public List<UserRole> getAllUserRoles() throws SQLException;

    UserRole findRoleById(int roleId) throws SQLException;
}
