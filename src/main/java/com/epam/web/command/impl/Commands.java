package com.epam.web.command.impl;

import com.epam.web.service.ServiceException;

public enum Commands {

   MAIN_PAGE_PATH("/WEB-INF/view/main.jsp"),

   USER_MANAGE_PAGE_PATH("/WEB-INF/view/userManage.jsp"),

   PERSONAL_PAGE_PATH("/WEB-INF/view/personal.jsp"),

   LOGIN_PAGE_PATH("/index.jsp"),

   SEARCH_PAGE_PATH("/WEB-INF/view/searchPage.jsp"),

   SHOW_FILM_PATH("/WEB-INF/view/showFilm.jsp"),

   CREATE_FILM_PATH("/WEB-INF/view/createFilm.jsp"),

   EDIT_FILM_PATH("/WEB-INF/view/editFilm.jsp"),

   DELETE_FILM("deleteFilm"),

   CHANGE_LANGUAGE("changeLanguage"),

   LOGIN("login"),

   LOGOUT("logout"),

   EDIT_FILM("editFilm"),

   GET_MOVIE("movie"),

   RATE_FILM("rateFilm"),

   REVIEW_FILM("reviewFilm"),

   MANAGE_USERS("manageUsers"),

   CHANGE_USER_RATING("changeUserRating"),

   CHANGE_USER_STATUS("changeUserStatus"),

   ADD_FILM("addFilm"),

   SAVE_FILM("saveFilm"),

   UPDATE_FILM("updateFilm"),

   GET_USER("updateUser"),

   SEARCH_FILM("searchFilm"),

   LOGIN_PAGE_COMMAND("loginPage"),

   MAIN_PAGE_COMMAND("mainPage"),

   USER_MANAGE_PAGE_COMMAND("userManagePage"),

   EDIT_FILM_PAGE_COMMAND("editFilmPage"),

   SHOW_FILM_PAGE_COMMAND("showFilmPage"),

   PERSONAL_PAGE_COMMAND("showPersonalPage"),

   ADD_FILM_PAGE("showAddPage"),

   FILMS_PAGE("showFilmsPage"),

   SEARCH_PAGE_COMMAND("searchPage"),

   SEARCH_BY_GENRES("searchByGenre"),

   CHANGE_PAGE("changePageNumber"),

   GENRE_SEARCH_PAGE("genreSearchPage");

    private final String name;

    Commands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

   public static Commands parse(String type) throws ServiceException {
      for (Commands paymentType : Commands.values()) {
         if (paymentType.getName().equals(type)) {
            return paymentType;
         }
      }
     throw new ServiceException("No such command");
   }
}
