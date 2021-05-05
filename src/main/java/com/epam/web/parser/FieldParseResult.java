package com.epam.web.parser;

class FieldParseResult {

    private final FilmField fieldType;
    private final String fieldValue;

    public static FieldParseResult imagePath(String fieldValue){
        return new FieldParseResult(FilmField.IMAGE_PATH, fieldValue);
    }

    public FieldParseResult(FilmField fieldType, String fieldValue) {
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
