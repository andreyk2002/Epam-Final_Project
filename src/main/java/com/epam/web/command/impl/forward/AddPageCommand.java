package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Pages;
import com.epam.web.entity.Genre;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddPageCommand implements Command {

    private final GenreService genreService;

    public AddPageCommand(GenreService service) {
        this.genreService = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Genre> genres = genreService.getAllGenres();
        request.setAttribute("genres", genres);
        return CommandResult.forward(Pages.CREATE_FILM);
    }
}
