package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.PlayerDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.PlayerDAOImpl;
import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.service.PlayerService;

import java.sql.SQLException;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private PlayerDAO playerDAO;

    public PlayerServiceImpl() {
        this.playerDAO = new PlayerDAOImpl();
    }

    @Override
    public List<Player> getAllPlayers() {
        try {
            return playerDAO.getAllPlayers();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Player findPlayerById(int playerId) {
        try {
            return playerDAO.findPlayerById(playerId);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addNewPlayer(Player playerToAdd) {
        try {
            return playerDAO.addNewPlayer(playerToAdd);
        } catch (SQLException e) {
            return false;
        }
    }
}
