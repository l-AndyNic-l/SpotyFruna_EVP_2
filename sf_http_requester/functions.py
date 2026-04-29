import requests
from screens import *

def api_url_title_plural(API: str) -> str:
    return API[29:].capitalize()

def api_url_title_singular(API: str) -> str:
    return API[29:1].capitalize()

def request_status(response) -> str:
    try:
        print(f"Código: {response.status_code}")
    except requests.exceptions.RequestException as e:
        print(f"Error: {e}")

def crud_get_findAll(API: str) -> str:
    print(screen_crud_get_findAll_menu(api_url_title_plural(API)))
    response = requests.get(API)
    request_status(response)
    json_response = response.json
    response_keys = json_response[0].keys()
    

def crud_get_findOne(API: str) -> str:
    print(screen_crud_get_findOne_menu(api_url_title_singular(API)))
    id = validate_id()
    response = requests.get(f"{API}/{id}")
    request_status(response)
    json_response = response.json
    print(json_response[0])
    print(json_response)

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