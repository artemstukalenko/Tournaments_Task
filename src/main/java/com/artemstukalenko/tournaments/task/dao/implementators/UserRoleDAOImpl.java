package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.UserRoleDAO;
import com.artemstukalenko.tournaments.task.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public class UserRoleDAOImpl implements UserRoleDAO {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public List<UserRole> getAllUserRoles() {
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
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return null;
    }
}
