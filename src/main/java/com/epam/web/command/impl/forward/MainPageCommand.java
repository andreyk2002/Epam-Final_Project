package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.dto.FilmDTO;
import com.epam.web.entity.Genre;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainPageCommand implements Command {

    private final String MAIN_PAGE = Commands.MAIN_PAGE_PATH.getName();
    private final FilmService filmService;
    private final GenreService genreService;

    public MainPageCommand(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        int pageNumber = (Integer)session.getAttribute("pageNumber");
        List<FilmDTO> movies = filmService.getPage(pageNumber);
        int totalPages = filmService.getPagesCount();
        List<Genre> allGenres = genreService.getAllGenres();
        request.setAttribute("genres", allGenres);
        request.setAttribute("movies", movies);
        request.setAttribute("pagesCount", totalPages);
        return CommandResult.forward(MAIN_PAGE);
    }
}
