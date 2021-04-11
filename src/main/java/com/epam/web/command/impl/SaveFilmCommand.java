package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Film;
import com.epam.web.parser.*;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SaveFilmCommand implements Command {

    private static final String FILMS_PAGE = "/controller?commandName=showFilmsPage&pageNumber=0";
    private final FilmService filmService;

    public SaveFilmCommand(FilmService saveFilmService) {
        this.filmService = saveFilmService;
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            FormParser processor = new FormParser();
            Film film = processor.parseFormData(request);
            filmService.saveFilm(film);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(request.getContextPath() + FILMS_PAGE);
    }

}
