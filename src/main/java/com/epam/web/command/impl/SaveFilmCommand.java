package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.entity.Film;
import com.epam.web.parser.FilmField;
import com.epam.web.parser.FormParser;
import com.epam.web.parser.FormParserFactory;
import com.epam.web.parser.ParseResult;
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
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> inputs = upload.parseRequest(request);
            Film.Builder builder = parseFormData(inputs);
            Film film = builder.build();
            filmService.saveFilm(film);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return CommandResult.redirect(request.getContextPath() + FILMS_PAGE);
    }

    private Film.Builder parseFormData(List<FileItem> images) throws Exception {
        Film.Builder builder = new Film.Builder();
        for (FileItem image : images) {
            boolean isFromField = image.isFormField();
            FormParserFactory parserFactory = new FormParserFactory();
            FormParser parser = parserFactory.createParser(isFromField);
            ParseResult parseResult = parser.parse(image);
            setField(builder, parseResult);
        }
        return builder;
    }

    private void setField(Film.Builder builder, ParseResult parseResult) {
        FilmField fieldType = parseResult.getFieldType();
        String fieldValue = parseResult.getFieldValue();
        switch (fieldType) {
            case NAME:
                builder.withName(fieldValue);
                break;
            case GENRE_ID:
                long genreId = Long.parseLong(fieldValue);
                builder.withGenreId(genreId);
                break;
            case IMAGE_PATH:
                builder.withImagePath(fieldValue);
                break;
            case DESCRIPTION:
                builder.withDescription(fieldValue);
        }
    }


}
