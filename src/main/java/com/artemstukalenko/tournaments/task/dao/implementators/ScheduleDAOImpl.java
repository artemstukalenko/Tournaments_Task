package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.EntityDAO;
import com.artemstukalenko.tournaments.task.dao.ScheduleDAO;
import com.artemstukalenko.tournaments.task.dao.TeamDAO;
import com.artemstukalenko.tournaments.task.dao.TournamentDAO;
import com.artemstukalenko.tournaments.task.entity.Entity;
import com.artemstukalenko.tournaments.task.entity.Schedule;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.Tournament;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAOImpl extends EntityDAO implements ScheduleDAO {

    private TeamDAO teamDAO = new TeamDAOImpl();
    private TournamentDAO tournamentDAO = new TournamentDAOImpl();

    @Override
    public List<Schedule> getAllSchedules() throws SQLException {
        List<Schedule> allSchedules = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllSchedules = "select * from schedules";
            statement = connection.prepareStatement(statementForGettingAllSchedules);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allSchedules.add(constructNewEntity());
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
                soughtSchedule = constructNewEntity();
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

    @Override
    public boolean deleteScheduleByTeamId(int teamId) throws SQLException {

        try {
            setConnectionWithNoAutoCommit();
            String statementForDeletingScheduleByTeamId = "delete from schedules where team_id = ?";
            statement = connection.prepareStatement(statementForDeletingScheduleByTeamId);
            statement.setInt(1, teamId);

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
    protected Schedule constructNewEntity() throws SQLException {
        int id = resultSet.getInt("schedule_id");
        Team team = teamDAO.findTeamById(resultSet.getInt("team_id"));
        Tournament tournament = tournamentDAO.findTournamentById(resultSet.getInt("tournament_id"));

        return new Schedule(id, tournament, team);
    }

    @Override
    protected void setValuesToStatementFromObject(Entity entity) throws SQLException {
        Schedule schedule = (Schedule) entity;

        statement.setInt(1, schedule.getTournament().getTournamentId());
        statement.setInt(2, schedule.getTeam().getTeamId());
    }
}
