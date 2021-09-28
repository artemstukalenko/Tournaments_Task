package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.TeamService;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.TeamServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class TeamController extends EntityController {

    private TeamService teamService;
    private UserService userService;

    public TeamController() {
        this.teamService = new TeamServiceImpl();
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
        System.out.println(teamService.getAllTeams());
    }

    @Override
    protected void processEntityAddition() {
        if (teamService.addNewTeam(constructNewTeam())) {
            System.out.println(ENTITY_ADDED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityDeletion() {
        System.out.println(DELETE_BY_ID);

        if (teamService.deleteTeamById(listenToInputForID())) {
            System.out.println(ENTITY_DELETED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityUpdate() {
        System.out.println(UPDATE_ENTITY_REQUEST);

        int teamToUpdateId = listenToInputForID();

        System.out.println(UPDATE_ENTITY_OBJECT + teamService.findTeamById(teamToUpdateId));

        Team updatedTeam = constructNewTeam();

        if (teamService.updateTeamInDB(teamToUpdateId, updatedTeam)) {
            System.out.println(UPDATED_SUCCESSFULLY + teamService.findTeamById(teamToUpdateId));
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    private Team constructNewTeam() {
        System.out.println(USER_ID_FOR_NEW_TEAM);
        User user = userService.findUserById(listenToInputForID());

        System.out.println(NAME_FOR_NEW_TEAM);
        String name = listenToInput();

        return new Team(user, name);
    }
}
