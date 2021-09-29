package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.TournamentDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.TournamentDAOImpl;
import com.artemstukalenko.tournaments.task.entity.Tournament;
import com.artemstukalenko.tournaments.task.service.TournamentService;

import java.sql.SQLException;
import java.util.List;

public class TournamentServiceImpl implements TournamentService {

    private TournamentDAO tournamentDAO;

    public TournamentServiceImpl() {
        this.tournamentDAO = new TournamentDAOImpl();
    }

    @Override
    public List<Tournament> getAllTournaments() {
        try {
            return tournamentDAO.getAllTournaments();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Tournament findTournamentById(int tournamentId) {
        try {
            return tournamentDAO.findTournamentById(tournamentId);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addNewTournament(Tournament tournamentToAdd) {
        try {
            return tournamentDAO.addNewTournament(tournamentToAdd);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteTournamentById(int tournamentId) {
        try {
            return tournamentDAO.deleteTournamentById(tournamentId);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateTournamentInDB(int tournamentToUpdate, Tournament updatedTournament) {
        try {
            return tournamentDAO.updateTournamentInDB(tournamentToUpdate, updatedTournament);
        } catch (SQLException e) {
            return false;
        }
    }
}
