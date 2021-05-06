package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandResult;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private static final String ERROR_PAGE = "/error.jsp";
    private static final String COMMAND_NAME = "commandName";
    public static final String COMMAND = "/controller?commandName=";
    public static final String EXCEPTION_BUNDLE_KEY = "local.somethingWrong";

    private final CommandFactory factory = new CommandFactory();

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        process(req, resp);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandType = request.getParameter(COMMAND_NAME);
        Command command;
        String page;

        boolean isRedirect = false;
        try {
            command = factory.create(commandType);
            CommandResult result = command.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        }  catch (Exception e) {
            request.setAttribute("errorMessage", EXCEPTION_BUNDLE_KEY);
            LOGGER.error(e.getMessage(), e);
            page = ERROR_PAGE;
        }
        if (!isRedirect) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + COMMAND + page);
        }
    }
}
