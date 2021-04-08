package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.User;
import com.epam.web.service.ReviewService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ReviewFilmCommand implements Command {
    private static final String SHOW_MOVIE = "/controller?commandName=movie&id=";
    private final ReviewService reviewService;

    public ReviewFilmCommand(ReviewService reviewService) {
        this.reviewService = reviewService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdString = request.getParameter("filmID");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        long userId = user.getId();
        String review = request.getParameter("review");
        long filmID = Long.parseLong(filmIdString);
        reviewService.reviewFilm(filmID, userId, review);
        return CommandResult.redirect(request.getContextPath() + SHOW_MOVIE + filmID);
    }
}
