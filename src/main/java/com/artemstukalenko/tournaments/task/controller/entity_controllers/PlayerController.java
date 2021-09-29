package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.PlayerService;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.PlayerServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class PlayerController extends EntityController {

    private PlayerService playerService;
    private UserService userService;

    public PlayerController() {
        this.playerService = new PlayerServiceImpl();
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void readAll() {
        System.out.println(playerService.getAllPlayers());
    }

    @Override
    protected void processEntityAddition() {
        if (playerService.addNewPlayer(constructNewEntity())) {
            System.out.println(ENTITY_ADDED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityDeletion() {
        System.out.println(DELETE_BY_ID);

        if (playerService.deletePlayerById(listenToInputForID())) {
            System.out.println(ENTITY_DELETED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityUpdate() {
        System.out.println(UPDATE_ENTITY_REQUEST);

        int playerToUpdateId = listenToInputForID();

        System.out.println(UPDATE_ENTITY_OBJECT + playerService.findPlayerById(playerToUpdateId));

        Player updatedPlayer = constructNewEntity();

        if (playerService.updatePlayer(playerToUpdateId, updatedPlayer)) {
            System.out.println(UPDATED_SUCCESSFULLY + playerService.findPlayerById(playerToUpdateId));
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected Player constructNewEntity() {
        System.out.println(NAME_FOR_NEW_PLAYER);
        String nameForNewPlayer = listenToInput();

        System.out.println(USER_ID_FOR_NEW_PLAYER);
        User userForNewPlayer = userService.findUserById(listenToInputForID());

        return new Player(nameForNewPlayer, userForNewPlayer);
    }
}
