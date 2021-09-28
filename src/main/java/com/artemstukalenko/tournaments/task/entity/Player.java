package com.artemstukalenko.tournaments.task.entity;

import java.util.Objects;

public class Player {

    private int id;
    private String playerName;
    private User user;

    public Player() {}

    public Player(int id, String playerName, User user) {
        this.id = id;
        this.playerName = playerName;
        this.user = user;
    }

    public Player(String playerName, User user) {
        this.playerName = playerName;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && Objects.equals(playerName, player.playerName) && Objects.equals(user, player.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playerName, user);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", user=" + user +
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
