package com.epam.web.command;

import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.service.MovieService;
import com.epam.web.service.ServiceException;
import com.epam.web.service.UserService;

public class CommandFactory {

    private static final String SHOW_FILM_PAGE = "/WEB-INF/view/showFilm.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String USER_MANAGE_PAGE = "/WEB-INF/view/userManage.jsp";
    private static final String PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    private static final String FILM_MANAGE_PAGE = "/WEB-INF/view/filmManage.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";


    public Command create(String commandName) throws CommandNotExistException, ServiceException {
        switch (commandName) {
            case "changeLanguage":
                return new ChangeLanguageCommand();
            case "login":
                DaoHelperFactory factory = new DaoHelperFactory();
                UserService service = new UserService(factory);
                return new LoginCommand(service);
            case "loginPage":
                return new ShowPageCommand(LOGIN_PAGE);
            case "logout":
                return new LogoutCommand();
            case "mainPage":
                return new ShowPageCommand(MAIN_PAGE);
            case "personalPage":
                return new ShowPageCommand(PERSONAL_PAGE);
            case "userManagePage":
                return new ShowPageCommand(USER_MANAGE_PAGE);
            case "filmManagePage":
                return new ShowPageCommand(FILM_MANAGE_PAGE);
            case "showMoviePage":
                return new ShowPageCommand(SHOW_FILM_PAGE);
            case "movie":
                MovieService movieService = getMovieService();
                return new FilmShowCommand(movieService);
            case "showFilmsPage":
                MovieService moviesService = getMovieService();
                return new ShowFilmsPageCommand(moviesService);
            default:
                throw new CommandNotExistException("Unknown type = " + commandName);
        }
    }

    private MovieService getMovieService() throws ServiceException {
        DaoHelperFactory helperFactory = new DaoHelperFactory();
        return new MovieService(helperFactory);
    }


}
