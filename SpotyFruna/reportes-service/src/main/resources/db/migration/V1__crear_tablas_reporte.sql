CREATE TABLE estado (
                        id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(50) NOT NULL,

                        CONSTRAINT uk_estado_nombre UNIQUE (nombre),
                        CONSTRAINT ck_estado_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 5 AND 50)
);

CREATE TABLE tipo_reporte (
                              id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(50) NOT NULL,

                              CONSTRAINT uk_tipo_reporte_nombre UNIQUE (nombre),
                              CONSTRAINT ck_tipo_reporte_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 5 AND 50)
);

CREATE TABLE reporte (
                         id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         administrador BIGINT NOT NULL,
                         usuario BIGINT NOT NULL,
                         descripcion VARCHAR(255) NOT NULL,
                         fecha_enviado DATE DEFAULT CURRENT_DATE NOT NULL,
                         fecha_resuelto DATE NULL,
                         id_tipo_reporte BIGINT NOT NULL,
                         id_estado BIGINT NOT NULL,

                         CONSTRAINT ck_reporte_descripcion CHECK (CHAR_LENGTH(descripcion) >= 10),
                         CONSTRAINT fk_reporte_tipo_reporte FOREIGN KEY (id_tipo_reporte) REFERENCES TIPO_REPORTE(id),
                         CONSTRAINT fk_reporte_estado FOREIGN KEY (id_estado) REFERENCES ESTADO(id)
);