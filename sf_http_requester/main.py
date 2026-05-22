#LIBRARIES
from engine import load_engine
#APIs SETUP
APIs = ["http://localhost:8080/api/v1/administradores",
        "http://localhost:8080/api/v1/artistas",
        "http://localhost:8080/api/v1/clientes",
        "http://localhost:8080/api/v1/usuarios",
        "http://localhost:8080/api/v1/canciones",
        "http://localhost:8080/api/v1/contenido",
        "http://localhost:8080/api/v1/busquedas",
        "http://localhost:8080/api/v1/listas",
        "http://localhost:8080/api/v1/albumes",
        "http://localhost:8080/api/v1/seguridades",
        "http://localhost:8080/api/v1/soportes"]
#INIT_PROGRAM
load_engine(APIs)