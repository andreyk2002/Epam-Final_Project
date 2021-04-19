package com.epam.web.command;

import com.epam.web.service.ServiceException;

public class CommandNotExistException extends ServiceException {
    public CommandNotExistException() {
    }

    public CommandNotExistException(String message) {
        super(message);
    }

    public CommandNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
