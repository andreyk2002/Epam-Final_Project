package com.epam.web.command;

public class CommandNotExistException extends RuntimeException {
    public CommandNotExistException() {
    }

    public CommandNotExistException(String message) {
        super(message);
    }

    public CommandNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
