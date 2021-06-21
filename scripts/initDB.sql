
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `ID` bigint NOT NULL AUTO_INCREMENT,
                         `Login` varchar(50) NOT NULL,
                         `Password` varchar(255) NOT NULL,
                         `Role` enum('ADMIN','USER') NOT NULL,
                         `Rating` int DEFAULT '50',
                         `Blocked` tinyint(1) DEFAULT '0',
                         PRIMARY KEY (`ID`)
);


DROP TABLE IF EXISTS films;
CREATE TABLE films(
                         `ID` bigint NOT NULL AUTO_INCREMENT,
                         `Name` varchar(255) NOT NULL,
                         `ImagePath` varchar(255) NOT NULL,
                         `Description` text,
                         PRIMARY KEY (`ID`),
                         FULLTEXT KEY `Name` (`Name`)
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres (
                        `ID` bigint NOT NULL AUTO_INCREMENT,
                        `Name` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`ID`),
                        KEY `idx_genres_ID` (`ID`)
);

DROP TABLE IF EXISTS filmsgenres;

CREATE TABLE filmsgenres (
                               `ID` bigint NOT NULL AUTO_INCREMENT,
                               `FilmID` bigint DEFAULT NULL,
                               `GenreID` bigint DEFAULT NULL,
                               PRIMARY KEY (`ID`),
                               KEY `GenreID_FK` (`GenreID`),
                               KEY `FilmID_FK` (`FilmID`),
                               CONSTRAINT `FilmID_FK` FOREIGN KEY (`FilmID`) REFERENCES `films` (`ID`),
                               CONSTRAINT `GenreID_FK` FOREIGN KEY (`GenreID`) REFERENCES `genres` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS filmsratings;
CREATE TABLE filmsratings (
                                `ID` bigint NOT NULL AUTO_INCREMENT,
                                `UserID` bigint NOT NULL,
                                `FilmID` bigint NOT NULL,
                                `Rating` double DEFAULT NULL,
                                PRIMARY KEY (`ID`),
                                UNIQUE KEY `FilmID_UserID` (`FilmID`,`UserID`),
                                KEY `FK_FILMSRATINGS_Users` (`UserID`),
                                CONSTRAINT `FK_FILMSRATINGS_FILMS` FOREIGN KEY (`FilmID`) REFERENCES `films` (`ID`),
                                CONSTRAINT `FK_FILMSRATINGS_Users` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`),
                                CONSTRAINT `filmsratings_chk_1` CHECK (((`rating` >= 0) and (`rating` <= 5.0)))
);


DROP TABLE IF EXISTS reviews;
CREATE TABLE reviews (
                           `ID` bigint NOT NULL AUTO_INCREMENT,
                           `UserID` bigint NOT NULL,
                           `FilmID` bigint NOT NULL,
                           `Review` text,
                           PRIMARY KEY (`ID`),
                           KEY `FK_FilmsFilmID` (`FilmID`),
                           KEY `FK_UserID` (`UserID`),
                           CONSTRAINT `FK_FilmsFilmID` FOREIGN KEY (`FilmID`) REFERENCES `films` (`ID`),
                           CONSTRAINT `FK_UserID` FOREIGN KEY (`UserID`) REFERENCES `users` (`ID`)
);


