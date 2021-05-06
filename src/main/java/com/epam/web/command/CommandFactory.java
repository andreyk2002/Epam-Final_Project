package com.epam.web.command;

import com.epam.web.command.impl.*;
import com.epam.web.command.impl.pages.GetFilmPageCommand;
import com.epam.web.command.impl.pages.ShowAddPageCommand;
import com.epam.web.command.impl.pages.ShowEditPageCommand;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.parser.FormParser;
import com.epam.web.security.XssProtector;
import com.epam.web.service.*;
import com.epam.web.validator.RatingValidator;
import com.epam.web.validator.UserRatingValidator;

public class CommandFactory {


    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String USER_MANAGE_PAGE = "/WEB-INF/view/userManage.jsp";
    private static final String PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";

    private static final String SEARCH_PAGE = "/WEB-INF/view/searchPage.jsp";

    private final DaoHelperFactory helperFactory = new DaoHelperFactory();
    private final UserRatingValidator ratingValidator = new UserRatingValidator();


    public Command create(String commandName) throws ServiceException {
        XssProtector protect = new XssProtector();
        Commands currentCommand = Commands.parse(commandName);
        switch (currentCommand) {
            case LOGIN_PAGE_COMMAND:
                return new ShowPageCommand(LOGIN_PAGE);
            case MAIN_PAGE_COMMAND:
                return new ShowPageCommand(MAIN_PAGE);
            case USER_MANAGE_PAGE_COMMAND:
                return new ShowPageCommand(USER_MANAGE_PAGE);
            case SHOW_FILM_PAGE_COMMAND:
                FilmService getFilmService = new FilmService(helperFactory, protect);
                return new GetFilmPageCommand(getFilmService);
            case PERSONAL_PAGE_COMMAND:
                return new ShowPageCommand(PERSONAL_PAGE);
            case ADD_FILM_PAGE:
                GenreService addGenreService = new GenreService(helperFactory);
                return new ShowAddPageCommand(addGenreService);
            case EDIT_FILM_PAGE_COMMAND:
                FilmService editFilmService = new FilmService(helperFactory, protect);
                GenreService loadGenreService = new GenreService(helperFactory);
                return new ShowEditPageCommand(editFilmService, loadGenreService);
            case SEARCH_PAGE_COMMAND:
                return new ShowPageCommand(SEARCH_PAGE);
            case LOGOUT:
                return new LogoutCommand();
            case SEARCH_FILM:
                FilmService searchFilmService = new FilmService(helperFactory, protect);
                return new SearchFilmCommand(searchFilmService);
            case SEARCH_BY_GENRES:
                FilmService genreSearchFilmService = new FilmService(helperFactory, protect);
                return new SearchByGenreCommand(genreSearchFilmService);
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
                GenreService genreService = new GenreService(helperFactory);
                FilmService filmsService = new FilmService(helperFactory, protect);
                return new GetFilmsCommand(filmsService, genreService);
            case GET_MOVIE:
                return new GetFilmCommand();
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
                String pageName = Commands.ADD_FILM_PAGE.getName();
                return new RedirectToPageCommand(pageName);
            case SAVE_FILM:
                FilmService saveFilmService = new FilmService(helperFactory, protect);
                FormParser parser = new FormParser();
                return new SaveFilmCommand(saveFilmService, parser);
            case EDIT_FILM:
                return new EditFilmPageCommand();
            case DELETE_FILM:
                FilmService deleteFilmService = new FilmService(helperFactory, protect);
                return new DeleteFilmCommand(deleteFilmService);
            case UPDATE_FILM:
                FormParser formParser = new FormParser();
                FilmService updateFilmService = new FilmService(helperFactory, protect);
                return new UpdateFilmCommand(updateFilmService, formParser);
            default:
                throw new CommandNotExistException("Unknown type = " + commandName);
        }
    }
}
