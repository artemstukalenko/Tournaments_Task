package com.artemstukalenko.tournaments.task.dao;

import com.artemstukalenko.tournaments.task.entity.Entity;
import com.artemstukalenko.tournaments.task.entity.Player;
import com.artemstukalenko.tournaments.task.entity.User;

import java.sql.*;

import static com.artemstukalenko.tournaments.task.db_info.DBInfo.*;

public abstract class EntityDAO implements ConnectionCloser {

    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;

    protected void setConnectionWithNoAutoCommit() throws SQLException {
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        connection.setAutoCommit(false);
    }

    protected abstract void setValuesToStatementFromObject(Entity entity) throws SQLException;

    protected abstract Entity constructNewEntity() throws SQLException;

}
