package com.epam.web.command;

import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RateFilmCommand implements Command {

    private static final String SHOW_MOVIE = "/controller?commandName=movie&id=";
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
        long userId = (long) session.getAttribute("userId");
        if(!service.rateFilm(filmId, userId, rating)){
            session.setAttribute("errorMessage", "local.alreadyRatedError");
        }
        return CommandResult.redirect(request.getContextPath() + SHOW_MOVIE + filmId);

    }
}
