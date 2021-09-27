package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.UserDAO;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.implementators.UserRoleServiceImpl;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public class UserDAOImpl implements UserDAO, ConnectionCloser {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;
    private UserRoleService userRoleService;

    public UserDAOImpl() {
        this.userRoleService = new UserRoleServiceImpl();
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String statementForGettingAllUsers = "select * from users";
            statement = connection.prepareStatement(statementForGettingAllUsers);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                UserRole userRole = userRoleService.findRoleById(resultSet.getInt("role_id"));
                String name = resultSet.getString("name");
                String username = resultSet.getString("user_name");
                String password = resultSet.getString("password");
                boolean isAdmin = resultSet.getBoolean("is_admin");

                allUsers.add(new User(userId, userRole, name, username, password, isAdmin));
            }

            return allUsers;
        } finally {
            close(connection, statement, resultSet);
        }
    }
}
