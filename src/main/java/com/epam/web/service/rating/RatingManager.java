package com.epam.web.service.rating;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Rating;
import com.epam.web.service.ServiceException;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.stream.Collectors.groupingByConcurrent;

public class RatingManager {

    private static final Lock LOCK = new ReentrantLock();
    private static final Integer REVIEWS_BEFORE_CHECK = 5;
    public static final double CLOSE_TO_AVG = 0.5;
    public static final double FAR_FROM_AVG = 1.5;
    private final DaoHelperFactory daoHelperFactory;
    private final RatingDao ratingDao;
    private static final AtomicReference<ConcurrentMap<Long, List<Rating>>> RATINGS = new AtomicReference<>();


    public RatingManager(DaoHelperFactory factory) throws ServiceException {
        this.daoHelperFactory = factory;
        try (DaoHelper helper = daoHelperFactory.create()) {
            this.ratingDao = helper.createRatingDao();
            initRatings();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private void initRatings() throws DaoException {
        ConcurrentMap<Long, List<Rating>> localInstance = RATINGS.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = RATINGS.get();
                if (localInstance == null) {
                    List<Rating> allRatings = ratingDao.getAll();
                    ConcurrentMap<Long, List<Rating>> collect = allRatings
                            .stream()
                            .collect(groupingByConcurrent(Rating::getFilmID));
                    RATINGS.getAndSet(collect);
                }
            } finally {
                LOCK.unlock();
            }
        }
    }


    public void changeRating(Rating lastRating) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            long filmID = lastRating.getFilmID();
            ConcurrentMap<Long, List<Rating>> ratings = RATINGS.get();
            List<Rating> filmRatings = ratings.get(filmID);
            int currentIndex = filmRatings.indexOf(lastRating) + 1;
            if (currentIndex < REVIEWS_BEFORE_CHECK) {
                return;
            }

            int positionToCheck = currentIndex - REVIEWS_BEFORE_CHECK;
            Rating ratingToCheck = filmRatings.get(positionToCheck);
            double movieRating = ratingDao.getMovieRating(filmID);
            double userRating = ratingToCheck.getRating();
            UserDao userDao = helper.createUserDao();
            long userID = ratingToCheck.getUserID();
            double delta = Math.abs(userRating - movieRating);
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
        ConcurrentMap<Long, List<Rating>> ratings = RATINGS.get();
        List<Rating> filmRatings = ratings.get(filmID);
        if (filmRatings == null) {
            List<Rating> firstFilmRating = List.of(rating);
            ratings.put(rating.getFilmID(), firstFilmRating);
            return true;
        }
        if (filmRatings.contains(rating)) {
            return false;
        }
        filmRatings.add(rating);
        return true;
    }
}