package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Pages;
import com.epam.web.dto.FilmDto;
import com.epam.web.entity.Genre;
import com.epam.web.entity.User;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainPageCommand implements Command {

    private final FilmService filmService;
    private final GenreService genreService;

    public MainPageCommand(FilmService filmService, GenreService genreService) {
        this.filmService = filmService;
        this.genreService = genreService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int pageNumber = (Integer) session.getAttribute("pageNumber");
        List<FilmDto> movies = filmService.getPage(pageNumber, user.getId());
        int totalPages = filmService.getPagesCount();
        List<Genre> allGenres = genreService.getAllGenres();
        request.setAttribute("genres", allGenres);
        request.setAttribute("movies", movies);
        request.setAttribute("pagesCount", totalPages);
        return CommandResult.forward(Pages.MAIN_PAGE);
    }
}
