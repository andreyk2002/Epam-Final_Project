package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandNotExistException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    private final CommandFactory factory = new CommandFactory();

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp);
        } catch (CommandNotExistException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            process(req, resp);
        } catch (CommandNotExistException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, CommandNotExistException {
        String commandType = request.getParameter("commandName");
        Command command = factory.create(commandType);
        String view = command.execute(request, response);
        request.getRequestDispatcher(view).forward(request, response);
    }
}
