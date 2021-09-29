package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.ScheduleDAO;
import com.artemstukalenko.tournaments.task.dao.TeamDAO;
import com.artemstukalenko.tournaments.task.dao.TeamPlayerDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.ScheduleDAOImpl;
import com.artemstukalenko.tournaments.task.dao.implementators.TeamDAOImpl;
import com.artemstukalenko.tournaments.task.dao.implementators.TeamPlayerDAOImpl;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.exception.CouldNotInteractWithEntityException;
import com.artemstukalenko.tournaments.task.exception.EntityNotFoundException;
import com.artemstukalenko.tournaments.task.service.TeamService;

import java.sql.SQLException;
import java.util.List;

public class TeamServiceImpl implements TeamService {

    private TeamDAO teamDAO;
    private TeamPlayerDAO teamPlayerDAO;
    private ScheduleDAO scheduleDAO;

    public TeamServiceImpl() {
        this.teamDAO = new TeamDAOImpl();
        this.teamPlayerDAO = new TeamPlayerDAOImpl();
        this.scheduleDAO = new ScheduleDAOImpl();
    }

    @Override
    public List<Team> getAllTeams() {
        try {
            return teamDAO.getAllTeams();
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public Team findTeamById(int teamId) {
        try {
            return teamDAO.findTeamById(teamId);
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean addNewTeam(Team teamToAdd) {
        try {
            return teamDAO.addNewTeam(teamToAdd);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean deleteTeamById(int teamId) {
        try {
            teamPlayerDAO.deleteTeamPlayerByExternalId(teamId,  "team_id");
            scheduleDAO.deleteScheduleByExternalId(teamId, "team_id");
            return teamDAO.deleteTeamById(teamId);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean updateTeamInDB(int teamToUpdate, Team updatedTeam) {
        try {
            return teamDAO.updateTeamInDB(teamToUpdate, updatedTeam);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean deleteTeamByUserId(int userId) {
        try {
            return teamDAO.deleteTeamByUserId(userId);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }
}
