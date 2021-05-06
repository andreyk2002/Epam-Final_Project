package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dto.FilmDTO;
import com.epam.web.entity.Genre;
import com.epam.web.service.FilmService;
import com.epam.web.service.GenreService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GetFilmsCommand implements Command {

    private static final String FILM_PAGE = Commands.MAIN_PAGE_COMMAND.getName() + "&pageNumber=";

    public GetFilmsCommand() {

    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = request.getParameter("pageNumber");
        return CommandResult.redirect(FILM_PAGE + page);
    }
}
