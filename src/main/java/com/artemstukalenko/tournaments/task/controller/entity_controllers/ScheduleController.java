package com.artemstukalenko.tournaments.task.controller.entity_controllers;

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

    }

    @Override
    protected void processEntityDeletion() {

    }

    @Override
    protected void processEntityUpdate() {

    }
}
