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

public class GetFilmsCommand implements Command {

    public static final String COMMAND_URL = "/controller?commandName=mainPage&pageNumber=";
    private final FilmService filmService;

    public GetFilmsCommand(FilmService filmService) {
        this.filmService = filmService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = request.getParameter("pageNumber");
        int pageNumber = Integer.parseInt(page);
        List<FilmDTO> movies = filmService.getNextMovies(pageNumber);
        int totalPages = filmService.getPagesCount();
        HttpSession session = request.getSession();
        session.setAttribute("movies", movies);
        session.setAttribute("pagesCount", totalPages);
        return CommandResult.redirect(request.getContextPath() + COMMAND_URL + pageNumber);
    }
}
