package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.ReviewDao;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.security.XssProtect;

public class ReviewService {

    private final DaoHelperFactory daoHelperFactory;
    private final XssProtect protect;

    public ReviewService(DaoHelperFactory helperFactory, XssProtect protect) {
        this.daoHelperFactory = helperFactory;
        this.protect = protect;
    }

    public void reviewFilm(long filmID, long userID, String review) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            String safeReview = protect.replaceMalformed(review);
            ReviewDao dao = daoHelper.createReviewDao();
            dao.reviewFilm(filmID, userID, safeReview);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
