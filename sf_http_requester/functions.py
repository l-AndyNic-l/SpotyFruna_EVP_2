import requests
from temp_classes import *
from screens import *

def request_status(response) -> str:
    try:
        print(f"Código: {response.status_code}")
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")


## CRUD FUNCTIONS
def crud_get_findAll(API: str) -> str:
    print(screen_crud_get_findAll_menu(api_plural(API)))
    response = requests.get(API)
    status = request_status(response)
    format_json_response(response.json)
    if status == 200:
        print(f"{api_plural(API)} encontrados con éxito!")
    elif status == 204:
        print(f"{api_plural(API)} no han sido encontrados.")
    else:
        print("Error.")


def crud_get_findById(API: str) -> str:
    print(screen_crud_get_findById_menu(api_singular(API)))
    id = validate_id()
    response = requests.get(f"{API}/{id}")
    status = request_status(response)
    format_json_response(response.json)
    if status == 200:
        print(f"{api_singular(API)} encontrado con éxito!")
    elif status == 204:
        print(f"{api_singular(API)} no ha sido encontrado.")
    else:
        print("Error.")


def crud_post_save(API: str) -> str:
    print(screen_crud_post_save_menu(api_singular(API)))
    response = requests.post(API)
    status = request_status(response)
    if status == 201:
        print(f"{api_singular(API)} creado con éxito!")
    else:
        print("Error.")


def crud_put_update(API: str) -> str:
    print(screen_crud_put_update_menu(api_singular(API)))
    id = validate_id()
    response = requests.put(f"{API}/{id}")
    status = request_status(response)
    if status == 200:
        print(f"{api_singular(API)} actualizado con éxito!")
    else:
        print("Error.")


def crud_delete(API: str) ->str:
    print(screen_crud_delete_menu(api_singular(API)))
    id = validate_id()
    response = requests.delete(f"{API}/{id}")
    status = request_status(response)
    if status == 204:
        print(f"{api_singular(API)} eliminado con éxito!")
    else:
        print("Error.")


## FORMATTING
def format_json_response(json_response) -> str:
    obj_keys = list(json_response[0].keys())
    text = ""
    for object in json_response:
        obj_values = list(object.values())
        text += "\n"
        for index in range(len(obj_values)):
            text += f"""{obj_keys[index]}: {obj_values[index]}
    """
    print(text)


def api_plural(API: str) -> str:
    return API[29:].capitalize()


def api_singular(API: str) -> str:
    return API[29:1].capitalize()


## VALIDATIONS
def validate_id():
    while True:
        try:
            id = int(input("Ingrese ID a buscar: "))
            if id > 0:
                return id
            else:
                print("Porfavor, ingrese solamente números enteros positivos desde el 1.")
        except:
            print("Error. Dato ingresado inválido.")