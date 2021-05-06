package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.entity.Genre;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class EditFilmPageCommand implements Command {
    private static final String EDIT_PAGE_COMMAND = Commands.EDIT_FILM_PAGE_COMMAND.getName();

    public EditFilmPageCommand() {

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdParam = request.getParameter("filmId");
        long filmId = Long.parseLong(filmIdParam);
        return CommandResult.redirect(EDIT_PAGE_COMMAND + "&filmId=" + filmId);
    }
}
