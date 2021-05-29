package com.epam.web.command.impl.forward;

import com.epam.web.command.Command;
import com.epam.web.command.CommandResult;
import com.epam.web.command.impl.Pages;
import com.epam.web.dto.FilmDto;
import com.epam.web.entity.User;
import com.epam.web.service.FilmService;
import com.epam.web.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class FilmPageCommand implements Command {


    private final FilmService service;

    public FilmPageCommand(FilmService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String idString = request.getParameter("filmId");
        long id = Long.parseLong(idString);
        Optional<FilmDto> movieOptional = service.getFilmDtoById(id, user.getId());
        movieOptional.ifPresentOrElse(movie -> request.setAttribute("film", movie),
                () -> request.setAttribute("errorMessage", "Film with id " + id + " not found"));
        return CommandResult.forward(Pages.SHOW_FILM);
    }
}
