package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.service.PlayerService;
import com.artemstukalenko.tournaments.task.service.implementators.PlayerServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class PlayerController extends EntityController {

    private PlayerService playerService;

    public PlayerController() {
        this.playerService = new PlayerServiceImpl();
    }

    @Override
    public void processUser() {
        System.out.println(WHAT_TO_DO_WITH);

        listenToInputCommand();

        responseToCommand();
    }

    @Override
    protected void readAll() {
        System.out.println(playerService.getAllPlayers());
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
