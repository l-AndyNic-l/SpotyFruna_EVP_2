/*Activar los scripts*/
alter session set "_oracle_script" = true;

/*DB Api usuarios*/
create user USUARIOS_SERVICE identified by "12345";
grant all privileges to USUARIOS_SERVICE;