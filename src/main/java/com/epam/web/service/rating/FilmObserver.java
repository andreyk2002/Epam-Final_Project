package com.epam.web.service.rating;

public interface FilmObserver {

    void notifyFilmAdded(long id);

    void notifyFilmDeleted(long id);
}
