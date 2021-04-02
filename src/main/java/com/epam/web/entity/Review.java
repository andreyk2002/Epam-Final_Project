package com.epam.web.entity;

public class Review {
    private final String username;
    private final String review;

    public Review(String username, String review) {
        this.username = username;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public String getReview() {
        return review;
    }

}
