package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ChangeLanguageCommand implements Command {


    public static final String LOCAL = "local";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String COMMAND = "/controller?commandName=";
    public static final String PAGE = "&pageNumber=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String local = request.getParameter(LOCAL);
        session.setAttribute(LOCAL, local);
        String currentCommand = request.getParameter(CURRENT_PAGE);
        Optional<String> pageNumber = Optional.ofNullable(request.getParameter(PAGE_NUMBER));
        return pageNumber.map(s -> CommandResult.redirect(request.getContextPath() + COMMAND + currentCommand + PAGE + s))
                .orElseGet(() -> CommandResult.redirect(request.getContextPath() + COMMAND + currentCommand));
    }
}
