package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.service.TeamService;
import com.artemstukalenko.tournaments.task.service.implementators.TeamServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class TeamController extends EntityController {

    private TeamService teamService;

    public TeamController() {
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
        System.out.println(teamService.getAllTeams());
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
