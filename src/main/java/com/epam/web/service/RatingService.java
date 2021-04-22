package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Rating;
import com.epam.web.service.rating.RatingManager;
import com.epam.web.validator.RatingValidator;

public class RatingService {
    private final DaoHelperFactory daoHelperFactory;
    private final RatingDao ratingDao;
    private final RatingValidator validator;


    public RatingService(DaoHelperFactory daoHelperFactory, RatingValidator validator) throws ServiceException {
        this.validator = validator;
        this.daoHelperFactory = daoHelperFactory;
        try (DaoHelper helper = daoHelperFactory.create()) {
            this.ratingDao = helper.createRatingDao();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    public RatingStatus rateFilm(long filmID, long userID, int score) throws ServiceException {
        if (!validator.validateRating(score)) {
            return RatingStatus.WRONG_RATING;
        }
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            Rating rating = new Rating(score, userID, filmID);
            RatingManager ratingManager = new RatingManager(daoHelperFactory);
            boolean firstRated = ratingManager.addRating(rating);
            RatingStatus status;
            if (firstRated) {
                ratingManager.changeRating(rating);
                ratingDao.addRating(rating);
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
