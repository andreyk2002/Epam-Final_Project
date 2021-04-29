package com.epam.web.command;

import com.epam.web.dto.FilmDTO;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchByGenreCommand implements Command {
    private static final String FILM_PAGE = "/controller?commandName=mainPage&pageNumber=0";
    private final FilmService filmService;

    public SearchByGenreCommand(FilmService genreSearchFilmService) {
        this.filmService = genreSearchFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String genreIdParam = request.getParameter("genreId");
        long genreId = Long.parseLong(genreIdParam);
        List<FilmDTO> filmsByGenre =  filmService.getByGenreId(genreId);
        HttpSession session = request.getSession();
        session.setAttribute("movies", filmsByGenre);
        return CommandResult.redirect(FILM_PAGE);
    }
}
