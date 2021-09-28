package com.artemstukalenko.tournaments.task.entity;

import java.util.Objects;

public class Player {

    private int id;
    private String playerName;
    private int userId;

    public Player() {}

    public Player(int id, String playerName, int userId) {
        this.id = id;
        this.playerName = playerName;
        this.userId = userId;
    }

    public Player(String playerName, int userId) {
        this.playerName = playerName;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && userId == player.userId && Objects.equals(playerName, player.playerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerName, userId);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", userId=" + userId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
