package com.epam.web.command;

import com.epam.web.entity.Genre;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddFilmCommand implements Command {
    private static final String ADD_FILM_PAGE = "/controller?commandName=showAddPage";
    private final GenreService genreService;

    public AddFilmCommand(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Genre> genres = genreService.getAllGenres();
        HttpSession session = request.getSession();
        session.setAttribute("genres", genres);
        return CommandResult.redirect(request.getContextPath() + ADD_FILM_PAGE);
    }
}
