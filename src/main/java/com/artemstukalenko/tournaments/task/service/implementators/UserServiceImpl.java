package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.UserDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.UserDAOImpl;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.exception.CouldNotInteractWithEntityException;
import com.artemstukalenko.tournaments.task.exception.EntityNotFoundException;
import com.artemstukalenko.tournaments.task.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean addNewUser(User userToAdd) {
        try {
            return userDAO.addNewUser(userToAdd);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUserById(int userId) {
        try {
            return userDAO.deleteUserById(userId);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public User findUserById(int userId) {
        try {
            return userDAO.findUserById(userId);
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateUser(int userToUpdateId, User updatedUserObject) {
        try {
            return userDAO.updateUser(userToUpdateId, updatedUserObject);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }
}
