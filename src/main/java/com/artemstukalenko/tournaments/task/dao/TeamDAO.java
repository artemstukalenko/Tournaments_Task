package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.Team;

import java.sql.SQLException;
import java.util.List;

public interface TeamDAO {

    List<Team> getAllTeams() throws SQLException;

    Team findTeamById(int teamId) throws SQLException;

    boolean addNewTeam(Team teamToAdd) throws SQLException;

    boolean deleteTeamById(int teamId) throws SQLException;

    boolean updateTeamInDB(int teamToUpdate, Team updatedTeam) throws SQLException;

}
