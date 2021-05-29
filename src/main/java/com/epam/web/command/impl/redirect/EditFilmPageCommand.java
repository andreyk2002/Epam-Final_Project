package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditFilmPageCommand implements Command {
    private static final String EDIT_PAGE_COMMAND = Commands.EDIT_FILM_PAGE_COMMAND+ "&filmId=";

    public EditFilmPageCommand() {

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmIdParam = request.getParameter("filmId");
        return CommandResult.redirect(EDIT_PAGE_COMMAND + filmIdParam);
    }
}
