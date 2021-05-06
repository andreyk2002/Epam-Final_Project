package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dto.FilmDTO;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SearchByGenreCommand implements Command {
    private static final String SEARCH_PAGE = Commands.GENRE_SEARCH_PAGE.getName() + "&genreName=";

    public SearchByGenreCommand() {

    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String genreName = request.getParameter("genreName");
        return CommandResult.redirect(SEARCH_PAGE + genreName);
    }
}
