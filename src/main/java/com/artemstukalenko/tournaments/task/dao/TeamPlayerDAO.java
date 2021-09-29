package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.TeamPlayer;

import java.sql.SQLException;
import java.util.List;

public interface TeamPlayerDAO {

    List<TeamPlayer> getAllTeamPlayers() throws SQLException;

    TeamPlayer findTeamPlayerById(int teamPlayerId) throws SQLException;

    boolean addNewTeamPlayer(TeamPlayer teamPlayerToAdd) throws SQLException;

    boolean deleteTeamPlayerById(int teamPlayerId) throws SQLException;

    boolean updateTeamPlayerInDB(int teamPlayerToUpdate, TeamPlayer updatedTeamPlayer) throws SQLException;

    boolean deleteTeamPlayerByExternalId(int playerId, String columnName) throws SQLException;
}
