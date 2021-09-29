package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.entity.Schedule;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.Tournament;
import com.artemstukalenko.tournaments.task.service.ScheduleService;
import com.artemstukalenko.tournaments.task.service.TeamService;
import com.artemstukalenko.tournaments.task.service.TournamentService;
import com.artemstukalenko.tournaments.task.service.implementators.ScheduleServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.TeamServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.TournamentServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class ScheduleController extends EntityController {

    private ScheduleService scheduleService;
    private TournamentService tournamentService;
    private TeamService teamService;

    public ScheduleController() {
        this.scheduleService = new ScheduleServiceImpl();
        this.tournamentService = new TournamentServiceImpl();
        this.teamService = new TeamServiceImpl();
    }

    @Override
    public void processUser() {
        System.out.println(WHAT_TO_DO_WITH);

        listenToInputCommand();

        responseToCommand();
    }

    @Override
    protected void readAll() {
        System.out.println(scheduleService.getAllSchedules());
    }

    @Override
    protected void processEntityAddition() {
        if (scheduleService.addNewSchedule(constructNewSchedule())) {
            System.out.println(ENTITY_ADDED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityDeletion() {
        System.out.println(DELETE_BY_ID);

        if (scheduleService.deleteScheduleById(listenToInputForID())) {
            System.out.println(ENTITY_DELETED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityUpdate() {
        System.out.println(UPDATE_ENTITY_REQUEST);

        int scheduleToUpdateId = listenToInputForID();

        System.out.println(UPDATE_ENTITY_OBJECT + scheduleService.findScheduleById(scheduleToUpdateId));

        Schedule updatedSchedule = constructNewSchedule();

        if (scheduleService.updateScheduleInDB(scheduleToUpdateId, updatedSchedule)) {
            System.out.println(UPDATED_SUCCESSFULLY +
                    scheduleService.findScheduleById(scheduleToUpdateId));
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    private Schedule constructNewSchedule() {
        System.out.println(TOURNAMENT_ID_FOR_NEW_SCHEDULE);
        Tournament tournament = tournamentService.findTournamentById(listenToInputForID());

        System.out.println(TEAM_ID_FOR_NEW_SCHEDULE);
        Team team = teamService.findTeamById(listenToInputForID());

        return new Schedule(tournament, team);
    }
}
