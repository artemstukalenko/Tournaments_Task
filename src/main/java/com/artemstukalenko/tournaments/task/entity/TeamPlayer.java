package com.artemstukalenko.tournaments.task.entity;

import java.util.Objects;

public class TeamPlayer {

    private int teamPlayerId;
    private Team team;
    private Player player;

    public TeamPlayer() {}

    public TeamPlayer(Team team, Player player) {
        this.team = team;
        this.player = player;
    }

    public TeamPlayer(int teamPlayerId, Team team, Player player) {
        this.teamPlayerId = teamPlayerId;
        this.team = team;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamPlayer that = (TeamPlayer) o;
        return teamPlayerId == that.teamPlayerId && Objects.equals(team, that.team) && Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamPlayerId, team, player);
    }

    @Override
    public String toString() {
        return "TeamPlayer{" +
                "teamPlayerId=" + teamPlayerId +
                ", team=" + team +
                ", player=" + player +
                '}';
    }

    public int getTeamPlayerId() {
        return teamPlayerId;
    }

    public void setTeamPlayerId(int teamPlayerId) {
        this.teamPlayerId = teamPlayerId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
