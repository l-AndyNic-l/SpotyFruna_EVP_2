-- Insertar 20 canciones con diferentes géneros y álbumes
INSERT INTO cancion (autor, titulo, duracion, fecha_lanzamiento, id_genero, id_album) VALUES
-- Rock (id_genero = 1)
('Queen', 'Bohemian Rhapsody', 354, '1975-10-31', 1, 8),
('Pink Floyd', 'Comfortably Numb', 382, '1979-11-30', 1, 4),
('Led Zeppelin', 'Stairway to Heaven', 482, '1971-11-08', 1, 6),
('Nirvana', 'Smells Like Teen Spirit', 301, '1991-09-24', 1, 1),
('Guns N Roses', 'Sweet Child o Mine', 356, '1987-07-21', 1, 1),

-- Pop (id_genero = 2)
('Michael Jackson', 'Billie Jean', 294, '1982-11-30', 2, 1),
('Ed Sheeran', 'Shape of You', 233, '2017-01-06', 2, 7),
('Adele', 'Hello', 295, '2015-10-23', 2, 9),
('Taylor Swift', 'Shake It Off', 219, '2014-08-18', 2, 1),
('The Weeknd', 'Blinding Lights', 200, '2019-11-29', 2, 10),

-- Jazz (id_genero = 3)
('Miles Davis', 'So What', 564, '1959-08-17', 3, 1),
('John Coltrane', 'My Favorite Things', 807, '1961-03-01', 3, 1),
('Dave Brubeck', 'Take Five', 324, '1959-09-21', 3, 1),

-- Metal (id_genero = 5)
('Metallica', 'Enter Sandman', 331, '1991-08-12', 5, 1),
('Iron Maiden', 'The Number of the Beast', 289, '1982-03-22', 5, 1),
('Black Sabbath', 'Paranoid', 168, '1970-09-18', 5, 1),

-- Electrónica (id_genero = 6)
('Daft Punk', 'Around the World', 427, '1997-01-20', 6, 1),
('The Chemical Brothers', 'Hey Boy Hey Girl', 266, '1999-03-22', 6, 1),

-- Reggae (id_genero = 7)
('Bob Marley', 'No Woman No Cry', 427, '1974-12-20', 7, 1),

-- Blues (id_genero = 8)
('B.B. King', 'The Thrill Is Gone', 325, '1969-12-23', 8, 1);