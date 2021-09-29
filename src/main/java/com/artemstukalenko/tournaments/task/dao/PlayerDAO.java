package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.Player;

import java.sql.SQLException;
import java.util.List;

public interface PlayerDAO {

    List<Player> getAllPlayers() throws SQLException;

    Player findPlayerById(int playerId) throws SQLException;

    boolean addNewPlayer(Player playerToAdd) throws SQLException;

    boolean deletePlayerById(int playerId) throws SQLException;

    boolean updatePlayerInDB(int playerToUpdate, Player updatedPlayer) throws SQLException;

    boolean deletePlayerByUserId(int userId) throws SQLException;
}
