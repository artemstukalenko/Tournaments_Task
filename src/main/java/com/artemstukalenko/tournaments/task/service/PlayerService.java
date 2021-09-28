package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.Player;

import java.sql.SQLException;
import java.util.List;

public interface PlayerService {

    List<Player> getAllPlayers();

    Player findPlayerById(int playerId);

    boolean addNewPlayer(Player playerToAdd);

    boolean deletePlayerById(int playerToDeleteId);
}
