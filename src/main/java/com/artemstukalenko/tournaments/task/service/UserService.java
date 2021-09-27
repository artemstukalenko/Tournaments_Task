package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.entity.UserRole;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws SQLException;

    boolean addNewUser(User userToAdd);

}
