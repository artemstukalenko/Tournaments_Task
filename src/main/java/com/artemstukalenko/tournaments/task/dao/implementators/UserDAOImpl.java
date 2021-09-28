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
            setConnectionWithNoAutoCommit();

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

    @Override
    public boolean addNewUser(User userToAdd) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForAddingNewUser = "insert into users (role_id, name, user_name, password, is_admin)" +
                    "values (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(statementForAddingNewUser);
            statement.setInt(1, userToAdd.getUserRole().getRoleId());
            statement.setString(2, userToAdd.getName());
            statement.setString(3, userToAdd.getUsername());
            statement.setString(4, userToAdd.getPassword());
            statement.setBoolean(5, userToAdd.isAdmin());

            statement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean deleteUserById(int userId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingUser = "delete from users where user_id = ?";
            statement = connection.prepareStatement(statementForDeletingUser);
            statement.setInt(1, userId);

            statement.executeUpdate();
            connection.commit();
            return true;

        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    private void setConnectionWithNoAutoCommit() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        connection.setAutoCommit(false);
    }
}
