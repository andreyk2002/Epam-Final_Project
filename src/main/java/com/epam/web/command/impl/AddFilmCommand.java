package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.parser.*;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AddFilmCommand implements Command {

    private static final String FILMS_PAGE = "/controller?commandName=showFilmsPage&pageNumber=";
    private final FilmService filmService;
    private final FormParser parser;


    public AddFilmCommand(FilmService saveFilmService, FormParser parser) {
        this.filmService = saveFilmService;
        this.parser=  parser;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            Film film = parser.parseFormData(request);
            filmService.saveFilm(film);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        HttpSession session = request.getSession();
        Integer pageNumber = (Integer)session.getAttribute("pageNumber");
        return CommandResult.redirect(FILMS_PAGE + pageNumber);
    }

}
