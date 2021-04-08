package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.ReviewDao;
import com.epam.web.dao.factory.DaoHelperFactory;

public class ReviewService {

    private final DaoHelperFactory daoHelperFactory;

    public ReviewService(DaoHelperFactory helperFactory) {
        this.daoHelperFactory = helperFactory;
    }

    public void reviewFilm(long filmID, long userID, String review) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            daoHelper.startTransaction();
            ReviewDao dao = daoHelper.createReviewDao();
            dao.reviewFilm(filmID, userID, review);
            daoHelper.endTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
