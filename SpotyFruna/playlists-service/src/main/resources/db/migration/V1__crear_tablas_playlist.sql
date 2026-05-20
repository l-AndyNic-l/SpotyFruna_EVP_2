CREATE TABLE privacidad (
                            id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(20) NOT NULL,

                            CONSTRAINT uk_privacidad_nombre UNIQUE (nombre),
                            CONSTRAINT ck_privacidad_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 1 AND 20)
);

CREATE TABLE playlist (
                          id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                          nombre VARCHAR(50) NOT NULL,
                          descripcion VARCHAR(300),
                          fecha_creacion DATE DEFAULT CURRENT_DATE NOT NULL,
                          id_privacidad BIGINT NOT NULL,
                          id_usuario BIGINT NOT NULL,

                          CONSTRAINT ck_playlist_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 1 AND 50),
                          CONSTRAINT ck_playlist_descripcion CHECK (CHAR_LENGTH(descripcion) BETWEEN 1 AND 300),
                          CONSTRAINT fk_playlist_privacidad FOREIGN KEY (id_privacidad) REFERENCES PRIVACIDAD(id)
);

CREATE TABLE guardar_cancion (
                                 id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 id_playlist BIGINT NOT NULL,
                                 id_cancion BIGINT NOT NULL,

                                 CONSTRAINT fk_guardar_cancion_playlist FOREIGN KEY (id_playlist) REFERENCES PLAYLIST(id)
);