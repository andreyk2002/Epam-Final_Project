package com.epam.web.validator;

public class UserRatingValidator {


    public static final int MIN_RATING = 0;
    public static final int MAX_RATING = 100;

    public boolean validateRating(double rating){
        return rating >= MIN_RATING && rating <= MAX_RATING;
    }
}
