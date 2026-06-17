CREATE TABLE dispositivo (
                             id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             nombre VARCHAR(50) NOT NULL,

                             CONSTRAINT uk_dispositivo_nombre UNIQUE (nombre),
                             CONSTRAINT ck_dispositivo_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 2 AND 50)
);

CREATE TABLE reproduccion (
                              id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              fecha_reproduccion DATE NOT NULL DEFAULT (CURDATE()),
                              segundos_escuchados INT NOT NULL,
                              id_dispositivo BIGINT NOT NULL,
                              cancion BIGINT NOT NULL,
                              usuario BIGINT NOT NULL,

                              CONSTRAINT ck_reproduccion_segundos CHECK (segundos_escuchados >= 1),
                              CONSTRAINT fk_reproduccion_dispositivo FOREIGN KEY (id_dispositivo) REFERENCES dispositivo(id)
);