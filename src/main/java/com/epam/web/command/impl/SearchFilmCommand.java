package com.epam.web.command.impl;

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

public class SearchFilmCommand implements Command {
    private static final String FILM_PAGE = Commands.SEARCH_PAGE_COMMAND.getName() + "&searchString=";
    private static final String SEARCH_STRING = "searchString";

    public SearchFilmCommand() {

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String filmName = request.getParameter(SEARCH_STRING);
        return CommandResult.redirect(FILM_PAGE + filmName);
    }
}