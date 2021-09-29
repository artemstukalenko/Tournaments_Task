package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.TournamentDAO;
import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.entity.Tournament;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public class TournamentDAOImpl implements TournamentDAO, ConnectionCloser {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private UserService userService = new UserServiceImpl();

    @Override
    public List<Tournament> getAllTournaments() throws SQLException {
        List<Tournament> allTournaments = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllTournaments = "select * from tournaments";
            statement = connection.prepareStatement(statementForGettingAllTournaments);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allTournaments.add(constructNewTournament());
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
                soughtTournament = constructNewTournament();
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

    private void setConnectionWithNoAutoCommit() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        connection.setAutoCommit(false);
    }

    private Tournament constructNewTournament() throws SQLException {
        int id = resultSet.getInt("tournament_id");
        User user = userService.findUserById(resultSet.getInt("user_id"));
        String name = resultSet.getString("tournament_name");
        String venue = resultSet.getString("venue");
        LocalDate start = LocalDate.parse(resultSet.getString("start_date"));
        LocalDate end = LocalDate.parse(resultSet.getString("end_date"));

        return new Tournament(id, user, name, venue, start, end);
    }

    private void setValuesToStatementFromObject(Tournament tournament) throws SQLException {
        statement.setInt(1, tournament.getUser().getUserId());
        statement.setString(2, tournament.getTournamentName());
        statement.setString(3, tournament.getVenue());
        statement.setDate(4, Date.valueOf(tournament.getStartDate()));
        statement.setDate(5, Date.valueOf(tournament.getEndDate()));
    }
}
