package com.epam.web.command.impl.pages;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.dto.FilmDTO;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GenreSearchPageCommand implements Command {
    private static final String SEARCH_PAGE = Commands.SEARCH_PAGE_PATH.getName();
    private final FilmService filmService;

    public GenreSearchPageCommand(FilmService genreSearchFilmService) {
        this.filmService = genreSearchFilmService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String genreName = request.getParameter("genreName");
        List<FilmDTO> filmsByGenre = filmService.getByGenreName(genreName);
        request.setAttribute("movies", filmsByGenre);
        return CommandResult.forward(SEARCH_PAGE);
    }
}
