CREATE TABLE plan (
                      id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                      nombre VARCHAR(30) NOT NULL,
                      precio BIGINT NOT NULL,
                      anuncios BOOLEAN NOT NULL,
                      tamanio_descargas DOUBLE NOT NULL,

                      CONSTRAINT uk_plan_nombre UNIQUE (nombre),
                      CONSTRAINT ck_plan_nombre CHECK (CHAR_LENGTH(nombre) BETWEEN 3 AND 30)
);

CREATE TABLE suscripcion (
                             id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             fecha_inicio DATE NOT NULL,
                             fecha_termino DATE NOT NULL,
                             activado BOOLEAN NOT NULL,
                             id_plan BIGINT NOT NULL,
                             id_usuario BIGINT NOT NULL,

                             CONSTRAINT fk_suscripcion_plan FOREIGN KEY (id_plan) REFERENCES PLAN(id)
);