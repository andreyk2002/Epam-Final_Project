package com.epam.web.service.rating;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.Rating;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.stream.Collectors.groupingBy;

public class RatingManager {

    private static final Lock LOCK = new ReentrantLock();
    private static final Logger LOGGER = LogManager.getLogger(RatingManager.class);
    private static final Integer REVIEWS_BEFORE_CHECK = 5;
    public static final double CLOSE_TO_AVG = 0.5;
    public static final double FAR_FROM_AVG = 1.5;
    private final DaoHelperFactory daoHelperFactory;
    private final RatingDao ratingDao;
    private static final AtomicReference<ConcurrentHashMap<Long, List<Rating>>> NON_CHECKED_RATINGS = new AtomicReference<>();


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
        ConcurrentHashMap<Long, List<Rating>> localInstance = NON_CHECKED_RATINGS.get();
        if (localInstance == null) {
            try {
                LOCK.lock();
                localInstance = NON_CHECKED_RATINGS.get();
                if (localInstance == null) {
                    List<Rating> allRatings = ratingDao.getAll();
                    Map<Long, List<Rating>> collect = allRatings.stream().collect(groupingBy(Rating::getFilmID));
                    NON_CHECKED_RATINGS.getAndSet(new ConcurrentHashMap<>(collect));
                }
            } finally {
                LOCK.unlock();
            }
        }
    }


    public void changeRating(Rating lastRating) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            long filmID = lastRating.getFilmID();
            ConcurrentHashMap<Long, List<Rating>> ratings = NON_CHECKED_RATINGS.get();
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
            if (Math.abs(userRating - movieRating) < CLOSE_TO_AVG) {
                userDao.incrementRating(userID);
            } else if (Math.abs(userRating - movieRating) < FAR_FROM_AVG) {
                userDao.decrementRating(userID);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean addRating(Rating rating) {
        long filmID = rating.getFilmID();
        ConcurrentHashMap<Long, List<Rating>> ratings = NON_CHECKED_RATINGS.get();
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