package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.controller.Controller;
import com.artemstukalenko.tournaments.task.controller.UserChoice;
import com.artemstukalenko.tournaments.task.entity.Entity;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public abstract class EntityController extends Controller {

    @Override
    public void processUser() {
        System.out.println(WHAT_TO_DO_WITH);

        listenToInputCommand();

        responseToCommand();
    }

    protected int listenToInputForID() {
        int desiredId = 0;

        while (scanner.hasNext()) {

            try {
                desiredId = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println(WRONG_INPUT);
                continue;
            }
        }

        return desiredId;
    }

    @Override
    protected void setUserCommand(String input) {
        switch (input) {
            case "R":
                userCommand = UserChoice.SHOW_ALL;
                break;
            case "D":
                userCommand = UserChoice.DELETE_ENTITY;
                break;
            case "U":
                userCommand = UserChoice.UPDATE_ENTITY;
                break;
            case "A":
                userCommand = UserChoice.ADD_NEW_ENTITY;
                break;
        }
    }

    protected void responseToCommand() {
        switch (userCommand) {
            case SHOW_ALL:
                readAll();
                break;
            case ADD_NEW_ENTITY:
                processEntityAddition();
                break;
            case DELETE_ENTITY:
                processEntityDeletion();
                break;
            case UPDATE_ENTITY:
                processEntityUpdate();
                break;
        }
    }

    protected abstract void readAll();

    protected abstract void processEntityAddition();

    protected abstract void processEntityDeletion();

    protected abstract void processEntityUpdate();

    protected abstract Entity constructNewEntity();
}
