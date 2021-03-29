package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {


    public static final String LOCAL = "local";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String COMMAND = "/controller?commandName=";
    public static final String PAGE = "&pageNumber=";
    private static final String DEFAULT_PAGE = "loginPage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String local = request.getParameter(LOCAL);
        session.setAttribute(LOCAL, local);
        String commandPath = request.getContextPath() + COMMAND;
        String page = request.getParameter(CURRENT_PAGE);
        if (!"".equals(page)) {
            commandPath += page;
        } else {
            commandPath += DEFAULT_PAGE;
        }
        String pageNumber = request.getParameter(PAGE_NUMBER);
        if (!"".equals(pageNumber)) {
            commandPath += PAGE + pageNumber;
        }
        return CommandResult.redirect(commandPath);
    }
}
