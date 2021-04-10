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

public class EditFilmCommand implements Command {
    public static final String EDIT_PAGE_COMMAND = "/controller?commandName=editFilmPage";
    private final FilmService filmService;
    private final GenreService genreService;

    public EditFilmCommand(FilmService editFilmService, GenreService genreService) {
        this.genreService = genreService;
        this.filmService = editFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdParam = request.getParameter("filmId");
        long filmId = Long.parseLong(filmIdParam);
        try {
            Optional<Film> optionalMovie = filmService.getFilmById(filmId);
            optionalMovie.orElseThrow(() -> new ServiceException("local.movieNotFound"));
            HttpSession session = request.getSession();
            Film movie = optionalMovie.get();
            List<Genre> allGenres = genreService.getAllGenres();
            session.setAttribute("genres", allGenres);
            session.setAttribute("movie", movie);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return CommandResult.redirect(request.getContextPath() + EDIT_PAGE_COMMAND);
    }
}
