package com.artemstukalenko.tournaments.task.service.implementators;

import com.artemstukalenko.tournaments.task.dao.*;
import com.artemstukalenko.tournaments.task.dao.implementators.*;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.exception.CouldNotInteractWithEntityException;
import com.artemstukalenko.tournaments.task.exception.EntityNotFoundException;
import com.artemstukalenko.tournaments.task.service.PlayerService;
import com.artemstukalenko.tournaments.task.service.TeamService;
import com.artemstukalenko.tournaments.task.service.TournamentService;
import com.artemstukalenko.tournaments.task.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    private TeamService teamService;
    private PlayerService playerService;
    private TournamentService tournamentService;

    public UserServiceImpl() {
        this.userDAO = new UserDAOImpl();
        this.tournamentService = new TournamentServiceImpl();
        this.teamService = new TeamServiceImpl();
        this.playerService = new PlayerServiceImpl();
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean addNewUser(User userToAdd) {
        try {
            return userDAO.addNewUser(userToAdd);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUserById(int userId) {
        try {
            teamService.deleteTeamByUserId(userId);
            playerService.deletePlayerByUserId(userId);
            tournamentService.deleteTournamentByUserId(userId);

            return userDAO.deleteUserById(userId);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public User findUserById(int userId) {
        try {
            return userDAO.findUserById(userId);
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }

    @Override
    public boolean updateUser(int userToUpdateId, User updatedUserObject) {
        try {
            return userDAO.updateUser(userToUpdateId, updatedUserObject);
        } catch (SQLException e) {
            throw new CouldNotInteractWithEntityException(e.getMessage());
        }
    }

    @Override
    public List<User> findUsersByUserRoleId(int userRoleId) {
        try {
            return userDAO.findUsersByUserRoleId(userRoleId);
        } catch (SQLException e) {
            throw new EntityNotFoundException(e.getMessage());
        }
    }
}
