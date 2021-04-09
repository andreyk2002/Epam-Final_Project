package com.epam.web.parser;

public class FormParserFactory {
    public FormParser createParser(boolean isFormField) {
        return isFormField ? new FieldFormParser() : new ImageFormParser();
    }
}
