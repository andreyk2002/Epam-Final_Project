package com.epam.web.command;

import com.epam.web.service.ReviewService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReviewFilmCommand implements Command {
    private static final String SHOW_MOVIE = "/controller?commandName=showMoviePage&id=";
    private final ReviewService reviewService;

    public ReviewFilmCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdString = request.getParameter("filmID");
        HttpSession session = request.getSession();
        Long userID = (Long) session.getAttribute("userId");
        String review = request.getParameter("review");

        Long filmID = Long.parseLong(filmIdString);
        reviewService.rateFilm(filmID, userID, review);
        return CommandResult.redirect(request.getContextPath() + SHOW_MOVIE);
    }
}
