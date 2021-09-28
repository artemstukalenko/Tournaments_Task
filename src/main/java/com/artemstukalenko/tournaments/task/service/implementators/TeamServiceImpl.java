package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.TeamDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.TeamDAOImpl;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.service.TeamService;

import java.sql.SQLException;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamDAO teamDAO;

    public TeamServiceImpl() {
        this.teamDAO = new TeamDAOImpl();
    }

    @Override
    public List<Team> getAllTeams() {
        try {
            return teamDAO.getAllTeams();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Team findTeamById(int teamId) {
        return null;
    }

    @Override
    public boolean addNewTeam(Team teamToAdd) {
        try {
            return teamDAO.addNewTeam(teamToAdd);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteTeamById(int teamId) {
        return false;
    }

    @Override
    public boolean updateTeamInDB(int teamToUpdate, Team updatedTeam) {
        return false;
    }
}
