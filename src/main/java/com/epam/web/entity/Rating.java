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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rating)) {
            return false;
        }
        Rating rating1 = (Rating) o;

        if (rating != rating1.rating) {
            return false;
        }
        if (userID != rating1.userID) {
            return false;
        }
        return filmID == rating1.filmID;
    }

    @Override
    public int hashCode() {
        int result = rating;
        result = 31 * result + (int) (userID ^ (userID >>> 32));
        result = 31 * result + (int) (filmID ^ (filmID >>> 32));
        return result;
    }
}
