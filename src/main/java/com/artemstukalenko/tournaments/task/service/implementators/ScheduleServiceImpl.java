package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.ScheduleDAO;
import com.artemstukalenko.tournaments.task.dao.implementators.ScheduleDAOImpl;
import com.artemstukalenko.tournaments.task.entity.Schedule;
import com.artemstukalenko.tournaments.task.service.ScheduleService;

import java.sql.SQLException;
import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private ScheduleDAO scheduleDAO;

    public ScheduleServiceImpl() {
        this.scheduleDAO = new ScheduleDAOImpl();
    }

    @Override
    public List<Schedule> getAllSchedules() {
        try {
            return scheduleDAO.getAllSchedules();
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Schedule findScheduleById(int scheduleId) {
        try {
            return scheduleDAO.findScheduleById(scheduleId);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean addNewSchedule(Schedule scheduleToAdd) {
        try {
            return scheduleDAO.addNewSchedule(scheduleToAdd);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteScheduleById(int scheduleId) {
        try {
            return scheduleDAO.deleteScheduleById(scheduleId);
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean updateScheduleInDB(int scheduleToUpdate, Schedule updatedSchedule) {
        try {
            return scheduleDAO.updateScheduleInDB(scheduleToUpdate, updatedSchedule);
        } catch (SQLException e) {
            return false;
        }
    }
}
