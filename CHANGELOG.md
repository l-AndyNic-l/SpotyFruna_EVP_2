# Changelog

Todos los cambios notables de este proyecto estarán documentados en este archivo.

---
## [v0.1.6] 29-04-2026: Nuevas pantallas y funciones principales.
Desarrollo del script/programa para las consultas HTTP, logrando un menú escalable con validación de ingreso de datos, sentando bases para realizar conexiones a la base de datos Oracle mediante las solicitudes a los Endpoints.

### ADDED
- Se añadió una pantalla para el menú Endpoints.
- Se añadió una pantalla para el menú CRUD Estándar.
- Se añadió una pantalla para el menú CRUD_GET.
- Se añadió una pantalla para el menú CRUD_GET_FindAll.
- Se añadió una pantalla para el menú CRUD_GET_FindOne.
- Se añadio una función para obtener el título de la API en plural.
- Se añadio una función para obtener el título de la API en singular.

### CHANGED
- Se refactorizó la lógica de las pausas de los menús.
- Se refactorizó la lógica del menú CRUD_GET.

## [v0.1.5] 29-04-2026: Actualización de la API "usuarios-service"
Desarrollo de la API "usuarios-service", bases solidas de Controller-Service-Repository-Model con su respectivo CRUD.

### ADDED
- Se añadió y definió la clase "Usuario.java" en el modelo de la API "usuarios-service".
- Se añadió y estableció el repositorio "UsuarioRepository.java".
- Se añadió y estableció el service "UsuarioService.java" con reglas de negocio estándar.
- Se agregó el .idea al archivo ".gitignore".

### CHANGED
- Se cambió la dependencia de la base de datos del proyecto a Oracle.
- Se refactorizó el CRUD de la API "usuarios-service" en el archivo "UsuarioController.java"
- 

## [v0.1.4] 28-04-2026: Python HTTP Requester pulido y listo para testear APIs
Desarrollo del script/programa para las consultas HTTP, logrando establecer unas base solida para testear la conexión a los Endpoints de SpotyFruna. 

### ADDED
- Se añadieron las URLs a la lista de APIs.
- Se añadió una función para obtener el Código de respuesta HTTP.

### CHANGED
- Se ajustó el CHANGELOG a una convención estándar.
- Se extendió las opciones del menú principal (screen_main_menu).
- Se refactorizó la lógica del menú principal para optimizar el script/programa.

### REMOVED
- Se eliminaron las funciones estandares para Endpoints Get del CRUD (get_all_objetos, get_one_objeto).
- Se eliminó la pantalla para presentar información de un objeto (screen_get_menu_objeto_format).
- Se eliminó el menú para los Endpoints Get del CRUD (load_crud_menu)

## [v0.1.3] 28-04-2026: HTTP Requester via Python
Implementación de script/programa mediante Python para realizar consultas HTTP a los Endpoints de SpotyFruna mediante un menu, con expectativas de crear un .exe a partir de este.
### ADDED
- Se agregó un script de Python para realizar solicitudes HTTP, aun en desarrollo.

## [v0.1.2] 28-04-2026: Changelog
Iniciación de documentación del proyecto.
### ADDED
- Se añadió al repositorio del proyecto el archivo CHANGELOG.md.

## [v0.1.1] 26-04-2026: Esqueleto de cada microservicio listo para conectar
Controllers para establecer Endpoints para cada microservicio, para luego probar conexiones HTTP.
### ADDED
- Se añadio a cada API su respectivo Controller.

## [v0.1.0] 26-04-2026: Estableciendo las carpetas de cada microservicio
Definición de bases del proyecto
### ADDED
- Se añadió la carpeta "administradores-service" para la API administradores.
- Se añadió la carpeta 	      "artistas-service" para la API artistas.
- Se añadió la carpeta 	      "clientes-service" para la API clientes.
- Se añadió la carpeta        "usuarios-service" para la API usuarios.
- Se añadió la carpeta 	     "canciones-service" para la API canciones.
- Se añadió la carpeta       "contenido-service" para la API contenido.
- Se añadió la carpeta 	        "buscar-service" para la API buscar.
- Se añadió la carpeta          "listas-service" para la API listas.
- Se añadió la carpeta	       "albumes-service" para la API albumes.
- Se añadió la carpeta       "seguridad-service" para la API seguridad.
- Se añadió la carpeta         "soporte-service" para la API soporte.
