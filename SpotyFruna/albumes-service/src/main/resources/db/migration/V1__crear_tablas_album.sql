CREATE TABLE tipo_album (
                            id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            nombre VARCHAR(30) NOT NULL,

                            CONSTRAINT ck_tipo_album_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 1 AND 30)
);

CREATE TABLE album (
                       id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                       artista BIGINT NOT NULL,
                       nombre VARCHAR(50) NOT NULL,
                       descripcion VARCHAR(300),
                       fecha_lanzamiento DATE DEFAULT CURRENT_DATE NOT NULL,
                       id_tipo_album BIGINT NOT NULL,

                       CONSTRAINT ck_album_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 1 AND 50),
                       CONSTRAINT ck_album_descripcion CHECK (
                           descripcion IS NULL
                               OR CHAR_LENGTH(descripcion) BETWEEN 1 AND 300
                           ),
                       CONSTRAINT fk_album_tipo_album
                           FOREIGN KEY (id_tipo_album)
                               REFERENCES TIPO_ALBUM(id)
);