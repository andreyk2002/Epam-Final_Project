package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.dto.FilmDto;
import com.epam.web.entity.Genre;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class EditPageCommand implements Command {
    private static final String EDIT_FILM_PAGE = Commands.EDIT_FILM_PATH.getName();
    private final FilmService filmService;
    private final GenreService genreService;

    public EditPageCommand(FilmService editFilmService, GenreService genreService) {
        this.genreService = genreService;
        this.filmService = editFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdParam = request.getParameter("filmId");
        long filmId = Long.parseLong(filmIdParam);
        Optional<FilmDto> optionalMovie = filmService.getFilmDtoById(filmId);
        optionalMovie.orElseThrow(() -> new ServiceException("Film with id = " + filmId + " not found"));
        FilmDto movie = optionalMovie.get();
        List<Genre> allGenres = genreService.getAllGenres();
        request.setAttribute("genres", allGenres);
        request.setAttribute("movie", movie);
        return CommandResult.forward(EDIT_FILM_PAGE);
    }
}
