package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.UserDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.UserDAOImpl;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    @Override
    public boolean addNewUser(User userToAdd) {
        try {
            return userDAO.addNewUser(userToAdd);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteUserById(int userId) {
        try {
            return userDAO.deleteUserById(userId);
        } catch (SQLException e) {
            return false;
        }
    }
}
