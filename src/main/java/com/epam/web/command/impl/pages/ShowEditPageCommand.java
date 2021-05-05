package com.epam.web.command.impl.pages;

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

public class ShowEditPageCommand implements Command {
    private static final String EDIT_FILM_PAGE = "/WEB-INF/view/editFilm.jsp";
    private final FilmService filmService;
    private final GenreService genreService;

    public ShowEditPageCommand(FilmService editFilmService, GenreService genreService) {
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
            Film movie = optionalMovie.get();
            List<Genre> allGenres = genreService.getAllGenres();
            request.setAttribute("genres", allGenres);
            request.setAttribute("movie", movie);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return CommandResult.forward(EDIT_FILM_PAGE);
    }
}
