import requests
from screens import *

#HTTP CRUD FUNCTIONS
def get_all_objetos(API: str) -> str:
    response = requests.get(API)
    json_response = response.json()
    for mascota in json_response:
        print(screen_get_menu_objeto_format(mascota))

def get_one_objeto(API: str, id: int) -> str:
    id = int(input("Ingrese ID a buscar: "))
    response = requests.get(API + f"/{id}")
    json_response = response.json()
    mascota = json_response
    print(screen_get_menu_objeto_format(mascota))

