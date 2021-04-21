package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.RatingService;
import com.epam.web.service.RatingStatus;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RateFilmCommand implements Command {

    private static final String SHOW_MOVIE = "/controller?commandName=movie&id=";
    private static final String BACK_TO_MOVIE = "/controller?commandName=showMoviePage&errorMessage=";
    public static final String ALREADY_RATED_ERROR = "local.alreadyRatedError";
    public static final String WRONG_RATING_ERROR = "local.wrongRating";
    private final RatingService service;

    public RateFilmCommand(RatingService ratingService) {
        this.service = ratingService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdString = request.getParameter("filmID");
        String ratingString = request.getParameter("rating");
        long filmId = Long.parseLong(filmIdString);
        int rating = Integer.parseInt(ratingString);

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();


        RatingStatus status = service.rateFilm(filmId, userId, rating);
        switch (status) {
            case SUCCESS:
                return CommandResult.redirect(SHOW_MOVIE + filmId);
            case ALREADY_RATED:
                return CommandResult.redirect(BACK_TO_MOVIE + ALREADY_RATED_ERROR);
            case WRONG_RATING:
                return CommandResult.redirect(BACK_TO_MOVIE + WRONG_RATING_ERROR);
            default:
                throw new IllegalArgumentException("Wrong status " + status);
        }
    }
}
