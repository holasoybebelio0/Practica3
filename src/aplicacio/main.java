package aplicacio;

import dades.*;
import dades.usuaris.*;
import opcionsmenu.*;

import java.util.Scanner;

public class main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        mostrarMenu();
        int opcio = scanner.nextInt();
        switch (opcio) {
            case 1:
                // Cridar mètode per indicar dades d'avui
                break;
            case 2:
                // Cridar mètode per mostrar dades de les llistes
                break;
            case 3:
                // Cridar mètode per activitats en període d'inscripció
                break;
            case 4:
                // Cridar mètode per activitats amb classe avui
                break;
            case 5:
                // Cridar mètode per activitats actives avui
                break;
            case 6:
                // Cridar mètode per activitats amb places disponibles
                break;
            case 7:
                // Cridar mètode per detall d'una activitat
                break;
            case 8:
                // Cridar mètode per detall d'un usuari
                break;
            case 9:
                // Cridar mètode per activitats d'un usuari
                break;
            case 10:
                // Cridar mètode per inscriure un usuari a una activitat
                break;

            case 11:
                // Cridar mètode per llistar inscrits i llista d'espera d'una activitat
                break;
            case 12:
                // Cridar mètode per eliminar un usuari d'una activitat
                break;
            case 13:
                // Cridar mètode per afegir nova activitat d'un dia
                break;
            case 14:
                // Cridar mètode per afegir nova activitat periòdica
                break;
            case 15:
                // Cridar mètode per afegir nova activitat en línia
                break;
            case 16:
                // Cridar mètode per valorar una activitat finalitzada
                break;
            case 17:
                // Cridar mètode per resum de valoracions (activitats acabades)
                break;
            case 18:
                // Cridar mètode per resum de valoracions per usuari
                break;
            case 19:
                // Cridar mètode per mitjana de valoracions per col·lectiu
                break;
            case 20:
                // Cridar mètode per usuari més actiu d'un col·lectiu
                break;
            case 21:

                // Cridar mètode per donar de baixa activitats amb poca participació
                break;
            case 22:
                System.out.println("Sortint de l'aplicació. Fins aviat!");

                break;
            default:
                System.out.println("Opció invàlida. Si us plau, tria una opció vàlida.");
                break;
        }

    }

        public static void mostrarMenu() {
        System.out.println("--- MENÚ PRINCIPAL ---");
        // Opcions generals i de consulta per data
        System.out.println("1. Indicar les dades d'avui");
        System.out.println("2. Mostrar les dades de les llistes");
        System.out.println("3. Activitats en període d'inscripció");
        System.out.println("4. Activitats amb classe avui (detall i places)");
        System.out.println("5. Activitats actives avui (dins de dates)");
        System.out.println("6. Activitats amb places disponibles");

        // Consultes específiques (Activitat/Usuari)
        System.out.println("7. Detall d'una activitat (per nom)");
        System.out.println("8. Detall d'un usuari (per nom)");
        System.out.println("9. Activitats d'un usuari");

        // Gestió d'inscripcions
        System.out.println("10. Inscriure un usuari a una activitat");
        System.out.println("11. Llistar inscrits i llista d'espera d'una activitat");
        System.out.println("12. Eliminar un usuari d'una activitat");

        // Creació d'activitats
        System.out.println("13. Afegir nova activitat d'un dia");
        System.out.println("14. Afegir nova activitat periòdica");
        System.out.println("15. Afegir nova activitat en línia");

        // Valoracions i estadístiques
        System.out.println("16. Valorar una activitat finalitzada");
        System.out.println("17. Resum de valoracions (activitats acabades)");
        System.out.println("18. Resum de valoracions per usuari");
        System.out.println("19. Mitjana de valoracions per col·lectiu");
        System.out.println("20. Usuari més actiu d'un col·lectiu");

        // Manteniment i Sortida
        System.out.println("21. Donar de baixa activitats amb poca participació");
        System.out.println("22. Sortir de l'aplicació");

        System.out.print("Escull una opció: ");
    }
}
