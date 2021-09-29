package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.EntityDAO;
import com.artemstukalenko.tournaments.task.dao.TeamPlayerDAO;
import com.artemstukalenko.tournaments.task.entity.Entity;
import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.TeamPlayer;
import com.artemstukalenko.tournaments.task.service.PlayerService;
import com.artemstukalenko.tournaments.task.service.TeamService;
import com.artemstukalenko.tournaments.task.service.implementators.PlayerServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.TeamServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamPlayerDAOImpl extends EntityDAO implements TeamPlayerDAO {

    private TeamService teamService = new TeamServiceImpl();
    private PlayerService playerService = new PlayerServiceImpl();

    @Override
    public List<TeamPlayer> getAllTeamPlayers() throws SQLException {
        List<TeamPlayer> allTeamPlayers = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllTeamPlayers = "select * from team_players";
            statement = connection.prepareStatement(statementForGettingAllTeamPlayers);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allTeamPlayers.add(constructNewEntity());
            }

            return allTeamPlayers;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public TeamPlayer findTeamPlayerById(int teamPlayerId) throws SQLException {
        TeamPlayer soughtTeamPlayer = null;

        try {
            setConnectionWithNoAutoCommit();
            String statementForFindingTeamPlayerById = "select * from team_players where tp_id = ?";
            statement = connection.prepareStatement(statementForFindingTeamPlayerById);
            statement.setInt(1, teamPlayerId);
            resultSet = statement.executeQuery();

            if (resultSet != null) {
                resultSet.next();
                soughtTeamPlayer = constructNewEntity();
            }

            return soughtTeamPlayer;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addNewTeamPlayer(TeamPlayer teamPlayerToAdd) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForAddingTeamPlayer = "insert into team_players (team_id, player_id) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(statementForAddingTeamPlayer);
            setValuesToStatementFromObject(teamPlayerToAdd);

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
    public boolean deleteTeamPlayerById(int teamPlayerId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingTeamPlayerById =
                    "delete from team_players where tp_id = ?";
            statement = connection.prepareStatement(statementForDeletingTeamPlayerById);
            statement.setInt(1, teamPlayerId);

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
    public boolean updateTeamPlayerInDB(int teamPlayerToUpdate, TeamPlayer updatedTeamPlayer) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForUpdatingTeamPlayer = "update team_players set team_id = ?, " +
                    "player_id = ? where tp_id = ?";
            statement = connection.prepareStatement(statementForUpdatingTeamPlayer);
            setValuesToStatementFromObject(updatedTeamPlayer);
            statement.setInt(3, teamPlayerToUpdate);
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
    protected TeamPlayer constructNewEntity() throws SQLException {
        int id = resultSet.getInt("tp_id");
        Team team = teamService.findTeamById(resultSet.getInt("team_id"));
        Player player = playerService.findPlayerById(resultSet.getInt("player_id"));

        return new TeamPlayer(id, team, player);
    }

    @Override
    protected void setValuesToStatementFromObject(Entity entity) throws SQLException {
        TeamPlayer teamPlayer = (TeamPlayer) entity;

        statement.setInt(1, teamPlayer.getTeam().getTeamId());
        statement.setInt(2, teamPlayer.getPlayer().getId());
    }
}
