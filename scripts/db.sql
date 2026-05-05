/*Activar los scripts*/
alter session set "_oracle_script" = true;

/*----------------------------------------------------------------------------------------------------------------------------*/

/*DB Api usuarios*/
create user USUARIOS_SERVICE identified by "12345";
grant all privileges to USUARIOS_SERVICE;

/*Tipos Usuarios*/
insert into TIPO_USUARIO ( tipo ) values( 'administrador' );
insert into TIPO_USUARIO ( tipo ) values( 'artista' );
insert into TIPO_USUARIO ( tipo ) values( 'cliente' );
commit;


/*Usuarios*/
insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Carlos', 'Escobedo', 'carl.escobedo@gmail.com', '123456', 673846109, TO_DATE('1987-04-12', 'YYYY-MM-DD'), 2);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('María', 'López', 'maria.lopez@gmail.com', 'password123', 912345678, TO_DATE('1990-08-23', 'YYYY-MM-DD'), 1);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Juan', 'Pérez', 'juan.perez@hotmail.com', 'juan123', 987654321, TO_DATE('1985-03-10', 'YYYY-MM-DD'), 3);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Ana', 'González', 'ana.gonzalez@gmail.com', 'ana2024', 934567890, TO_DATE('1995-11-05', 'YYYY-MM-DD'), 2);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Pedro', 'Rodríguez', 'pedro.rodriguez@yahoo.com', 'pedrito77', 956789012, TO_DATE('1988-07-19', 'YYYY-MM-DD'), 1);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Laura', 'Martínez', 'laura.martinez@gmail.com', 'laura2024', 978901234, TO_DATE('1992-02-28', 'YYYY-MM-DD'), 3);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Diego', 'Sánchez', 'diego.sanchez@outlook.com', 'diego123', 990123456, TO_DATE('1983-09-15', 'YYYY-MM-DD'), 2);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Valentina', 'Ramírez', 'vale.ramirez@gmail.com', 'vale1234', 901234567, TO_DATE('1998-05-30', 'YYYY-MM-DD'), 1);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Felipe', 'Torres', 'felipe.torres@hotmail.com', 'felipe89', 923456789, TO_DATE('1989-12-10', 'YYYY-MM-DD'), 3);

insert into USUARIO (nombre, apellido, email, password, celular, fecha_nacimiento, id_tipo) 
values('Camila', 'Flores', 'camila.flores@gmail.com', 'cami2024', 945678901, TO_DATE('1993-06-25', 'YYYY-MM-DD'), 2);

commit;

/*----------------------------------------------------------------------------------------------------------------------------*/

/*DB Api registro-sesiones*/
create user REGISTRO_SESSION_SERVICE identified by "12345";
grant all privileges to REGISTRO_SESSION_SERVICE;


/*Registros*/
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (5, to_date('2025-11-12 09:23:45', 'yyyy-mm-dd hh24:mi:ss'), 'A7F3K9');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (2, to_date('2026-02-18 14:56:12', 'yyyy-mm-dd hh24:mi:ss'), 'L2M8N1');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (8, to_date('2025-07-30 22:10:33', 'yyyy-mm-dd hh24:mi:ss'), 'R4P6Q0');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (1, to_date('2026-04-01 08:45:21', 'yyyy-mm-dd hh24:mi:ss'), 'T9S7D3');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (10, to_date('2025-09-14 17:32:09', 'yyyy-mm-dd hh24:mi:ss'), 'B5W2E8');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (4, to_date('2026-01-25 11:18:47', 'yyyy-mm-dd hh24:mi:ss'), 'H3U6C1');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (7, to_date('2025-12-03 20:05:59', 'yyyy-mm-dd hh24:mi:ss'), 'V9X4A0');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (3, to_date('2026-03-19 06:42:18', 'yyyy-mm-dd hh24:mi:ss'), 'M2N7K5');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (9, to_date('2025-08-27 13:29:36', 'yyyy-mm-dd hh24:mi:ss'), 'Q1F8J4');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (6, to_date('2026-05-02 19:55:03', 'yyyy-mm-dd hh24:mi:ss'), 'Z6G3L9');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (2, to_date('2025-10-09 16:14:27', 'yyyy-mm-dd hh24:mi:ss'), 'W8E4R2');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (8, to_date('2026-01-17 10:37:55', 'yyyy-mm-dd hh24:mi:ss'), 'D4C7V1');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (1, to_date('2025-11-28 23:48:14', 'yyyy-mm-dd hh24:mi:ss'), 'K9P2N5');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (5, to_date('2026-02-07 07:21:39', 'yyyy-mm-dd hh24:mi:ss'), 'F1H8J3');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (10, to_date('2025-09-21 12:59:42', 'yyyy-mm-dd hh24:mi:ss'), 'L5T0B7');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (4, to_date('2026-03-12 18:08:11', 'yyyy-mm-dd hh24:mi:ss'), 'C3Y6A9');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (7, to_date('2025-07-15 04:30:58', 'yyyy-mm-dd hh24:mi:ss'), 'P4V2N8');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (3, to_date('2026-04-26 20:17:33', 'yyyy-mm-dd hh24:mi:ss'), 'Z7X1K6');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (9, to_date('2025-12-19 09:43:07', 'yyyy-mm-dd hh24:mi:ss'), 'M2R5W0');
INSERT INTO REGISTRO_SESSION (usuario, fecha_hora, token) VALUES (6, to_date('2026-01-08 14:25:44', 'yyyy-mm-dd hh24:mi:ss'), 'J9H3C8');
commit;

/*----------------------------------------------------------------------------------------------------------------------------*/