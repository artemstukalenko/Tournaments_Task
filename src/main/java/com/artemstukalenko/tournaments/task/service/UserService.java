package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    boolean addNewUser(User userToAdd);

    boolean deleteUserById(int userId);

    User findUserById(int userId);

    boolean updateUser(int userToUpdateId, User updatedUserObject);

    List<User> findUsersByUserRoleId(int userRoleId);
}
