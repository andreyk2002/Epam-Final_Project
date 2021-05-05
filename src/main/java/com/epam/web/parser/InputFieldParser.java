package com.epam.web.parser;

import org.apache.commons.fileupload.FileItem;

import java.nio.charset.StandardCharsets;

class InputFieldParser implements FieldParser {
    @Override
    public FieldParseResult parse(FileItem item) {
        String fieldName = item.getFieldName();
        byte[] bytes = item.get();
        String result = new String(bytes, StandardCharsets.UTF_8);
        FilmField fieldType = FilmField.valueOf(fieldName.toUpperCase());
        return new FieldParseResult(fieldType, result);
    }
}
