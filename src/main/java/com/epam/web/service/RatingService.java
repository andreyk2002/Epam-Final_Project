package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class RatingService {
    private final DaoHelperFactory daoHelperFactory;
    private final RatingDao ratingDao;


    public RatingService(DaoHelperFactory daoHelperFactory) throws ServiceException {
        this.daoHelperFactory = daoHelperFactory;

        try (DaoHelper helper = daoHelperFactory.create()) {
            this.ratingDao = helper.createRatingDao();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }


    public boolean rateFilm(long filmId, long userId, int rating) throws ServiceException {
        try (DaoHelper helper = daoHelperFactory.create()) {
            helper.startTransaction();
            boolean result = ratingDao.addRating(filmId, userId, rating);
            if (result) {
                ExecutorService service = new ForkJoinPool(1);
                RatingChanger changer = new RatingChanger(filmId, daoHelperFactory);
                Future<Boolean> future = service.submit(changer);
                future.get();
                service.shutdown();
            }
            helper.endTransaction();
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
