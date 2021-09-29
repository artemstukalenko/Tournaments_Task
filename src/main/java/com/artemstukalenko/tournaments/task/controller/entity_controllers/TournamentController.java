package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.controller.RegexContainer;
import com.artemstukalenko.tournaments.task.entity.Tournament;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.TournamentService;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.TournamentServiceImpl;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.time.LocalDate;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;

public class TournamentController extends EntityController{

    private TournamentService tournamentService;
    private UserService userService;

    public TournamentController() {
        this.tournamentService = new TournamentServiceImpl();
        this.userService = new UserServiceImpl();
    }

    @Override
    protected void readAll() {
        System.out.println(tournamentService.getAllTournaments());
    }

    @Override
    protected void processEntityAddition() {
        if (tournamentService.addNewTournament(constructNewEntity())) {
            System.out.println(ENTITY_ADDED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityDeletion() {
        System.out.println(DELETE_BY_ID);

        if (tournamentService.deleteTournamentById(listenToInputForID())) {
            System.out.println(ENTITY_DELETED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityUpdate() {
        System.out.println(UPDATE_ENTITY_REQUEST);

        int tournamentToUpdateId = listenToInputForID();

        System.out.println(UPDATE_ENTITY_OBJECT + tournamentService.findTournamentById(tournamentToUpdateId));

        Tournament updatedTournament = constructNewEntity();

        if (tournamentService.updateTournamentInDB(tournamentToUpdateId, updatedTournament)) {
            System.out.println(UPDATED_SUCCESSFULLY +
                    tournamentService.findTournamentById(tournamentToUpdateId));
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected Tournament constructNewEntity() {
        System.out.println(USER_ID_FOR_NEW_TOURNAMENT);
        User user = userService.findUserById(listenToInputForID());

        System.out.println(NAME_FOR_NEW_TOURNAMENT);
        String name = listenToInputForString();

        System.out.println(VENUE_FOR_NEW_TOURNAMENT);
        String venue = listenToInput();

        System.out.println(START_DATE_FOR_NEW_TOURNAMENT);
        LocalDate start = LocalDate.parse(listenToInputForDate());

        System.out.println(END_DATE_FOR_NEW_TOURNAMENT);
        LocalDate end = LocalDate.parse(listenToInputForDate());

        return new Tournament(user, name, venue, start, end);
    }

    private String listenToInputForDate() {

        while (scanner.hasNext()) {
            String input = scanner.next();

            if (input.matches(RegexContainer.VALID_DATE)) {
                return input;
            } else {
                System.out.println(WRONG_INPUT);
                continue;
            }
        }

        throw new IllegalArgumentException();
    }
}
