package com.epam.web.service;

import com.epam.web.dao.DaoException;
import com.epam.web.dao.DaoHelper;
import com.epam.web.dao.ReviewDao;
import com.epam.web.dao.factory.DaoHelperFactory;

public class ReviewService {
    private ReviewDao dao;

    public ReviewService(DaoHelperFactory helperFactory) {
        try(DaoHelper daoHelper = helperFactory.create()) {
            this.dao = daoHelper.createReviewDao();
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rateFilm(Long filmID, Long userID, String review) throws ServiceException {
        try {
            dao.rateFilm(filmID, userID, review);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}