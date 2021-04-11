package com.epam.web.parser;

class FieldParserFactory {
    public FieldParser createParser(boolean isFormField) {
        return isFormField ? new InputFieldParser() : new ImageFieldParser();
    }
}
