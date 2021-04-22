package com.epam.web.command;

import com.epam.web.command.impl.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.parser.FormParser;
import com.epam.web.security.XssProtector;
import com.epam.web.service.*;
import com.epam.web.validator.RatingValidator;
import com.epam.web.validator.UserRatingValidator;

public class CommandFactory {

    private static final String SHOW_FILM_PAGE = "/WEB-INF/view/showFilm.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String USER_MANAGE_PAGE = "/WEB-INF/view/userManage.jsp";
    private static final String PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String CREATE_FILM_PAGE = "/WEB-INF/view/createFilm.jsp";
    private static final String EDIT_FILM_PAGE = "/WEB-INF/view/editFilm.jsp";

    private static final String DELETE_FILM = "deleteFilm";
    private static final String PERSONAL = "personalPage";
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
    private static final String UPDATE_FILM = "updateFilm";
    private static final String GET_USER = "getUser";

    private final DaoHelperFactory helperFactory = new DaoHelperFactory();
    private final UserRatingValidator ratingValidator = new UserRatingValidator();


    public Command create(String commandName) throws ServiceException {
        XssProtector protect = new XssProtector();
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
                UserService service = new UserService(helperFactory, ratingValidator);
                return new LoginCommand(service);
            case GET_USER:
                UserService getUserService = new UserService(helperFactory, ratingValidator);
                return new GetUserCommand(getUserService);
            case PERSONAL:
                return new PersonalPageCommand();
            case FILMS_PAGE:
                FilmService filmsService = new FilmService(helperFactory, protect);
                return new GetFilmsCommand(filmsService);
            case MOVIE:
                FilmService filmService = new FilmService(helperFactory, protect);
                return new GetFilmCommand(filmService);
            case RATE_FILM:
                RatingValidator validator = new RatingValidator();
                RatingService ratingService = new RatingService(helperFactory, validator);
                return new RateFilmCommand(ratingService);
            case REVIEW_FILM:
                ReviewService reviewService = new ReviewService(helperFactory, protect);
                return new ReviewFilmCommand(reviewService);
            case MANAGE_USERS:
                UserService userService = new UserService(helperFactory, ratingValidator);
                return new ManageUsersCommand(userService);
            case CHANGE_USER_RATING:
                UserService changeRatingService = new UserService(helperFactory, ratingValidator);
                return new ChangeUserRatingCommand(changeRatingService);
            case CHANGE_USER_STATUS:
                UserService changeStatusService = new UserService(helperFactory, ratingValidator);
                return new ChangeUserStatusCommand(changeStatusService);
            case ADD_FILM:
                GenreService genreService = new GenreService(helperFactory);
                return new AddFilmPageCommand(genreService);
            case SAVE_FILM:
                FilmService saveFilmService = new FilmService(helperFactory, protect);
                FormParser parser = new FormParser();
                return new AddFilmCommand(saveFilmService, parser);
            case EDIT_FILM:
                FilmService editFilmService = new FilmService(helperFactory, protect);
                GenreService filmGenreService = new GenreService(helperFactory);
                return new EditFilmPageCommand(editFilmService, filmGenreService);
            case DELETE_FILM:
                FilmService deleteFilmService = new FilmService(helperFactory, protect);
                return new DeleteFilmCommand(deleteFilmService);
            case UPDATE_FILM:
                FormParser formParser = new FormParser();
                FilmService updateFilmService = new FilmService(helperFactory, protect);
                return new EditFilmCommand(updateFilmService, formParser);
            default:
                throw new CommandNotExistException("Unknown type = " + commandName);
        }
    }
}
