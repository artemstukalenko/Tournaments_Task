package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.Tournament;

import java.sql.SQLException;
import java.util.List;

public interface TournamentDAO {

    List<Tournament> getAllTournaments() throws SQLException;

    Tournament findTournamentById(int tournamentId) throws SQLException;

    boolean addNewTournament(Tournament tournamentToAdd) throws SQLException;

    boolean deleteTournamentById(int tournamentId) throws SQLException;

    boolean updateTournamentInDB(int tournamentToUpdate, Tournament updatedTournament) throws SQLException;

}
