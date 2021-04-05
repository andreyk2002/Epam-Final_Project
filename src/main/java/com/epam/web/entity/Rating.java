package com.epam.web.entity;

public class Rating {

    private final int rating;
    private final long userID;
    private final long filmID;

    public Rating(int rating, long userID, long filmID) {
        this.rating = rating;
        this.userID = userID;
        this.filmID = filmID;
    }

    public int getRating() {
        return rating;
    }

    public long getUserID() {
        return userID;
    }

    public long getFilmID() {
        return filmID;
    }
}
