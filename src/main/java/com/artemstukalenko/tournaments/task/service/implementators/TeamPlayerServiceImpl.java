package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.TeamPlayerDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.TeamPlayerDAOImpl;
import com.artemstukalenko.tournaments.task.entity.TeamPlayer;
import com.artemstukalenko.tournaments.task.service.TeamPlayerService;

import java.sql.SQLException;
import java.util.List;

public class TeamPlayerServiceImpl implements TeamPlayerService {

    private TeamPlayerDAO teamPlayerDAO;

    public TeamPlayerServiceImpl() {
        this.teamPlayerDAO = new TeamPlayerDAOImpl();
    }

    @Override
    public List<TeamPlayer> getAllTeamPlayers() {
        try {
            return teamPlayerDAO.getAllTeamPlayers();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public TeamPlayer findTeamPlayerById(int teamPlayerId) {
        try {
            return teamPlayerDAO.findTeamPlayerById(teamPlayerId);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addNewTeamPlayer(TeamPlayer teamPlayerToAdd) {
        try {
            return teamPlayerDAO.addNewTeamPlayer(teamPlayerToAdd);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteTeamPlayerById(int teamPlayerId) {
        try {
            return teamPlayerDAO.deleteTeamPlayerById(teamPlayerId);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateTeamPlayerInDB(int teamPlayerToUpdate, TeamPlayer updatedTeamPlayer) {
        try {
            return teamPlayerDAO.updateTeamPlayerInDB(teamPlayerToUpdate, updatedTeamPlayer);
        } catch (SQLException e) {
            return false;
        }
    }
}
