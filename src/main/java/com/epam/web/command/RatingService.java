package com.epam.web.command;

import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.RatingDao;
import com.epam.web.dao.UserDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.entity.User;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class RatingService {
    private final DaoHelperFactory daoHelperFactory;

    public RatingService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }


    public boolean rateFilm(long filmId, long userId, int rating) throws ServiceException {
        try(DaoHelper helper = daoHelperFactory.create()){
            helper.startTransaction();
            RatingDao dao = helper.createRatingDao();
            boolean result = dao.addRating(filmId, userId, rating);
            helper.endTransaction();
            return result;
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public double getFilmRating(Long filmId) {
      throw new UnsupportedOperationException();
    }
}
