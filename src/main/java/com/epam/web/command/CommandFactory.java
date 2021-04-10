package com.epam.web.command;

import com.epam.web.command.impl.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.service.*;

public class CommandFactory {

    public static final String DELETE_FILM = "deleteFilm";
    private static final String PERSONAL = "personalPage";
    private static final String SHOW_FILM_PAGE = "/WEB-INF/view/showFilm.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String USER_MANAGE_PAGE = "/WEB-INF/view/userManage.jsp";
    private static final String PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String CREATE_FILM_PAGE = "/WEB-INF/view/createFilm.jsp";
    private static final String EDIT_FILM_PAGE = "/WEB-INF/view/editFilm.jsp";

    private static final String CHANGE_LANGUAGE = "changeLanguage";
    private static final String LOGIN = "login";
    private static final String LOGOUT = "logout";
    private static final String LOGIN_PAGE_COMMAND = "loginPage";
    private static final String MAIN_PAGE_COMMAND = "mainPage";
    private static final String USER_MANAGE_PAGE_COMMAND = "userManagePage";
    private static final String EDIT_FILM = "editFilm";
    private static final String EDIT_FILM_PAGE_COMMAND = "editFilmPage";
    private static final String SHOW_FILM_PAGE_COMMAND = "showMoviePage";
    private static final String PERSONAL_PAGE_COMMAND = "showPersonalPage";
    private static final String CREATE_FILM = "showAddPage";
    private static final String FILMS_PAGE = "showFilmsPage";
    private static final String MOVIE = "movie";
    private static final String RATE_FILM = "rateFilm";
    private static final String REVIEW_FILM = "reviewFilm";
    private static final String MANAGE_USERS = "manageUsers";
    private static final String CHANGE_USER_RATING = "changeUserRating";
    private static final String CHANGE_USER_STATUS = "changeUserStatus";
    private static final String ADD_FILM = "addFilm";
    private static final String SAVE_FILM = "saveFilm";
    public static final String UPDATE_FILM = "updateFilm";


    public Command create(String commandName) throws CommandNotExistException, ServiceException {
        DaoHelperFactory helperFactory = new DaoHelperFactory();
        switch (commandName) {
            case LOGIN_PAGE_COMMAND:
                return new ShowPageCommand(LOGIN_PAGE);
            case MAIN_PAGE_COMMAND:
                return new ShowPageCommand(MAIN_PAGE);
            case USER_MANAGE_PAGE_COMMAND:
                return new ShowPageCommand(USER_MANAGE_PAGE);
            case SHOW_FILM_PAGE_COMMAND:
                return new ShowPageCommand(SHOW_FILM_PAGE);
            case PERSONAL_PAGE_COMMAND:
                return new ShowPageCommand(PERSONAL_PAGE);
            case CREATE_FILM:
                return new ShowPageCommand(CREATE_FILM_PAGE);
            case EDIT_FILM_PAGE_COMMAND:
                return new ShowPageCommand(EDIT_FILM_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case LOGIN:
                UserService service = new UserService(helperFactory);
                return new LoginCommand(service);
            case PERSONAL:
                return new PersonalPageCommand();
            case FILMS_PAGE:
                FilmService filmsService = new FilmService(helperFactory);
                return new GetFilmsCommand(filmsService);
            case MOVIE:
                FilmService filmService = new FilmService(helperFactory);
                return new GetFilmCommand(filmService);
            case RATE_FILM:
                RatingService ratingService = new RatingService(helperFactory);
                return new RateFilmCommand(ratingService);
            case REVIEW_FILM:
                ReviewService reviewService = new ReviewService(helperFactory);
                return new ReviewFilmCommand(reviewService);
            case MANAGE_USERS:
                UserService userService = new UserService(helperFactory);
                return new ManageUsersCommand(userService);
            case CHANGE_USER_RATING:
                UserService changeRatingService =new UserService(helperFactory);
                return new ChangeUserRatingCommand(changeRatingService);
            case CHANGE_USER_STATUS:
                UserService changeStatusService = new UserService(helperFactory);
                return new ChangeUserStatusCommand(changeStatusService);
            case ADD_FILM:
                GenreService genreService = new GenreService(helperFactory);
                return new AddFilmCommand(genreService);
            case SAVE_FILM:
                FilmService saveFilmService = new FilmService(helperFactory);
                return new SaveFilmCommand(saveFilmService);
            case EDIT_FILM:
                FilmService editFilmService = new FilmService(helperFactory);
                GenreService filmGenreService = new GenreService(helperFactory);
                return new EditFilmCommand(editFilmService, filmGenreService);
            case DELETE_FILM:
                FilmService deleteFilmService = new FilmService(helperFactory);
                return new DeleteFilmCommand(deleteFilmService);
            case UPDATE_FILM:
                FilmService updateFilmService = new FilmService(helperFactory);
                return new UpdateFilmCommand(updateFilmService);
            default:
                throw new CommandNotExistException("Unknown type = " + commandName);
        }
    }
}
