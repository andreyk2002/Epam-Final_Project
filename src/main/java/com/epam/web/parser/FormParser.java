package com.epam.web.parser;

import com.epam.web.dao.DaoException;
import com.epam.web.entity.Film;
import com.epam.web.service.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FormParser {
    public Film parseFormData(HttpServletRequest request) throws FormParserException {
        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> inputs = upload.parseRequest(request);
            inputs.removeIf(fileItem -> fileItem.getSize() == 0);
            Film.Builder builder = new Film.Builder();
            for (FileItem input : inputs) {
                boolean isFromField = input.isFormField();
                FieldParserFactory parserFactory = new FieldParserFactory();
                FieldParser parser = parserFactory.createParser(isFromField);
                ParseResult parseResult = parser.parse(input);
                setField(builder, parseResult);
            }
            return builder.build();
        } catch (Exception e) {
            throw new FormParserException(e.getMessage(), e);
        }
    }

    private void setField(Film.Builder builder, ParseResult parseResult) {
        FilmField fieldType = parseResult.getFieldType();
        String fieldValue = parseResult.getFieldValue();
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
        }
    }
}
