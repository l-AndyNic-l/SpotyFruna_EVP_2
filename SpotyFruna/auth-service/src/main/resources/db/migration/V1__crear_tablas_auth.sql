CREATE TABLE estado (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(50) NOT NULL,

                        CONSTRAINT uk_estado_nombre UNIQUE (nombre),
                        CONSTRAINT ck_estado_nom_len CHECK (CHAR_LENGTH(nombre) BETWEEN 5 AND 50)
);

CREATE TABLE auth (
                      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      usuario BIGINT NOT NULL,
                      id_estado BIGINT NOT NULL,
                      token VARCHAR(255) NOT NULL,
                      fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                      fecha_expiracion TIMESTAMP NULL,

                      CONSTRAINT uk_auth_token UNIQUE (token),

                      CONSTRAINT fk_auth_estado
                          FOREIGN KEY (id_estado)
                              REFERENCES ESTADO(id)
);