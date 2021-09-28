package com.artemstukalenko.tournaments.task.controller;

public interface TextConstants {
    String GREETING = "Hello! Please, choose an entity to work with! Type R for roles; U for users; P for players; T for teams";
    String WRONG_INPUT = "Wrong input!";
    String WHAT_TO_DO_WITH = "What do you want to do with this entity? Type R to read; A to add; D to delete; U to update";
    String ENTITY_ADDED = "Entity added successfully!";
    String UNEXPECTED_ERROR_OCCURRED = "Unexpected error has occurred!";
    String ENTER_NAME_FOR_NEW_ENTITY = "Enter name for entity:";
    String DELETE_BY_ID = "Type id of an entity you want to delete:";
    String ENTITY_DELETED = "Entity deleted successfully";
    String UPDATE_ENTITY_REQUEST = "Please, type in ID of an entity, that you want to update";
    String UPDATE_ENTITY_OBJECT = "You are going to update entity: ";
    String UPDATED_SUCCESSFULLY = "Successfully updated entity: ";

    String ROLE_FOR_NEW_USER = "Enter role for new user: ";
    String NAME_FOR_NEW_USER = "Enter name for new user: ";
    String USERNAME_FOR_NEW_USER = "Enter username for new user: ";
    String PASSWORD_FOR_NEW_USER = "Enter password for new user: ";
    String PASSWORD_LENGTH_MESSAGE = "Password's length has to be at least 4 symbols";

    String NAME_FOR_NEW_PLAYER = "Enter a name for new player: ";
    String USER_ID_FOR_NEW_PLAYER = "Enter user id for new player: ";

    String USER_ID_FOR_NEW_TEAM = "Enter user id for new team: ";
    String NAME_FOR_NEW_TEAM = "Enter name for new team: ";
}
