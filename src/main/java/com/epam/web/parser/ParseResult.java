package com.epam.web.parser;

public class ParseResult {

    private final FilmField fieldType;
    private final String fieldValue;

    public static ParseResult imagePath(String fieldValue){
        return new ParseResult(FilmField.IMAGE_PATH, fieldValue);
    }

    public ParseResult(FilmField fieldType, String fieldValue) {
        this.fieldType = fieldType;
        this.fieldValue = fieldValue;
    }

    public FilmField getFieldType() {
        return fieldType;
    }

    public String getFieldValue() {
        return fieldValue;
    }
}
