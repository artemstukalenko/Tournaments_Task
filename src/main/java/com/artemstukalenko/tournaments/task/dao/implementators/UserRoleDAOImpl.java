package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.UserRoleDAO;
import com.artemstukalenko.tournaments.task.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public class UserRoleDAOImpl implements UserRoleDAO, ConnectionCloser {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public List<UserRole> getAllUserRoles() throws SQLException {
        List<UserRole> allUserRoles = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String statementForGettingAllUserRoles = "select * from usersroles";
            statement = connection.prepareStatement(statementForGettingAllUserRoles);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("role_id");
                String roleName = resultSet.getString("role_name");

                allUserRoles.add(new UserRole(id, roleName));
            }

            return allUserRoles;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public UserRole findRoleById(int roleId) throws SQLException {
        UserRole soughtRole = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String statementForGettingUserById = "select * from usersroles where role_id = ?";
            statement = connection.prepareStatement(statementForGettingUserById);
            statement.setInt(1, roleId);
            resultSet = statement.executeQuery();

            if (resultSet != null) {
                resultSet.next();
                int foundRoleId = resultSet.getInt("role_id");
                String foundRoleName = resultSet.getString("role_name");

                soughtRole = new UserRole(foundRoleId, foundRoleName);
            }

            return soughtRole;
        } finally {
            close(connection, statement, resultSet);
        }
    }
}
