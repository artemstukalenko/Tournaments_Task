package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.ScheduleDAO;
import com.artemstukalenko.tournaments.task.dao.TournamentDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.ScheduleDAOImpl;
import com.artemstukalenko.tournaments.task.dao.implementators.TournamentDAOImpl;
import com.artemstukalenko.tournaments.task.entity.Tournament;
import com.artemstukalenko.tournaments.task.exception.CouldNotInteractWithEntityException;
import com.artemstukalenko.tournaments.task.exception.EntityNotFoundException;
import com.artemstukalenko.tournaments.task.service.TournamentService;

import java.sql.SQLException;
import java.util.List;

public class TournamentServiceImpl implements TournamentService {

    private TournamentDAO tournamentDAO;
    private ScheduleDAO scheduleDAO;

    public TournamentServiceImpl() {
        this.tournamentDAO = new TournamentDAOImpl();
        this.scheduleDAO = new ScheduleDAOImpl();
    }

    @Override
    public List<Tournament> getAllTournaments() {
        try {
            return tournamentDAO.getAllTournaments();
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public Tournament findTournamentById(int tournamentId) {
        try {
            return tournamentDAO.findTournamentById(tournamentId);
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean addNewTournament(Tournament tournamentToAdd) {
        try {
            return tournamentDAO.addNewTournament(tournamentToAdd);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean deleteTournamentById(int tournamentId) {
        try {
            scheduleDAO.deleteScheduleByExternalId(tournamentId, "tournament_id");
            return tournamentDAO.deleteTournamentById(tournamentId);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean updateTournamentInDB(int tournamentToUpdate, Tournament updatedTournament) {
        try {
            return tournamentDAO.updateTournamentInDB(tournamentToUpdate, updatedTournament);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }
}
