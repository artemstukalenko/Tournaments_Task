package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.UserRoleDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.UserRoleDAOImpl;
import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.exception.CouldNotInteractWithEntityException;
import com.artemstukalenko.tournaments.task.exception.EntityNotFoundException;
import com.artemstukalenko.tournaments.task.service.UserRoleService;

import java.sql.SQLException;
import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleDAO userRoleDAO;

    public UserRoleServiceImpl() {
        this.userRoleDAO = new UserRoleDAOImpl();
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        try {
            return userRoleDAO.getAllUserRoles();
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public UserRole findRoleById(int roleId) {
        try {
            return userRoleDAO.findRoleById(roleId);
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean addNewRole(UserRole roleToAdd) {
        try {
            return userRoleDAO.addNewRole(roleToAdd);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean deleteRoleById(int roleId) {
        try {
            return userRoleDAO.deleteRoleById(roleId);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean updateRole(int roleToUpdate, UserRole updatedRole) {
        try {
            return userRoleDAO.updateRoleInDB(roleToUpdate, updatedRole);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }
}
