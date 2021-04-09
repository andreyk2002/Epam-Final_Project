package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FieldFormParser implements FormParser {
    @Override
    public ParseResult parse(FileItem item) throws Exception {
        String fieldName = item.getFieldName();
        try (InputStream itemStream = item.getInputStream();
             InputStreamReader itemStreamReader = new InputStreamReader(itemStream);
             BufferedReader reader = new BufferedReader(itemStreamReader)) {
            String value = reader.readLine();
            FilmField fieldType = FilmField.valueOf(fieldName.toUpperCase());
            return new ParseResult(fieldType, value);
        }
    }
}
