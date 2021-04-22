package com.epam.web.validator;

public class RatingValidator {

    public static final int MIN_RATING = 0;
    public static final int RATING = 5;

    public boolean validateRating(double rating) {
        return rating >= MIN_RATING && rating <= RATING;
    }
}
