package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.Team;

import java.sql.SQLException;
import java.util.List;

public interface TeamService {

    List<Team> getAllTeams();

    Team findTeamById(int teamId);

    boolean addNewTeam(Team teamToAdd);

    boolean deleteTeamById(int teamId);

    boolean updateTeamInDB(int teamToUpdate, Team updatedTeam);

}
