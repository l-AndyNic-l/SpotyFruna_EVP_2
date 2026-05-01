/*Activar los scripts*/
alter session set "_oracle_script" = true;

/*DB Api usuarios*/
create user USUARIOS_SERVICE identified by "12345";
grant all privileges to USUARIOS_SERVICE;

/*DB Api seguridades*/
create user REGISTRO_SESSION_SERVICE identified by "12345";
grant all privileges to REGISTRO_SESSION_SERVICE;