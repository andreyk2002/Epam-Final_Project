package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFilmCommand implements Command {
    private static final String SHOW_MOVIE = Commands.SHOW_FILM_PAGE_COMMAND.getName();

    public GetFilmCommand() {

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String idString = request.getParameter("filmId");
        long id = Long.parseLong(idString);
        return CommandResult.redirect(SHOW_MOVIE + "&filmId=" + id);
    }
}
