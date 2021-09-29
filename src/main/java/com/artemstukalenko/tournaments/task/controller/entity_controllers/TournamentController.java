package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.service.TournamentService;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.TournamentServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class TournamentController extends EntityController{

    private TournamentService tournamentService;
    private UserService userService;

    public TournamentController() {
        this.tournamentService = new TournamentServiceImpl();
        this.userService = new UserServiceImpl();
    }

    @Override
    public void processUser() {
        System.out.println(WHAT_TO_DO_WITH);

        listenToInputCommand();

        responseToCommand();
    }

    @Override
    protected void readAll() {
        System.out.println(tournamentService.getAllTournaments());
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
