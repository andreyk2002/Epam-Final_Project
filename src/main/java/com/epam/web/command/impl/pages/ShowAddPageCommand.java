package com.epam.web.command.impl.pages;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.entity.Genre;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowAddPageCommand implements Command {

    private static final String CREATE_FILM_PAGE = Commands.CREATE_FILM_PATH.getName();
    private final GenreService genreService;

    public ShowAddPageCommand(GenreService service) {
        this.genreService = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Genre> genres = genreService.getAllGenres();
        HttpSession session = request.getSession();
        session.setAttribute("genres", genres);
        return CommandResult.forward(CREATE_FILM_PAGE);
    }
}
