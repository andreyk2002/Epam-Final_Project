package com.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class ChangeLanguageCommand implements Command {


    public static final String LOCAL = "local";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String local = request.getParameter(LOCAL);
        session.setAttribute(LOCAL, local);
        String currentCommand = request.getParameter("currentPage");
        Optional<String> pageNumber = Optional.ofNullable(request.getParameter("pageNumber"));
        return pageNumber.map(s -> CommandResult.redirect(request.getContextPath() + "/controller?commandName=" + currentCommand + "&pageNumber=" + s))
                .orElseGet(() -> CommandResult.redirect(request.getContextPath() + "/controller?commandName=" + currentCommand));
    }
}
