package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandNotExistException;
import com.epam.web.command.CommandResult;
import com.epam.web.dao.DaoException;
import com.epam.web.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private static final String ERROR_PAGE = "/error.jsp";

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
//        HttpSession session = request.getSession(true);
//        String local = request.getParameter("local");
//        session.setAttribute("local", local);
        String commandType = request.getParameter("commandName");
        Command command;
        String page;

        boolean isRedirect = false;
        try {
            command = factory.create(commandType);
            CommandResult result = command.execute(request, response);
            page = result.getPage();
            isRedirect = result.isRedirect();
        } catch (CommandNotExistException | ServiceException | DaoException e) {
            request.setAttribute("errorMessage", e.getMessage());
            LOGGER.error(e.getMessage(), e);
            page = ERROR_PAGE;
        }
        if (!isRedirect) {
            forward(request, response, page);
        } else {
            redirect(response, page);
        }
    }

    private void redirect(HttpServletResponse response, String command) throws IOException {
        response.sendRedirect(command);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
