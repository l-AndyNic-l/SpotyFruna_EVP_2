def screen_main_menu() -> str:
    return """
=== SpotyFruna HTTP Requester ===

1.  Administradores API.
2.  Artistas API.
3.  Usuarios API.
4.  Clientes API.
5.  Canciones API.
6.  Contenido API.
7.  Busquedas API.
8.  Listas API.
9.  Albumes API.
10. Seguridades API.
11. Soportes API.
0.  Terminar programa.
"""

def screen_endpoints_menu(API: str) -> str:
    return f"""
=== {API} Endpoints Menu ===

1. Funciones principales de la API.
2. CRUD Estandár.
0. Volver al menú principal.
"""

def screen_crud_menu(API: str) -> str:
    return f"""
=== {API} CRUD Requests ===

1. Get.
2. Put.
3. Post.
4. Delete.
0. Volver al menu principal.
"""

def screen_crud_get_menu(API: str) -> str:
    return f"""
=== {API} GET Requests ===

1. Listar todos.
2. Buscar uno.
"""

def screen_crud_get_findAll_menu(API: str) -> str:
    return f"""
=== Listando {API} ===
"""

def screen_crud_get_findOne_menu(API: str) -> str:
    return f"""
=== Buscando {API[:1]} ===
"""