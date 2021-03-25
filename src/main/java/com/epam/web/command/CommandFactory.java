package com.epam.web.command;

import com.epam.web.dao.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.service.MovieService;
import com.epam.web.service.UserService;

public class CommandFactory {

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String USER_MANAGE_PAGE = "/WEB-INF/view/userManage.jsp";
    private static final String PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    private static final String FILM_MANAGE_PAGE = "/WEB-INF/view/filmManage.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";

    public Command create(String commandName) throws CommandNotExistException, DaoException {
        switch (commandName) {
            case "login":
                DaoHelperFactory factory = new DaoHelperFactory();
                DaoHelper helper = factory.create();
                UserDao userDao = helper.createUserDao();
                UserService service = new UserService(userDao);
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
//            case "showFilmsPage":
//                MovieDao dao = new ListBasedMovieDao();
//                MovieService movieService = new MovieService(dao);
//                return new ShowFilmsPageCommand(movieService);
            default:
                throw new CommandNotExistException("Unknown type = " + commandName);
        }
    }

}
