package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.entity.Team;
import com.artemstukalenko.tournaments.task.entity.TeamPlayer;
import com.artemstukalenko.tournaments.task.service.PlayerService;
import com.artemstukalenko.tournaments.task.service.TeamPlayerService;
import com.artemstukalenko.tournaments.task.service.TeamService;
import com.artemstukalenko.tournaments.task.service.implementators.PlayerServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.TeamPlayerServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.TeamServiceImpl;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class TeamPlayerController extends EntityController {

    private TeamPlayerService teamPlayerService;
    private TeamService teamService;
    private PlayerService playerService;

    public TeamPlayerController() {
        this.teamPlayerService = new TeamPlayerServiceImpl();
        this.teamService = new TeamServiceImpl();
        this.playerService = new PlayerServiceImpl();
    }

    @Override
    protected void readAll() {
        System.out.println(teamPlayerService.getAllTeamPlayers());
    }

    @Override
    protected void processEntityAddition() {
        if (teamPlayerService.addNewTeamPlayer(constructNewEntity())) {
            System.out.println(ENTITY_ADDED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityDeletion() {
        System.out.println(DELETE_BY_ID);

        if (teamPlayerService.deleteTeamPlayerById(listenToInputForID())) {
            System.out.println(ENTITY_DELETED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityUpdate() {
        System.out.println(UPDATE_ENTITY_REQUEST);

        int teamPlayerToUpdateId = listenToInputForID();

        System.out.println(UPDATE_ENTITY_OBJECT +
                teamPlayerService.findTeamPlayerById(teamPlayerToUpdateId));

        TeamPlayer updatedTeamPlayer = constructNewEntity();

        if (teamPlayerService.updateTeamPlayerInDB(teamPlayerToUpdateId, updatedTeamPlayer)) {
            System.out.println(UPDATED_SUCCESSFULLY + teamPlayerService
                    .findTeamPlayerById(teamPlayerToUpdateId));
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected TeamPlayer constructNewEntity() {
        System.out.println(TEAM_ID_FOR_TEAMPLAYER);
        Team team = teamService.findTeamById(listenToInputForID());

        System.out.println(PLAYER_ID_FOR_TEAMPLAYER);
        Player player = playerService.findPlayerById(listenToInputForID());

        return new TeamPlayer(team, player);
    }
}
