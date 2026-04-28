def screen_main_menu() -> str:
    return """
=== HTTP Requests Skeleton ===
1. API_1.
2. API_2.
3. API_3.
0. Terminar programa.
"""

def screen_crud_menu() -> str:
    return """
=== HTTP CRUD Requests ===
1. Get.
2. Put.
3. Post.
4. Delete.
0. Volver al menu principal.
"""

def screen_get_menu() -> str: 
    return """
=== HTTP Get Requests ===
1. Listar todo.
2. Buscar uno.
0. Volver al menu anterior.
"""

def screen_get_menu_objeto_format(mascota) -> str:
    return f"""
ID: {mascota["id"]}
Nombre: {mascota["nombre"]}
Raza: {mascota["raza"]}
Genero: {mascota["genero"]}
Fecha Nacimiento: {mascota["fechaDeNacimiento"]}
"""

