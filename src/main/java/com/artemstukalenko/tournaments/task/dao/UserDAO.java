package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    boolean addNewUser(User userToAdd) throws SQLException;

    boolean deleteUserById(int userId) throws SQLException;

    User findUserById(int userId) throws SQLException;

    boolean updateUser(int userToUpdateId, User updatedUserObject) throws SQLException;

    List<User> findUsersByUserRoleId(int userRoleId) throws SQLException;
}
