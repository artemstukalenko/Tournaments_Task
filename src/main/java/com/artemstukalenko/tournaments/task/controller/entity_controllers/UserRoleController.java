package com.artemstukalenko.tournaments.task.controller.entity_controllers;

import com.artemstukalenko.tournaments.task.controller.entity_controllers.EntityController;
import com.artemstukalenko.tournaments.task.entity.UserRole;
import com.artemstukalenko.tournaments.task.service.UserRoleService;
import com.artemstukalenko.tournaments.task.service.implementators.UserRoleServiceImpl;

import java.sql.SQLException;

import static com.artemstukalenko.tournaments.task.controller.TextConstants.*;
import static com.artemstukalenko.tournaments.task.controller.RegexContainer.*;

public class UserRoleController extends EntityController {

    private UserRoleService userRoleService;

    public UserRoleController() {
        this.userRoleService = new UserRoleServiceImpl();
    }

    @Override
    protected void processEntityUpdate() {
        System.out.println(UPDATE_ENTITY_REQUEST);

        int roleToUpdate = listenToInputForID();

        System.out.println(UPDATE_ENTITY_OBJECT + userRoleService.findRoleById(roleToUpdate));
        System.out.println(ENTER_NAME_FOR_NEW_ENTITY);

        UserRole updatedRoleObject = constructNewRole();

        if(userRoleService.updateRole(roleToUpdate, updatedRoleObject)) {
            System.out.println(UPDATED_SUCCESSFULLY + userRoleService.findRoleById(roleToUpdate));
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityDeletion() {
        System.out.println(DELETE_BY_ID);

        if (userRoleService.deleteRoleById(listenToInputForID())) {
            System.out.println(ENTITY_DELETED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    @Override
    protected void processEntityAddition() {
        System.out.println(ENTER_NAME_FOR_NEW_ENTITY);

        if(userRoleService.addNewRole(constructNewRole())) {
            System.out.println(ENTITY_ADDED);
        } else {
            System.out.println(UNEXPECTED_ERROR_OCCURRED);
        }
    }

    private UserRole constructNewRole() {
        UserRole newRole = null;

        while (scanner.hasNext()) {
            String roleName = scanner.next();

            if(roleName.matches(ONLY_LETTERS)) {
                newRole = new UserRole(roleName);
                break;
            } else {
                System.out.println(WRONG_INPUT);
            }
        }

        return newRole;
    }

    @Override
    public void readAll() {
        System.out.println(userRoleService.getAllUserRoles());
    }

}
