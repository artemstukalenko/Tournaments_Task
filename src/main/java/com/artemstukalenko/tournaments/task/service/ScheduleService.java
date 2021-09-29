package com.artemstukalenko.tournaments.task.service;

import com.artemstukalenko.tournaments.task.entity.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface ScheduleService {

    List<Schedule> getAllSchedules();

    Schedule findScheduleById(int scheduleId);

    boolean addNewSchedule(Schedule scheduleToAdd);

    boolean deleteScheduleById(int scheduleId);

    boolean updateScheduleInDB(int scheduleToUpdate, Schedule updatedSchedule);

}
