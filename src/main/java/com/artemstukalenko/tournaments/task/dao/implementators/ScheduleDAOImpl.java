package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.ScheduleDAO;
import com.artemstukalenko.tournaments.task.entity.Schedule;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.Tournament;
import com.artemstukalenko.tournaments.task.service.TeamService;
import com.artemstukalenko.tournaments.task.service.TournamentService;
import com.artemstukalenko.tournaments.task.service.implementators.TeamServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.TournamentServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public class ScheduleDAOImpl implements ScheduleDAO, ConnectionCloser {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private TeamService teamService = new TeamServiceImpl();
    private TournamentService tournamentService = new TournamentServiceImpl();

    @Override
    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> allSchedules = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllSchedules = "select * from schedules";
            statement = connection.prepareStatement(statementForGettingAllSchedules);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allSchedules.add(constructNewSchedule());
            }

            return allSchedules;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public Schedule findScheduleById(int scheduleId) throws SQLException {
        Schedule soughtSchedule = null;

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingScheduleById = "select * from schedules where schedule_id = ?";
            statement = connection.prepareStatement(statementForGettingScheduleById);
            statement.setInt(1, scheduleId);
            resultSet = statement.executeQuery();

            if (resultSet != null) {
                resultSet.next();
                soughtSchedule = constructNewSchedule();
            }

            return soughtSchedule;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public boolean addNewSchedule(Schedule scheduleToAdd) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForAddingNewSchedule = "insert into schedules (tournament_id, team_id) " +
                    "values (?, ?)";
            statement = connection.prepareStatement(statementForAddingNewSchedule);
            setValuesToStatementFromObject(scheduleToAdd);

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
    public boolean deleteScheduleById(int scheduleId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingScheduleById = "delete from schedules where schedule_id = ?";
            statement = connection.prepareStatement(statementForDeletingScheduleById);
            statement.setInt(1, scheduleId);

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
    public boolean updateScheduleInDB(int scheduleToUpdate, Schedule updatedSchedule) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForUpdatingSchedule = "update schedules set tournament_id = ?, team_id = ? " +
                    "where schedule_id = ?";
            statement = connection.prepareStatement(statementForUpdatingSchedule);
            setValuesToStatementFromObject(updatedSchedule);
            statement.setInt(3, scheduleToUpdate);
            statement.executeUpdate();

            connection.commit();
            return true;
        } catch (SQLException e){
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

    private Schedule constructNewSchedule() throws SQLException {
        int id = resultSet.getInt("schedule_id");
        Team team = teamService.findTeamById(resultSet.getInt("team_id"));
        Tournament tournament = tournamentService.findTournamentById(resultSet.getInt("tournament_id"));

        return new Schedule(id, tournament, team);
    }

    private void setValuesToStatementFromObject(Schedule schedule) throws SQLException {
        statement.setInt(1, schedule.getTournament().getTournamentId());
        statement.setInt(2, schedule.getTeam().getTeamId());
    }
}
