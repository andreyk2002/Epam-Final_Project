package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Rating;
import com.epam.web.service.rating.RatingManager;
import com.epam.web.validator.RatingValidator;

public class RatingService {
    private final DaoHelperFactory daoHelperFactory;
    private final RatingValidator validator;


    public RatingService(DaoHelperFactory daoHelperFactory, RatingValidator validator) throws ServiceException {
        this.validator = validator;
        this.daoHelperFactory = daoHelperFactory;
    }


    public RatingStatus rateFilm(RatingManager ratingManager ,long filmID, long userID, int score) throws ServiceException {
        if (!validator.validateRating(score)) {
            return RatingStatus.WRONG_RATING;
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            UserDao userDao = helper.createUserDao();
            RatingDao ratingDao = helper.createRatingDao();
            Rating rating = new Rating(score, userID, filmID);
            boolean firstRated = ratingManager.addRating(rating);
            RatingStatus status;
            if (firstRated) {
                ratingDao.addRating(rating);
                ratingManager.changeUserRating(userDao, rating);
                status = RatingStatus.SUCCESS;
            } else {
                status = RatingStatus.ALREADY_RATED;
            }
            helper.endTransaction();
            return status;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
