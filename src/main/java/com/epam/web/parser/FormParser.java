package com.epam.web.parser;

import com.epam.web.entity.Film;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FormParser {
    private List<Long> genresId = new ArrayList<>();

    public ParseResult parseFormData(HttpServletRequest request) throws FormParserException {
        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> inputs = upload.parseRequest(request);
            inputs.removeIf(fileItem -> fileItem.getSize() == 0);
            Film.Builder builder = new Film.Builder();
            for (FileItem input : inputs) {
                FieldParserFactory parserFactory = new FieldParserFactory();
                FieldParser parser = parserFactory.createParser(input.getFieldName());
                FieldParseResult fieldParseResult = parser.parse(input);
                setField(builder, fieldParseResult);
            }
            Film film = builder.build();
            return new ParseResult(film, genresId);
        } catch (Exception e) {
            throw new FormParserException(e.getMessage(), e);
        }
    }

    private void setField(Film.Builder builder, FieldParseResult fieldParseResult) {
        FilmField fieldType = fieldParseResult.getFieldType();
        String fieldValue = fieldParseResult.getFieldValue();
        switch (fieldType) {
            case NAME:
                builder.withName(fieldValue);
                break;
            case IMAGE_PATH:
                builder.withImagePath(fieldValue);
                break;
            case DESCRIPTION:
                builder.withDescription(fieldValue);
                break;
            case FILMID:
                long filmId = Long.parseLong(fieldValue);
                builder.withId(filmId);
                break;
            case GENREID:
                long genreId = Long.parseLong(fieldValue);
                genresId.add(genreId);
        }
    }
}
