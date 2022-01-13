/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import cadtattooally.*;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class CPresentacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int opcion = -1;
        int opcion2 = -1;

        Scanner in = new Scanner(System.in);

        // bucle do while que recoja el menú principal bajo la condición de que la opcion introducida por el usuario se encuentre entre las mostradas
        do {
            mostrarMenuPrincipal();
            opcion = in.nextInt();
            if (opcion < 0 || opcion > 2) {
                System.out.println("Opción incorrecta, pulse un número de los mostrados en el menú");
                continue;
            }
            switch (opcion) {
                case 1: // se pulsa la opcion de gestion de usuario
                    do {
                        mostrarMenuUsuario();
                        in = new Scanner(System.in, "ISO-8859-1");
                        opcion2 = in.nextInt();
                        if (opcion2 < 0 || opcion2 > 5) {
                            System.out.println("Opción incorrecta");
                            continue;
                        }
                        gestionUsuario(opcion2);
                    } while (opcion2 != 0);
                    break;
                case 2: // se pulsa la opcion de gestion de publicacion
                    do {
                        mostrarMenuPublicacion();
                        in = new Scanner(System.in, "ISO-8859-1");
                        opcion2 = in.nextInt();
                        if (opcion2 < 0 || opcion2 > 5) {
                            System.out.println("Opción incorrecta");
                            continue;
                        }
                        gestionPublicacion(opcion2);
                    } while (opcion2 != 0);
                    break;
                case 0: // se pulsa la opcion de salir
                    System.out.println("Ha pulsado salir");
                    break;
                default: // opcion incorrecta
                    System.out.println("Opción incorrecta, inténtelo de nuevo");
                    mostrarMenuPrincipal();
            }
        } while (opcion < 0 || opcion > 2);
        System.out.println("FIN");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("- - - - MENÚ PRINCIPAL - - - -");
        System.out.println("Pulse 1 para Menú de Usuario");
        System.out.println("Pulse 2 para Menú de Publicación");
        System.out.println("Pulse 0 para Salir");
    }

    private static void mostrarMenuUsuario() {
        System.out.println("- - - - MENÚ USUARIO - - - -");
        System.out.println("Pulse 1 para crear un Nuevo Usuario");
        System.out.println("Pulse 2 para Eliminar un Usuario");
        System.out.println("Pulse 3 para Actualizar un Usuario");
        System.out.println("Pulse 4 para Ver un Usuario");
        System.out.println("Pulse 5 para Ver Todos los Usuarios");
        System.out.println("Pulse 0 para Volver");
    }

    private static void mostrarMenuPublicacion() {
        System.out.println("- - - - MENÚ PUBLICACIÓN - - - -");
        System.out.println("Pulse 1 para crear una Nueva Publicación");
        System.out.println("Pulse 2 para Eliminar una Publicación");
        System.out.println("Pulse 3 para Actualizar una Publicación");
        System.out.println("Pulse 4 para Ver una Publicación");
        System.out.println("Pulse 5 para Ver Todas las Publicaciones");
        System.out.println("Pulse 0 para Volver");
    }

    private static void gestionUsuario(int opcion) {
        Scanner in = new Scanner(System.in);

        switch (opcion) {
            case 1:
                System.out.println("Insertar Usuario");
                break;
            case 2:
                System.out.println("Eliminar Usuario");
                break;
            case 3:
                System.out.println("Actualizar Usuario");
                break;
            case 4:
                System.out.println("Ver un Usuario");
                break;
            case 5:
                System.out.println("Ver Todos Usuarios");
                break;
            case 0:
                System.out.println("Volver");
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("Opcion incorrecta");
        }
    }

    private static void gestionPublicacion(int opcion) {
        Scanner in = new Scanner(System.in);

        switch (opcion) {
            case 1:
                System.out.println("Insertar Publicación");
                break;
            case 2:
                System.out.println("Eliminar Publicación");
                break;
            case 3:
                System.out.println("Actualizar Publicación");
                break;
            case 4:
                System.out.println("Ver una Publicación");
                break;
            case 5:
                System.out.println("Ver Todas Publicaciones");
                break;
            case 0:
                System.out.println("Volver");
                mostrarMenuPrincipal();
                break;
            default:
                System.out.println("Opcion incorrecta");
        }
    }
}
