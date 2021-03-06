package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.EntityDAO;
import com.artemstukalenko.tournaments.task.dao.TeamDAO;
import com.artemstukalenko.tournaments.task.dao.UserDAO;
import com.artemstukalenko.tournaments.task.entity.Entity;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAOImpl extends EntityDAO implements TeamDAO {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<Team> getAllTeams() throws SQLException {
        List<Team> allTeams = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllTeams = "select * from teams";
            statement = connection.prepareStatement(statementForGettingAllTeams);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allTeams.add(constructNewEntity());
            }

            return allTeams;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public Team findTeamById(int teamId) throws SQLException {
        Team soughtTeam = null;

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingTeamById = "select * from teams where team_id = ?";
            statement = connection.prepareStatement(statementForGettingTeamById);
            statement.setInt(1, teamId);
            resultSet = statement.executeQuery();

            if (resultSet != null) {
                resultSet.next();
                soughtTeam = constructNewEntity();
            }

            return soughtTeam;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addNewTeam(Team teamToAdd) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForAddingTeam = "insert into teams (user_id, team_name) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(statementForAddingTeam);
            setValuesToStatementFromObject(teamToAdd);

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
    public boolean deleteTeamById(int teamId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingTeam = "delete from teams where team_id = ?";
            statement = connection.prepareStatement(statementForDeletingTeam);
            statement.setInt(1, teamId);

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
    public boolean updateTeamInDB(int teamToUpdate, Team updatedTeam) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForUpdatingTeam = "update teams set user_id = ?, team_name = ? " +
                    "where team_id = ?";
            statement = connection.prepareStatement(statementForUpdatingTeam);
            setValuesToStatementFromObject(updatedTeam);
            statement.setInt(3, teamToUpdate);
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
    public boolean deleteTeamByUserId(int userId) throws SQLException {
        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingTournamentByUserId = "delete from teams where user_id = ?";
            statement = connection.prepareStatement(statementForDeletingTournamentByUserId);
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
    protected Team constructNewEntity() throws SQLException {
        int id = resultSet.getInt("team_id");
        User user = userDAO.findUserById(resultSet.getInt("user_id"));
        String name = resultSet.getString("team_name");

        return new Team(id, user, name);
    }

    @Override
    protected void setValuesToStatementFromObject(Entity entity) throws SQLException {
        Team team = (Team) entity;

        statement.setInt(1, team.getUser().getUserId());
        statement.setString(2, team.getTeamName());
    }
}
