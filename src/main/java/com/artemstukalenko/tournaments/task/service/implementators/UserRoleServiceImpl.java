package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.UserRoleDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.UserRoleDAOImpl;
import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;

import java.sql.SQLException;
import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleDAO userRoleDAO;

    public UserRoleServiceImpl() {
        this.userRoleDAO = new UserRoleDAOImpl();
    }

    @Override
    public List<UserRole> getAllUserRoles() throws SQLException {
        return userRoleDAO.getAllUserRoles();
    }

    @Override
    public UserRole findRoleById(int roleId) throws SQLException {
        return userRoleDAO.findRoleById(roleId);
    }
}
