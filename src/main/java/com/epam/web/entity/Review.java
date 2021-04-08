package com.epam.web.entity;

import java.util.Objects;

public class Review {
    private final String username;
    private final String review;
    private final long id;

    public Review(String username, String review, long id) {
        this.username = username;
        this.review = review;
        this.id = id;
    }

    public long getId(){
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getReview() {
        return review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)){
            return false;
        }
        Review review1 = (Review) o;
        if (id != review1.id) {
            return false;
        }
        if (!Objects.equals(username, review1.username)) {
            return false;
        }
        return Objects.equals(review, review1.review);
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        result = 31 * result + (int) (id ^ (id >>> 32));
        return result;
    }
}

