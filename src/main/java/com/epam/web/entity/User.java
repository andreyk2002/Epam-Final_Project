package com.epam.web.entity;

import java.util.Objects;

public class User {

    private final String login;
    private final long id;
    private final double rating;
    private final Role role;

    public User(long id, String login, double rating, Role role) {
        this.login = login;
        this.id = id;
        this.rating = rating;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }


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
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (Double.compare(user.rating, rating) != 0) {
            return false;
        }
        if (!Objects.equals(login, user.login)) {
            return false;
        }
        return role == user.role;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = login != null ? login.hashCode() : 0;
        result = 31 * result + (int) (id ^ (id >>> 32));
        temp = Double.doubleToLongBits(rating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

}
