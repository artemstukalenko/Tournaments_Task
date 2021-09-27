package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers() throws SQLException;

    boolean addNewUser(User userToAdd) throws SQLException;

}
