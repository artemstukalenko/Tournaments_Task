package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.PlayerDAO;
import com.artemstukalenko.tournaments.task.dao.TeamPlayerDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.PlayerDAOImpl;
import com.artemstukalenko.tournaments.task.dao.implementators.TeamPlayerDAOImpl;
import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.exception.CouldNotInteractWithEntityException;
import com.artemstukalenko.tournaments.task.exception.EntityNotFoundException;
import com.artemstukalenko.tournaments.task.service.PlayerService;

import java.sql.SQLException;
import java.util.List;

public class PlayerServiceImpl implements PlayerService {

    private PlayerDAO playerDAO;
    private TeamPlayerDAO teamPlayerDAO;

    public PlayerServiceImpl() {
        this.playerDAO = new PlayerDAOImpl();
        this.teamPlayerDAO = new TeamPlayerDAOImpl();
    }

    @Override
    public List<Player> getAllPlayers() {
        try {
            return playerDAO.getAllPlayers();
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public Player findPlayerById(int playerId) {
        try {
            return playerDAO.findPlayerById(playerId);
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean addNewPlayer(Player playerToAdd) {
        try {
            return playerDAO.addNewPlayer(playerToAdd);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean deletePlayerById(int playerToDeleteId) {
        try {
            teamPlayerDAO.deleteTeamPlayerByExternalId(playerToDeleteId, "player_id");
            return playerDAO.deletePlayerById(playerToDeleteId);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean updatePlayer(int playerToUpdateId, Player updatedPlayerObject) {
        try {
            return playerDAO.updatePlayerInDB(playerToUpdateId, updatedPlayerObject);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }
}
