package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.ReviewDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.validator.XssProtect;

public class ReviewService {

    private final DaoHelperFactory daoHelperFactory;

    public ReviewService(DaoHelperFactory helperFactory) {
        this.daoHelperFactory = helperFactory;
    }

    public void reviewFilm(long filmID, long userID, String review) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            XssProtect protect = new XssProtect();
            String safeReview = protect.replaceMalformed(review);
            ReviewDao dao = daoHelper.createReviewDao();
            dao.reviewFilm(filmID, userID, safeReview);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
