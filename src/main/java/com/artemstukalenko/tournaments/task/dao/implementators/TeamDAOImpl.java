package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.TeamDAO;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public class TeamDAOImpl implements TeamDAO, ConnectionCloser {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private UserService userService = new UserServiceImpl();

    @Override
    public List<Team> getAllTeams() throws SQLException {
        List<Team> allTeams = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllTeams = "select * from teams";
            statement = connection.prepareStatement(statementForGettingAllTeams);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allTeams.add(constructTeam());
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
                soughtTeam = constructTeam();
            }

            return soughtTeam;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addNewTeam(Team teamToAdd) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteTeamById(int teamId) throws SQLException {
        return false;
    }

    @Override
    public boolean updateTeamInDB(int teamToUpdate, Team updatedTeam) throws SQLException {
        return false;
    }

    private void setConnectionWithNoAutoCommit() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        connection.setAutoCommit(false);
    }

    private Team constructTeam() throws SQLException {
        int id = resultSet.getInt("team_id");
        User user = userService.findUserById(resultSet.getInt("user_id"));
        String name = resultSet.getString("team_name");

        return new Team(id, user, name);
    }
}
