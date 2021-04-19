package com.epam.web.validator;

public class RatingValidator {

    public boolean validateRating(int rating) {
        return rating >= 0 && rating <= 5;
    }
}
