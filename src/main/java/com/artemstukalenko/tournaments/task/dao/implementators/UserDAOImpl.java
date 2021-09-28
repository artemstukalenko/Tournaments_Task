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
                allUsers.add(constructUserFromDBData());
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
            setValuesToStatementFromObject(userToAdd);

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

    @Override
    public User findUserById(int userId) throws SQLException {
        User soughtUser = null;

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingUserById = "select * from users where user_id = ?";
            statement = connection.prepareStatement(statementForGettingUserById);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();

            if (resultSet != null) {
                resultSet.next();

                soughtUser = constructUserFromDBData();
            }

            return soughtUser;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
     public boolean updateUser(int userToUpdateId,
                               User updatedUserObject) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementToUpdateUser = "update users set role_id = ?, name = ?, user_name = ?, " +
                    "password = ?, is_admin = ? where user_id = ?";
            statement = connection.prepareStatement(statementToUpdateUser);
            setValuesToStatementFromObject(updatedUserObject);
            statement.setInt(6, userToUpdateId);
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

    private void setValuesToStatementFromObject(User user) throws SQLException {
        statement.setInt(1, user.getUserRole().getRoleId());
        statement.setString(2, user.getName());
        statement.setString(3, user.getUsername());
        statement.setString(4, user.getPassword());
        statement.setBoolean(5, user.isAdmin());
    }

    private void setConnectionWithNoAutoCommit() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        connection.setAutoCommit(false);
    }

    private User constructUserFromDBData() throws SQLException {
        int foundUserId = resultSet.getInt("user_id");
        UserRole foundRole = userRoleService.findRoleById(resultSet.getInt("role_id"));
        String foundName = resultSet.getString("name");
        String foundUsername = resultSet.getString("user_name");
        String foundPassword = resultSet.getString("password");
        boolean isAdmin = resultSet.getBoolean("is_admin");

        return new User(foundUserId, foundRole, foundName, foundUsername,
                foundPassword, isAdmin);
    }
}
