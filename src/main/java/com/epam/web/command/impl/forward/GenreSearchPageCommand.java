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

public class GenreSearchPageCommand implements Command {

    private final FilmService filmService;
    private final GenreService genreService;


    public GenreSearchPageCommand(FilmService genreSearchFilmService, GenreService genreService) {
        this.filmService = genreSearchFilmService;
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String genreIdParam = request.getParameter("genreId");
        long genreId = Long.parseLong(genreIdParam);
        List<FilmDto> filmsByGenre = filmService.getByGenreId(genreId, user.getId());
        request.setAttribute("movies", filmsByGenre);
        List<Genre> allGenres = genreService.getAllGenres();
        request.setAttribute("genres", allGenres);
        return CommandResult.forward(Pages.SEARCH_PAGE);
    }
}
