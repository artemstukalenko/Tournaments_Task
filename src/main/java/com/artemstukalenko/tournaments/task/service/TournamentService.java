package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.Tournament;

import java.sql.SQLException;
import java.util.List;

public interface TournamentService {

    List<Tournament> getAllTournaments();

    Tournament findTournamentById(int tournamentId);

    boolean addNewTournament(Tournament tournamentToAdd);

    boolean deleteTournamentById(int tournamentId);

    boolean updateTournamentInDB(int tournamentToUpdate, Tournament updatedTournament);

}
