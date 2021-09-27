package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.UserDAO;
import com.artemstukalenko.tournaments.task.entity.User;

import javax.sql.DataSource;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private DataSource dataSource;

    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }
}
