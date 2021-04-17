package com.epam.web.parser;

import com.epam.web.service.ServiceException;

public class FormParserException extends ServiceException {
    public FormParserException() {
        super();
    }

    public FormParserException(String message) {
        super(message);
    }

    public FormParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
