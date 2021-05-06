package com.epam.web.command;

import com.epam.web.command.impl.Commands;
import com.epam.web.dto.FilmDTO;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchByGenreCommand implements Command {
    private static final String FILM_PAGE = Commands.SEARCH_PAGE_COMMAND.getName();
    private final FilmService filmService;

    public SearchByGenreCommand(FilmService genreSearchFilmService) {
        this.filmService = genreSearchFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String genreName = request.getParameter("genreName");
        List<FilmDTO> filmsByGenre =  filmService.getByGenreName(genreName);
        HttpSession session = request.getSession();
        session.setAttribute("movies", filmsByGenre);
        return CommandResult.redirect(FILM_PAGE);
    }
}
