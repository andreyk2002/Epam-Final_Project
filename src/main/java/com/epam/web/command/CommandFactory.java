package com.epam.web.command;

import com.epam.web.service.UserService;

public class CommandFactory {

    public Command create(String commandName) throws CommandNotExistException {
        switch (commandName){
            case "login":
                return new LoginCommand(new UserService());
            default:
                throw new CommandNotExistException("Unknown type = " + commandName);
        }
    }
}
