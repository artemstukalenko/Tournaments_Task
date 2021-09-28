package com.artemstukalenko.tournaments.task.dao.implementators;

import com.artemstukalenko.tournaments.task.dao.ConnectionCloser;
import com.artemstukalenko.tournaments.task.dao.PlayerDAO;
import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.entity.User;
import com.artemstukalenko.tournaments.task.service.UserService;
import com.artemstukalenko.tournaments.task.service.implementators.UserServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public class PlayerDAOImpl implements PlayerDAO, ConnectionCloser {

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    private UserService userService = new UserServiceImpl();

    @Override
    public List<Player> getAllPlayers() throws SQLException {
        List<Player> allPlayers = new ArrayList<>();

        try {
            setConnectionWithNoAutoCommit();
            String statementForGettingAllPlayers = "select * from players";
            statement = connection.prepareStatement(statementForGettingAllPlayers);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                allPlayers.add(constructNewPlayer());
            }

            return allPlayers;
        } finally {
            close(connection, statement, resultSet);
        }
    }

    @Override
    public Player findPlayerById(int playerId) throws SQLException {
        return null;
    }

    @Override
    public boolean addNewPlayer(Player playerToAdd) throws SQLException {
        return false;
    }

    @Override
    public boolean deletePlayerById(int playerId) throws SQLException {
        return false;
    }

    @Override
    public boolean updatePlayerInDB(int playerToUpdate, Player updatedPlayer) throws SQLException {
        return false;
    }

    private void setConnectionWithNoAutoCommit() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        connection.setAutoCommit(false);
    }

    private Player constructNewPlayer() throws SQLException {
        int id = resultSet.getInt("player_id");
        String name = resultSet.getString("player_name");
        User user = userService.findUserById(resultSet.getInt("user_id"));

        return new Player(id, name, user);
    }
}
