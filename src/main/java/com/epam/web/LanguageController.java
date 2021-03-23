package com.epam.web;

import com.epam.web.command.Command;
import com.epam.web.command.CommandFactory;
import com.epam.web.command.CommandNotExistException;
import com.epam.web.command.CommandResult;
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

public class LanguageController extends HttpServlet {
    private static final String PAGE = "/index.jsp";

    private final CommandFactory factory = new CommandFactory();

    public LanguageController() {
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
        HttpSession session = request.getSession(true);
        String local = request.getParameter("local");
        session.setAttribute("local", local);;
        request.getRequestDispatcher(PAGE).forward(request, response);
    }

}