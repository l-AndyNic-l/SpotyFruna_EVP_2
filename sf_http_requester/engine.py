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
        option = input("Seleccione una opción (0-3): ")
        os.system('cls')
        #OPTIONS
        #API_1
        if option == '1':
            load_crud_menu(APIs[0])
        #API_2
        elif option == '2':
            load_crud_menu(APIs[1])
        #API_3
        elif option == '3':
            load_crud_menu(APIs[2])
        #END PROGRAM
        elif option == '0':
            print("...Saliendo del programa...")
            time.sleep(1)
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
        print(screen_crud_menu())
        option = input("Seleccione una opción (0-4): ")
        os.system('cls')
        #OPTIONS
        #HTTP GET
        if option == '1':
            #HTTP_GET_MENU
            while True:
                #SCREEN
                os.system('cls')
                print(screen_get_menu())
                option = input("Seleccione una opción (0-2): ")
                os.system('cls')
                #OPTIONS
                #HTTP GET ALL OBJECTS
                if option == '1':
                    get_all_objetos(API)
                #HTTP GET ONE OBJECT
                elif option == '0':
                    get_one_objeto(API)
                #VALIDATION
                else:
                    print("\nOpción ingresada inválida.")
                    #PAUSE
                    print("\n...Presione un boton para continuar...")
                    msvcrt.getch()
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
            print("...Saliendo del programa...")
            time.sleep(1)
            break
        #VALIDATION
        else:
            print("\nOpción ingresada inválida.")
        #PAUSE
        print("\n...Presione un boton para continuar...")
        msvcrt.getch()

