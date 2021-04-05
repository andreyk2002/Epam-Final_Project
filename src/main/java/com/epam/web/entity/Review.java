package com.epam.web.entity;

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

}

