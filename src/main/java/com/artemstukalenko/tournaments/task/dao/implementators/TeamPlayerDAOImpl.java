package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.TeamPlayerDAO;
import com.artemstukalenko.tournaments.task.entity.TeamPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TeamPlayerDAOImpl implements TeamPlayerDAO, ConnectionCloser {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @Override
    public List<TeamPlayer> getAllTeamPlayers() throws SQLException {
        return null;
    }

    @Override
    public TeamPlayer findTeamPlayerById(int teamPlayerId) throws SQLException {
        return null;
    }

    @Override
    public boolean addNewTeamPlayer(TeamPlayer teamPlayerToAdd) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteTeamPlayerById(int teamPlayerId) throws SQLException {
        return false;
    }

    @Override
    public boolean updateTeamPlayerInDB(int teamPlayerToUpdate, TeamPlayer updatedTeamPlayer) throws SQLException {
        return false;
    }
}
