package com.epam.web.parser;

class FieldParserFactory {
    private static final String IMAGE = "Image";
    private static final String GENRE_LIST = "genre";

    public FieldParser createParser(String isFormField) {
        switch (isFormField){
            case IMAGE:
                return new ImageFieldParser();
            case GENRE_LIST:
                return new ListParser();
            default:
                return new InputFieldParser();
        }
    }
}
