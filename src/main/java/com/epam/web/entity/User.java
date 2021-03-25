package com.epam.web.entity;

import java.util.Objects;

public class User implements Identifiable {
    private static final String USER_TABLE = "Users";
    private final String name;
    private final long id;
    private final double rating;
    private final Role role;

    public User(long id, String name, double rating, Role role) {
        this.name = name;
        this.id = id;
        this.rating = rating;
        this.role = role;
    }

    public static String getTable() {
        return USER_TABLE;
    }

    public String getLogin() {
        return name;
    }

    @Override
    public long getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (Double.compare(user.rating, rating) != 0) return false;
        if (!Objects.equals(name, user.name)) return false;
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    public String getPassword() {
        return null;
    }
}
