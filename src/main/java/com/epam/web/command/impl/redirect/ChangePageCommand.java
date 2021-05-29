package com.epam.web.command.impl.redirect;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Commands;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangePageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageParam = request.getParameter("pageNumber");
        Integer page = Integer.parseInt(pageParam);
        HttpSession session = request.getSession();
        session.setAttribute("pageNumber", page);
        return CommandResult.redirect(Commands.FILMS_PAGE);
    }
}
