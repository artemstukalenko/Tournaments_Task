package com.artemstukalenko.tournaments.task.entity;

import java.util.Objects;

public class User {

    private int userId;
    private int roleId;
    private String name;
    private String username;
    private String password;
    private boolean isAdmin;

    public User() {}

    public User(int roleId, String name, String username, String password, boolean isAdmin) {
        this.roleId = roleId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId && roleId == user.roleId && isAdmin == user.isAdmin && Objects.equals(name, user.name) && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId, name, username, password, isAdmin);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
