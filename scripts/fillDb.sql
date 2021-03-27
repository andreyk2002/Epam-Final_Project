INSERT INTO genres
VALUES (1, 'Action film'),
       (2, 'Comedy'),
       (3, 'Drama'),
       (4, 'Science fiction'),
       (5, 'Detective'),
       (6, 'Thriller');


INSERT INTO users
VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 'ADMIN', 50),
       (2, 'test_user', '25d55ad283aa400af464c76d713c07ad', 'ADMIN', 50),
       (3, 'user0', 'e80b5017098950fc58aad83c8c14978e', 'ADMIN', 50),
       (4, 'user1', 'e80b5017098950fc58aad83c8c14978e', 'ADMIN', 50),
       (5, 'user2', 'e80b5017098950fc58aad83c8c14978e', 'ADMIN', 50),
       (6, 'user3', 'e80b5017098950fc58aad83c8c14978e', 'ADMIN', 50),
       (7, 'user4', 'e80b5017098950fc58aad83c8c14978e', 'ADMIN', 50),
       (8, 'user5', 'e80b5017098950fc58aad83c8c14978e', 'ADMIN', 50),
       (9, 'user6', 'e80b5017098950fc58aad83c8c14978e', 'ADMIN', 50);


INSERT INTO films
VALUES (1, 'Inception', 'static/img/movies/inception.jfif',
        'Inception is a 2010 science fiction action film written and directed by Christopher Nolan, '
            'who also produced the film with his wife, Emma Thomas. The film stars Leonardo DiCaprio as'
            ' a professional thief who steals information by infiltrating the subconscious of his targets', 4),
       (2, 'Interstellar', 'static/img/movies/interstellar.jfif',
        'Interstellar is a 2014 epic science fiction film directed and produced by Christopher Nolan.'
            ' It stars Matthew McConaughey, Anne Hathaway, Jessica Chastain, Bill Irwin, Ellen Burstyn, John Lithgow,'
            ' Michael Caine, and Matt Damon', 4),
       (3, 'Terminator', 'static/img/movies/terminator.jfif',
        'The Terminator is a 1984 science fiction film directed by James Cameron.It stars Arnold Schwarzenegger '
            'as the Terminator, a cyborg assassin sent back in time from 2029 to 1984 to kill Sarah Connor '
            '(Linda Hamilton), whose son will one day save mankind from extinction by a hostile artificial '
            'intelligence in a post-apocalyptic future', 4),
       (4, 'The beautiful mind', 'static/img/movies/the_beautiful_mind.jfif',
        'A Beautiful Mind is a 2001 American biographical drama film\n based on the life of the American '
            'mathematician John Nash, a Nobel Laureate in Economics and Abel Prize winner. The film was directed '
            'by Ron Howard, from a screenplay written by Akiva Goldsman.It was inspired by the bestselling,\n Pulitzer'
            ' Prize-nominated 1997 book of the same name by Sylvia Nasar.', 3),
       (5, 'Брат', 'static/img/movies/brother.jpg',
        '«Брат» — российский художественный фильм в жанре криминальной драмы. '
            'Четвёртый фильм режиссёра Алексея Балабанова.\n Первая часть дилогии (продолжение — «Брат 2») о '
            'герое 1990-х годов Даниле Багрове, роль которого сыграл Сергей Бодров-младший.\nФильм включён в список '
            '«100 главных русских фильмов по версии журнала „Афиша“».\n Бодров и Балабанов получили гран-при'
            ' фестиваля «Кинотавр» (1997) как лучший актёр и лучший режиссёр соответственно.', 3),
       (6, 'Shutter island', 'static/img/movies/shutter_island.jpg',
        'Shutter Island is a 2010 American neo-noir psychological thriller film directed by Martin Scorsese'
            ' and written by Laeta Kalogridis, based on Dennis Lehane \n\"s 2003 novel of the same name', 6);

