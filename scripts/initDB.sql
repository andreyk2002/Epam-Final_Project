DROP TABLE IF EXISTS films;
DROP TABLE IF EXISTS genres;

CREATE TABLE genres
(
    ID   bigint NOT NULL AUTO_INCREMENT,
    Name varchar(255) DEFAULT NULL,
    PRIMARY KEY (ID),
    KEY    idx_genres_ID (ID)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE films
(
    ID          bigint       NOT NULL AUTO_INCREMENT,
    Name        varchar(255) NOT NULL,
    ImagePath   varchar(255) NOT NULL,
    Description text,
    GenreID     bigint DEFAULT NULL,
    PRIMARY KEY (ID),
    KEY           FK_FilmsGenreID (GenreID),
    CONSTRAINT FK_FilmsGenreID FOREIGN KEY (GenreID) REFERENCES genres (ID)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE users
(
    ID       bigint       NOT NULL AUTO_INCREMENT,
    Login    varchar(50)  NOT NULL,
    Password varchar(255) NOT NULL,
    Role     enum('ADMIN','USER') NOT NULL,
    Rating   int DEFAULT '50',
    PRIMARY KEY (ID)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE filmsratings
(
    UserID bigint NOT NULL,
    FilmID bigint NOT NULL,
    Rating double DEFAULT NULL,
    PRIMARY KEY (UserID, FilmID),
    KEY      FK_FILMSRATINGS_FILMS (FilmID),
    CONSTRAINT FK_FILMSRATINGS_FILMS FOREIGN KEY (FilmID) REFERENCES films (ID),
    CONSTRAINT FK_FILMSRATINGS_Users FOREIGN KEY (UserID) REFERENCES users (ID),
    CONSTRAINT filmsratings_chk_1 CHECK (((rating >= 0) and (rating <= 5.0)))
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE reviews
(
    ID bigint PRIMARY KEY auto_increment,
    UserID bigint NOT NULL,
    FilmID bigint NOT NULL,
    Review TEXT
);

alter table reviews
    add constraint FK_FilmsFilmID
        foreign key (FilmID) references films(ID);

alter table reviews
    add constraint FK_UserID
        foreign key (UserID) references users(ID);


Create VIEW film_view AS
SELECT f.Name AS filmName, f.ImagePath, f.Description, g.Name AS genreNamefilm_view, avg(r.Rating) AS rating
FROM films f
         JOIN genres g
              ON f.GenreID = g.ID
         LEFT JOIN filmsratings r
                   ON f.ID = r.FilmID
GROUP BY f.Name

