package com.epam.web.dao;

public class WrongQueryException extends Exception {

    public WrongQueryException() {
    }

    public WrongQueryException(String message) {
        super(message);
    }

    public WrongQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
