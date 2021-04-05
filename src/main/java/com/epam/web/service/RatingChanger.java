package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Rating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.Callable;

//Package access

class RatingChanger implements Callable<Boolean> {

    private static final Logger LOGGER = LogManager.getLogger(RatingChanger.class);

    private final long filmId;
    private final DaoHelperFactory daoHelperFactory;

    public RatingChanger(long filmId, DaoHelperFactory daoHelperFactory) {
        this.filmId = filmId;
        this.daoHelperFactory = daoHelperFactory;
    }

    @Override
    public Boolean call() {
        try (DaoHelper helper = daoHelperFactory.create()) {
            RatingDao ratingDao = helper.createRatingDao();
            Optional<Rating> optionalRating = ratingDao.getRatingForCheck(filmId);
            if (!optionalRating.isPresent()) {
                return false;
            }
            Rating ratingToCheck = optionalRating.get();
            long filmID = ratingToCheck.getFilmID();
            double movieRating = ratingDao.getMovieRating(filmID);
            double userRating = ratingToCheck.getRating();
            UserDao userDao = helper.createUserDao();
            long userID = ratingToCheck.getUserID();
            if (Math.abs(userRating - movieRating) < 0.5) {
                userDao.increaseRating(userID);
            } else if (Math.abs(userRating - movieRating) < 1.5) {
                userDao.decreaseRating(userID);
            }
            return true;
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return false;
    }
}