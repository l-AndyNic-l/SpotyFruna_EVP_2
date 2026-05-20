CREATE TABLE genero (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(30) NOT NULL,

                        CONSTRAINT uk_genero_nombre UNIQUE (nombre),
                        CONSTRAINT ck_genero_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 1 AND 30)
);

CREATE TABLE cancion (
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         autor VARCHAR(50) NOT NULL,
                         titulo VARCHAR(50) NOT NULL,
                         duracion BIGINT NOT NULL,
                         fecha_lanzamiento DATE NOT NULL,
                         id_genero BIGINT NOT NULL,
                         id_album BIGINT NOT NULL,

                         CONSTRAINT uk_cancion_autor UNIQUE (autor),
                         CONSTRAINT uk_cancion_titulo UNIQUE (titulo),
                         CONSTRAINT ck_cancion_autor CHECK (CHAR_LENGTH(autor) BETWEEN 1 AND 50),
                         CONSTRAINT ck_cancion_titulo CHECK (CHAR_LENGTH(titulo) BETWEEN 1 AND 50),
                         CONSTRAINT fk_cancion_genero FOREIGN KEY (id_genero) REFERENCES GENERO(id)
);