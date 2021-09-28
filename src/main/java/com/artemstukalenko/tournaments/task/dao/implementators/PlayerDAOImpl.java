package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.PlayerDAO;
import com.artemstukalenko.tournaments.task.entity.Player;

import java.sql.SQLException;
import java.util.List;

public class PlayerDAOImpl implements PlayerDAO, ConnectionCloser {

    @Override
    public List<Player> getAllPlayers() throws SQLException {
        return null;
    }

    @Override
    public Player findPlayerById(int playerId) throws SQLException {
        return null;
    }

    @Override
    public boolean addNewPlayer(Player playerToAdd) throws SQLException {
        return false;
    }

    @Override
    public boolean deletePlayerById(int playerId) throws SQLException {
        return false;
    }

    @Override
    public boolean updatePlayerInDB(int playerToUpdate, Player updatedPlayer) throws SQLException {
        return false;
    }
}
