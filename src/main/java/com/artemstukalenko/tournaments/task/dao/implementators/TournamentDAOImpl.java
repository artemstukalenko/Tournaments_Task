package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.EntityDAO;
import com.artemstukalenko.tournaments.task.dao.TournamentDAO;
import com.artemstukalenko.tournaments.task.dao.UserDAO;
import com.artemstukalenko.tournaments.task.entity.Entity;
import com.artemstukalenko.tournaments.task.entity.Tournament;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentDAOImpl extends EntityDAO implements TournamentDAO {

    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<Tournament> getAllTournaments() throws SQLException {
        List<Tournament> allTournaments = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllTournaments = "select * from tournaments";
            statement = connection.prepareStatement(statementForGettingAllTournaments);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allTournaments.add(constructNewEntity());
            }

            return allTournaments;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public Tournament findTournamentById(int tournamentId) throws SQLException {
        Tournament soughtTournament = null;

        try {
            setConnectionWithNoAutoCommit();
            String statementFprGettingTournamentById = "select * from tournaments " +
                    "where tournament_id = ?";
            statement = connection.prepareStatement(statementFprGettingTournamentById);
            statement.setInt(1, tournamentId);
            resultSet = statement.executeQuery();

            if (resultSet != null) {
                resultSet.next();
                soughtTournament = constructNewEntity();
            }

            return soughtTournament;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addNewTournament(Tournament tournamentToAdd) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForAddingTournament = "insert into tournaments (user_id, tournament_name, " +
                    "venue, start_date, end_date) values (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(statementForAddingTournament);
            setValuesToStatementFromObject(tournamentToAdd);

            statement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean deleteTournamentById(int tournamentId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingTournamentById = "delete from tournaments where tournament_id = ?";
            statement = connection.prepareStatement(statementForDeletingTournamentById);
            statement.setInt(1, tournamentId);

            statement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean updateTournamentInDB(int tournamentToUpdate, Tournament updatedTournament) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementToUpdateTournament = "update tournaments set user_id = ?, tournament_name = ?, " +
                    "venue = ?, start_date = ?, end_date = ? " +
                    "where tournament_id = ?";
            statement = connection.prepareStatement(statementToUpdateTournament);
            setValuesToStatementFromObject(updatedTournament);
            statement.setInt(6, tournamentToUpdate);
            statement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean deleteTournamentByUserId(int userId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingTournamentByUserId = "delete from tournaments where user_id = ?";
            statement = connection.prepareStatement(statementForDeletingTournamentByUserId);
            statement.setInt(1, userId);

            statement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            return false;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    protected Tournament constructNewEntity() throws SQLException {
        int id = resultSet.getInt("tournament_id");
        User user = userDAO.findUserById(resultSet.getInt("user_id"));
        String name = resultSet.getString("tournament_name");
        String venue = resultSet.getString("venue");
        LocalDate start = LocalDate.parse(resultSet.getString("start_date"));
        LocalDate end = LocalDate.parse(resultSet.getString("end_date"));

        return new Tournament(id, user, name, venue, start, end);
    }

    @Override
    protected void setValuesToStatementFromObject(Entity entity) throws SQLException {
        Tournament tournament = (Tournament) entity;

        statement.setInt(1, tournament.getUser().getUserId());
        statement.setString(2, tournament.getTournamentName());
        statement.setString(3, tournament.getVenue());
        statement.setDate(4, Date.valueOf(tournament.getStartDate()));
        statement.setDate(5, Date.valueOf(tournament.getEndDate()));
    }
}
