package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.EntityDAO;
import com.artemstukalenko.tournaments.task.dao.PlayerDAO;
import com.artemstukalenko.tournaments.task.entity.Entity;
import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAOImpl extends EntityDAO implements PlayerDAO {

    private UserService userService = new UserServiceImpl();

    @Override
    public List<Player> getAllPlayers() throws SQLException {
        List<Player> allPlayers = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllPlayers = "select * from players";
            statement = connection.prepareStatement(statementForGettingAllPlayers);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allPlayers.add(constructNewEntity());
            }

            return allPlayers;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public Player findPlayerById(int playerId) throws SQLException {
        Player soughtPlayer = null;

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingUserById = "select * from players where player_id = ?";
            statement = connection.prepareStatement(statementForGettingUserById);
            statement.setInt(1, playerId);
            resultSet = statement.executeQuery();

            if (resultSet != null) {
                resultSet.next();
                soughtPlayer = constructNewEntity();
            }

            return soughtPlayer;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addNewPlayer(Player playerToAdd) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForAddingPlayer = "insert into players (player_name, user_id) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(statementForAddingPlayer);
            setValuesToStatementFromObject(playerToAdd);

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
    public boolean deletePlayerById(int playerId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingPlayer = "delete from players where player_id = ?";
            statement = connection.prepareStatement(statementForDeletingPlayer);
            statement.setInt(1, playerId);

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
    public boolean updatePlayerInDB(int playerToUpdate, Player updatedPlayer) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementToUpdatePlayer = "update players set player_name = ?, user_id = ? " +
                    "where player_id = ?";
            statement = connection.prepareStatement(statementToUpdatePlayer);
            setValuesToStatementFromObject(updatedPlayer);
            statement.setInt(3, playerToUpdate);
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
        Player player = (Player) entity;

        statement.setString(1, player.getPlayerName());
        statement.setInt(2, player.getUser().getUserId());
    }

    @Override
    protected Player constructNewEntity() throws SQLException {
        int id = resultSet.getInt("player_id");
        String name = resultSet.getString("player_name");
        User user = userService.findUserById(resultSet.getInt("user_id"));

        return new Player(id, name, user);
    }
}
