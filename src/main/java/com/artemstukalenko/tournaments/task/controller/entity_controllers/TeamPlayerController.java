package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.service.TeamPlayerService;
import com.artemstukalenko.tournaments.task.service.implementators.TeamPlayerServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class TeamPlayerController extends EntityController {

    private TeamPlayerService teamPlayerService;

    public TeamPlayerController() {
        this.teamPlayerService = new TeamPlayerServiceImpl();
    }

    @Override
    public void processUser() {
        System.out.println(WHAT_TO_DO_WITH);

        listenToInputCommand();

        responseToCommand();
    }

    @Override
    protected void readAll() {
        System.out.println(teamPlayerService.getAllTeamPlayers());
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
