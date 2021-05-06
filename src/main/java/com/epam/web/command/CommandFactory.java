package com.epam.web.command;

import com.epam.web.command.impl.*;
import com.epam.web.command.impl.pages.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.parser.FormParser;
import com.epam.web.security.XssProtector;
import com.epam.web.service.*;
import com.epam.web.validator.RatingValidator;
import com.epam.web.validator.UserRatingValidator;

public class CommandFactory {


    private static final String PERSONAL_PAGE = "/WEB-INF/view/personal.jsp";
    private static final String LOGIN_PAGE = "/index.jsp";
    private static final String SEARCH_PAGE = "/WEB-INF/view/searchPage.jsp";

    private final DaoHelperFactory helperFactory = new DaoHelperFactory();
    private final UserRatingValidator userRatingValidator = new UserRatingValidator();
    private final RatingValidator ratingValidator = new RatingValidator();

    public Command create(String commandName) throws ServiceException {
        XssProtector protect = new XssProtector();
        Commands currentCommand = Commands.parse(commandName);
        switch (currentCommand) {
            case LOGIN_PAGE_COMMAND:
                return new ShowPageCommand(LOGIN_PAGE);
            case MAIN_PAGE_COMMAND:
                GenreService genreService = new GenreService(helperFactory);
                FilmService filmsService = new FilmService(helperFactory, protect);
                return new MainPageCommand(filmsService, genreService);
            case USER_MANAGE_PAGE_COMMAND:
                UserRatingValidator validator = new UserRatingValidator();
                UserService showUsersService = new UserService(helperFactory, validator);
                return new UserManagePageCommand(showUsersService);
            case SHOW_FILM_PAGE_COMMAND:
                FilmService getFilmService = new FilmService(helperFactory, protect);
                return new FilmPageCommand(getFilmService);
            case PERSONAL_PAGE_COMMAND:
                return new ShowPageCommand(PERSONAL_PAGE);
            case ADD_FILM_PAGE:
                GenreService addGenreService = new GenreService(helperFactory);
                return new AddPageCommand(addGenreService);
            case EDIT_FILM_PAGE_COMMAND:
                FilmService editFilmService = new FilmService(helperFactory, protect);
                GenreService loadGenreService = new GenreService(helperFactory);
                return new EditPageCommand(editFilmService, loadGenreService);
            case SEARCH_PAGE_COMMAND:
                FilmService searchFilmService = new FilmService(helperFactory, protect);
                return new FilmSearchPageCommand(searchFilmService);
            case GENRE_SEARCH_PAGE:
                FilmService genreSearchFilmService = new FilmService(helperFactory, protect);
                return new GenreSearchPageCommand(genreSearchFilmService);
            case LOGOUT:
                return new LogoutCommand();
            case SEARCH_FILM:
                return new SearchFilmCommand();
            case SEARCH_BY_GENRES:
                return new SearchByGenreCommand();
            case CHANGE_LANGUAGE:
                return new ChangeLanguageCommand();
            case LOGIN:
                UserService service = new UserService(helperFactory, userRatingValidator);
                return new LoginCommand(service);
            case GET_USER:
                UserService getUserService = new UserService(helperFactory, userRatingValidator);
                return new GetUserCommand(getUserService);
            case PERSONAL:
                String personalPage = Commands.PERSONAL_PAGE_COMMAND.getName();
                return new RedirectToPageCommand(personalPage);
            case FILMS_PAGE:
                return new GetFilmsCommand();
            case GET_MOVIE:
                return new GetFilmCommand();
            case RATE_FILM:
                RatingService ratingService = new RatingService(helperFactory, ratingValidator);
                return new RateFilmCommand(ratingService);
            case REVIEW_FILM:
                ReviewService reviewService = new ReviewService(helperFactory, protect);
                return new ReviewFilmCommand(reviewService);
            case MANAGE_USERS:
                String redirectPage = Commands.USER_MANAGE_PAGE_COMMAND.getName();
                return new RedirectToPageCommand(redirectPage);
            case CHANGE_USER_RATING:
                UserService changeRatingService = new UserService(helperFactory, userRatingValidator);
                return new ChangeUserRatingCommand(changeRatingService);
            case CHANGE_USER_STATUS:
                UserService changeStatusService = new UserService(helperFactory, userRatingValidator);
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
