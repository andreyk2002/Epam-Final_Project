package com.epam.web.command;

import com.epam.web.command.impl.Commands;
import com.epam.web.command.impl.Pages;
import com.epam.web.command.impl.forward.*;
import com.epam.web.command.impl.redirect.*;
import com.epam.web.dao.factory.DaoHelperFactory;
import com.epam.web.parser.FormParser;
import com.epam.web.security.XssProtector;
import com.epam.web.service.*;
import com.epam.web.service.rating.FilmObserver;
import com.epam.web.service.rating.RatingManager;
import com.epam.web.validator.RatingValidator;
import com.epam.web.validator.UserRatingValidator;

public class CommandFactory {




    private final DaoHelperFactory helperFactory = new DaoHelperFactory();
    private final UserRatingValidator userRatingValidator = new UserRatingValidator();
    private final RatingValidator ratingValidator = new RatingValidator();

    public Command create(String commandName) throws ServiceException {
        XssProtector protect = new XssProtector();
        FilmObserver filmObserver = RatingManager.getInstance();
        switch (commandName) {
            case Commands.LOGIN_PAGE:
                return new ShowPageCommand(Pages.LOGIN_PAGE);
            case Commands.MAIN_PAGE_COMMAND:
                GenreService genreService = new GenreService(helperFactory);
                FilmService filmsService = new FilmService(helperFactory, protect, filmObserver);
                return new MainPageCommand(filmsService, genreService);
            case Commands.USER_MANAGE_PAGE_COMMAND:
                UserRatingValidator validator = new UserRatingValidator();
                UserService showUsersService = new UserService(helperFactory, validator);
                return new UserManagePageCommand(showUsersService);
            case Commands.SHOW_FILM_PAGE_COMMAND:
                FilmService getFilmService = new FilmService(helperFactory, protect, filmObserver);
                return new FilmPageCommand(getFilmService);
            case Commands.PERSONAL_PAGE_COMMAND:
                return new ShowPageCommand(Pages.PERSONAL_PAGE);
            case Commands.ADD_FILM_PAGE:
                GenreService addGenreService = new GenreService(helperFactory);
                return new AddPageCommand(addGenreService);
            case Commands.EDIT_FILM_PAGE_COMMAND:
                FilmService editFilmService = new FilmService(helperFactory, protect, filmObserver);
                GenreService loadGenreService = new GenreService(helperFactory);
                return new EditPageCommand(editFilmService, loadGenreService);
            case Commands.SEARCH_PAGE_COMMAND:
                FilmService searchFilmService = new FilmService(helperFactory, protect, filmObserver);
                GenreService genresService = new GenreService(helperFactory);
                return new FilmSearchPageCommand(searchFilmService, genresService);
            case Commands.GENRE_SEARCH_PAGE:
                FilmService genreSearchFilmService = new FilmService(helperFactory, protect, filmObserver);
                GenreService allGenreService = new GenreService(helperFactory);
                return new GenreSearchPageCommand(genreSearchFilmService, allGenreService);
            case Commands.LOGOUT:
                return new LogoutCommand();
            case Commands.SEARCH_FILM:
                return new SearchFilmCommand();
            case Commands.SEARCH_BY_GENRES:
                return new SearchByGenreCommand();
            case Commands.LOGIN:
                UserService service = new UserService(helperFactory, userRatingValidator);
                return new LoginCommand(service);
            case Commands.GET_USER:
                UserService getUserService = new UserService(helperFactory, userRatingValidator);
                return new GetUserCommand(getUserService);
            case Commands.FILMS_PAGE:
                String page = Commands.MAIN_PAGE_COMMAND;
                return new RedirectToPageCommand(page);
            case Commands.GET_MOVIE:
                return new GetFilmCommand();
            case Commands.RATE_FILM:
                RatingService ratingService = new RatingService(helperFactory, ratingValidator);
                return new RateFilmCommand(ratingService);
            case Commands.REVIEW_FILM:
                ReviewService reviewService = new ReviewService(helperFactory, protect);
                return new ReviewFilmCommand(reviewService);
            case Commands.MANAGE_USERS:
                String redirectPage = Commands.USER_MANAGE_PAGE_COMMAND;
                return new RedirectToPageCommand(redirectPage);
            case Commands.CHANGE_USER_RATING:
                UserService changeRatingService = new UserService(helperFactory, userRatingValidator);
                return new ChangeUserRatingCommand(changeRatingService);
            case Commands.CHANGE_USER_STATUS:
                UserService changeStatusService = new UserService(helperFactory, userRatingValidator);
                return new ChangeUserStatusCommand(changeStatusService);
            case Commands.ADD_FILM:
                String pageName = Commands.ADD_FILM_PAGE;
                return new RedirectToPageCommand(pageName);
            case Commands.SAVE_FILM:
                FilmService saveFilmService = new FilmService(helperFactory, protect, filmObserver);
                FormParser parser = new FormParser();
                return new SaveFilmCommand(saveFilmService, parser);
            case Commands.CHANGE_PAGE:
                return new ChangePageCommand();
            case Commands.EDIT_FILM:
                return new EditFilmPageCommand();
            case Commands.DELETE_FILM:
                FilmService deleteFilmService = new FilmService(helperFactory, protect, filmObserver);
                return new DeleteFilmCommand(deleteFilmService);
            case Commands.UPDATE_FILM:
                FormParser formParser = new FormParser();
                FilmService updateFilmService = new FilmService(helperFactory, protect, filmObserver);
                return new UpdateFilmCommand(updateFilmService, formParser);
            default:
                throw new CommandNotExistException("Unknown type = " + commandName);
        }
    }
}
