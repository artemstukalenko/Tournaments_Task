package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleDAO {

    List<Schedule> getAllSchedules() throws SQLException;

    Schedule findScheduleById(int scheduleId) throws SQLException;

    boolean addNewSchedule(Schedule scheduleToAdd) throws SQLException;

    boolean deleteScheduleById(int scheduleId) throws SQLException;

    boolean updateScheduleInDB(int scheduleToUpdate, Schedule updatedSchedule) throws SQLException;

    boolean deleteScheduleByTeamId(int teamId) throws SQLException;
}
