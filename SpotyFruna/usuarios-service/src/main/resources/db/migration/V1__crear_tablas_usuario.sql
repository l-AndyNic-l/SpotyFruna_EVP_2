CREATE TABLE tipo_usuario (
                              id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(50) NOT NULL,
                              CONSTRAINT uk_tipo_usuario_nombre UNIQUE (nombre),
                              CONSTRAINT ck_tipo_usuario_nom_len CHECK (CHAR_LENGTH(nombre) BETWEEN 5 AND 50)
);

CREATE TABLE usuario (
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(50) NOT NULL,
                         apellido VARCHAR(50) NOT NULL,
                         nickname VARCHAR(50) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         password VARCHAR(12) NOT NULL,
                         fecha_nacimiento DATE NOT NULL,
                         celular BIGINT NOT NULL,
                         id_tipo_usr BIGINT NOT NULL,

                         CONSTRAINT uk_usuario_nickname UNIQUE (nickname),
                         CONSTRAINT uk_usuario_email UNIQUE (email),

                         CONSTRAINT ck_usuario_nom_len CHECK (CHAR_LENGTH(nombre) BETWEEN 1 AND 50),
                         CONSTRAINT ck_usuario_ape_len CHECK (CHAR_LENGTH(apellido) BETWEEN 1 AND 50),
                         CONSTRAINT ck_usuario_nick_len CHECK (CHAR_LENGTH(nickname) BETWEEN 3 AND 50),
                         CONSTRAINT ck_usuario_pass_len CHECK (CHAR_LENGTH(password) BETWEEN 6 AND 12),

                         CONSTRAINT fk_usuario_tipo_usuario
                             FOREIGN KEY (id_tipo_usr)
                                 REFERENCES tipo_usuario(id)
);