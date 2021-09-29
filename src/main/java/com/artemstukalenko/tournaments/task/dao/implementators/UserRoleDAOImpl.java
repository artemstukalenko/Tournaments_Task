package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.EntityDAO;
import com.artemstukalenko.tournaments.task.dao.UserRoleDAO;
import com.artemstukalenko.tournaments.task.entity.Entity;
import com.artemstukalenko.tournaments.task.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAOImpl extends EntityDAO implements UserRoleDAO {

    @Override
    public List<UserRole> getAllUserRoles() throws SQLException {
        List<UserRole> allUserRoles = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
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
            setConnectionWithNoAutoCommit();
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

    @Override
    public boolean addNewRole(UserRole roleToAdd) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForAddingRole = "insert into usersroles (role_name) values (?)";
            statement = connection.prepareStatement(statementForAddingRole);
            statement.setString(1, roleToAdd.getRoleName());

            statement.executeUpdate();
            connection.commit();

            return true;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean deleteRoleById(int roleId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingRole = "delete from usersroles where role_id = ?";
            statement = connection.prepareStatement(statementForDeletingRole);
            statement.setInt(1, roleId);

            statement.executeUpdate();
            connection.commit();

            return true;
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean updateRoleInDB(int roleToUpdate, UserRole updatedRole) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForRoleUpdate = "update usersroles set role_name = ? where role_id = ?";
            statement = connection.prepareStatement(statementForRoleUpdate);
            statement.setString(1, updatedRole.getRoleName());
            statement.setInt(2, roleToUpdate);
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
    protected void setValuesToStatementFromObject(Entity entity) throws SQLException {
        UserRole userRole = (UserRole) entity;

        statement.setInt(1, userRole.getRoleId());
        statement.setString(2, userRole.getRoleName());
    }

    @Override
    protected Entity constructNewEntity() throws SQLException {
        int id = resultSet.getInt("role_id");
        String name = resultSet.getString("role_name");

        return new UserRole(id, name);
    }
}
