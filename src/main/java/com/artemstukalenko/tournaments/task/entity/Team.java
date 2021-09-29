package com.artemstukalenko.tournaments.task.entity;

import java.util.Objects;

public class Team extends Entity {

    private int teamId;
    private User user;
    private String teamName;

    public Team() {}

    public Team(User user, String teamName) {
        this.user = user;
        this.teamName = teamName;
    }

    public Team(int teamId, User user, String teamName) {
        this.teamId = teamId;
        this.user = user;
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return teamId == team.teamId && Objects.equals(user, team.user) && Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamId, user, teamName);
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", user=" + user +
                ", teamName='" + teamName + '\'' +
                '}';
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
