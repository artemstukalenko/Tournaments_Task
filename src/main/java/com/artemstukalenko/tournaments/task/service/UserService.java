package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    public List<User> getAllUsers() throws SQLException;

}
