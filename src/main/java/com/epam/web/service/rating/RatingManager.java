package com.epam.web.service.rating;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Rating;
import com.epam.web.service.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.stream.Collectors.groupingByConcurrent;

public class RatingManager {

    private static final Lock LOCK = new ReentrantLock();
    private static final Integer REVIEWS_BEFORE_CHECK = 5;
    private static final double CLOSE_TO_AVG = 0.5;
    private static final double FAR_FROM_AVG = 1.5;
    private static final AtomicReference<RatingManager> INSTANCE = new AtomicReference<>();
    private final ConcurrentMap<Long, List<Rating>> ratings;


    private RatingManager() throws ServiceException {
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        try (DaoHelper helper = daoHelperFactory.create()) {
            RatingDao ratingDao = helper.createRatingDao();
            List<Rating> allRatings = ratingDao.getAll();
            this.ratings = allRatings
                    .stream()
                    .collect(groupingByConcurrent(Rating::getFilmID));
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public static RatingManager getInstance() throws ServiceException {
        RatingManager localInstance = INSTANCE.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = INSTANCE.get();
                if (localInstance == null) {
                    RatingManager manager = new RatingManager();
                    INSTANCE.getAndSet(manager);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }


    public void changeUserRating(UserDao userDao, Rating lastRating) throws ServiceException {
        try {
            long filmID = lastRating.getFilmID();
            List<Rating> filmRatings = ratings.get(filmID);
            int currentIndex = filmRatings.indexOf(lastRating) + 1;
            if (currentIndex < REVIEWS_BEFORE_CHECK) {
                return;
            }
            int positionToCheck = currentIndex - REVIEWS_BEFORE_CHECK;
            Rating ratingToCheck = filmRatings.get(positionToCheck);
            double averageRating = filmRatings.stream()
                    .mapToInt(Rating::getRating)
                    .summaryStatistics()
                    .getAverage();
            double userRating = ratingToCheck.getRating();
            long userID = ratingToCheck.getUserID();
            double delta = Math.abs(userRating - averageRating);
            if (delta < CLOSE_TO_AVG) {
                userDao.incrementRating(userID);
            } else if (delta > FAR_FROM_AVG) {
                userDao.decrementRating(userID);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean addRating(Rating rating) {
        long filmID = rating.getFilmID();
        List<Rating> filmRatings = ratings.get(filmID);
        if (filmRatings == null) {
            List<Rating> addNotSupport = List.of(rating);
            List<Rating> newRatings = new ArrayList<>(addNotSupport);
            ratings.put(rating.getFilmID(), newRatings);
            return true;
        }
        boolean containsRating = filmRatings.stream()
                .anyMatch(rate -> rate.getUserID() == rating.getUserID());
        if (containsRating) {
            return false;
        }
        filmRatings.add(rating);
        return true;
    }
}