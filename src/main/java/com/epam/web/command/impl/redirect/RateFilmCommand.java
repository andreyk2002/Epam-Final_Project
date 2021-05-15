package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.entity.User;
import com.epam.web.service.RatingService;
import com.epam.web.service.RatingStatus;
import com.epam.web.service.ServiceException;
import com.epam.web.service.rating.RatingManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RateFilmCommand implements Command {

    private static final String SHOW_MOVIE = Commands.GET_MOVIE.getName() + "&filmId=";
    private static final String BACK_TO_MOVIE = Commands.SHOW_FILM_PAGE_COMMAND.getName() + "&filmId=";
    private static final String ALREADY_RATED_ERROR = "&errorMessage=local.alreadyRatedError";
    private static final String WRONG_RATING_ERROR = "&errorMessage=local.wrongRating";
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
        RatingManager ratingManager = RatingManager.getInstance();
        RatingStatus status = service.rateFilm(ratingManager, filmId, userId, rating);
        String backToCurrent = BACK_TO_MOVIE + filmId;
        switch (status) {
            case SUCCESS:
                return CommandResult.redirect(SHOW_MOVIE + filmId);
            case ALREADY_RATED:
                return CommandResult.redirect(backToCurrent + ALREADY_RATED_ERROR);
            case WRONG_RATING:
                return CommandResult.redirect(backToCurrent + filmId + WRONG_RATING_ERROR);
            default:
                throw new IllegalArgumentException("Wrong status " + status);
        }
    }
}
