package com.epam.web.command.impl;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    public static final String LOCAL = "local";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String PAGE = "&pageNumber=";
    private static final String DEFAULT_PAGE = "loginPage";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String local = request.getParameter(LOCAL);
        session.setAttribute(LOCAL, local);

        String page = request.getParameter(CURRENT_PAGE);
        String result = "".equals(page)? DEFAULT_PAGE : page;
        String pageNumber = request.getParameter(PAGE_NUMBER);
        if (!"".equals(pageNumber)) {
            result += PAGE + pageNumber;
        }
        return CommandResult.redirect(result);
    }
}
