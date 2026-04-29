#LIBRARIES
import os
import msvcrt
import time
from screens import *
from functions import *

def load_engine(APIs: list):
    load_main_menu(APIs)

def load_main_menu(APIs: list):
    #MAIN_MENU
    while True:
        #SCREEN
        os.system('cls')
        print(screen_main_menu())
        option = input(f"Seleccione una opción (0-{len(APIs)}): ")
        os.system('cls')
        #SCREEN

        #OPTIONS
        if option == '0':
            print("...Saliendo del programa...")
            time.sleep(1)
            break

        for index, api_url in enumerate(APIs, start=1):
            if option == str(index):
                load_select_endpoints_menu(api_url)
                break
        else:
            print("\nOpción ingresada inválida.")
            #PAUSE
            print("\n...Presione un boton para continuar...")
            msvcrt.getch()

def load_select_endpoints_menu(API: str):
    while True:
        #SCREEN
        os.system('cls')
        print(screen_endpoints_menu(api_url_title_plural(API)))
        option = input("Seleccione una opción (0-2): ")
        os.system('cls')
        #OPTIONS
        #API MAIN ENDPOINTS
        if option == '1':
            pass
        #API CRUD ENDPOINTS
        elif option == '2':
            load_crud_menu(API)
        #RETURN TO MAIN MENU
        elif option == '0':
            break
        #VALIDATION
        else:
            print("\nOpción ingresada inválida.")
            #PAUSE
            print("\n...Presione un boton para continuar...")
            msvcrt.getch()

def load_crud_menu(API: str):
    #CRUD_MENU
    while True:
        #SCREEN
        os.system('cls')
        print(screen_crud_menu(api_url_title_plural(API)))
        option = input("Seleccione una opción (0-4): ")
        os.system('cls')
        #OPTIONS
        #HTTP GET
        if option == '1':
            load_crud_get_menu(API)
        #HTTP POST
        elif option == '2':
            pass
        #HTTP PUT
        elif option == '3':
            pass
        #HTTP DELETE
        elif option == '4':
            pass
        #END PROGRAM
        elif option == '0':
            break
        #VALIDATION
        else:
            print("\nOpción ingresada inválida.")
            #PAUSE
            print("\n...Presione un boton para continuar...")
            msvcrt.getch()

def load_crud_get_menu(API: str) -> str:
    while True:
        #SCREEN
        os.system('cls')
        print(screen_crud_get_menu(api_url_title_plural(API)))
        option = input("Seleccione una opción (0-2): ")
        os.system('cls')
        #GET FINDALL
        if option == '1':
            crud_get_findAll(API)
        #GET FINDONE
        elif option == '2':
            crud_get_findOne(API)
        elif option == '0':
            break
        #VALIDATION
        else:
            print("\nOpción ingresada inválida.")
            #PAUSE
            print("\n...Presione un boton para continuar...")
            msvcrt.getch()