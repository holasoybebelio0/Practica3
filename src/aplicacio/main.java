package aplicacio;

import dades.activitats.ActivitatPeriodica;
import dades.activitats.ActivitatUnDia;
import dades.activitats.LlistaActivitats;
import dades.usuaris.*;
import java.time.LocalDate;
import java.util.Scanner;
import opcionsmenu.menuAran;

public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Inicialització de llistes
        LlistaActivitats llistaActivitats = new LlistaActivitats(100);
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(100);
        
        // Carreguem dades inicials dels fitxers per tenir dades de prova
        System.out.println("Carregant dades inicials...");
        llistaUsuaris.carregarUsuarisFitxer("prova.txt");
        llistaActivitats.carregarActivitatsFitxer("provaActivitats.txt");
        System.out.println("--------------------------------------------------");

        // Objectes auxiliars
        menuAran menuLogic = new menuAran(); // Per accedir a les opcions complexes implementades per l'Aran
        LocalDate avui = LocalDate.now(); // Data del sistema
        
        boolean sortir = false;
        
        while(!sortir) {
            mostrarMenu();
            System.out.println("Data actual del sistema: " + avui);
            int opcio = -1;
            try { 
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                opcio = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Introdueix un número vàlid.");
                continue;
            }
        
            switch (opcio) {
                case 1:
                    // Indicar les dades d'avui
                    avui = llistaActivitats.moddataActual(avui);
                    break;

                case 2:
                    // Mostrar les dades de les llistes
                    System.out.println("\n--- LLISTA D'ACTIVITATS ---");
                    llistaActivitats.mostrarLlista(0); // 0 = Mostrar totes
                    System.out.println("\n--- LLISTA D'USUARIS ---");
                    System.out.println(llistaUsuaris.toString());
                    break;

                case 3:
                    // Activitats en període d'inscripció
                    llistaActivitats.mostrarActivitatsDisponibles(avui);
                    break;

                case 4:
                    // Activitats amb classe avui
                    System.out.println("\n--- ACTIVITATS AMB CLASSE AVUI (" + avui + ") ---");
                    llistaActivitats.mostrarActivitatsClasseAvui(avui);
                    break;

                case 5:
                    // Activitats actives avui (dins de dates)
                    menuLogic.mostrarActivitatsAvui(llistaActivitats, avui);
                    break;

                case 6:
                    // Activitats amb places disponibles
                    llistaActivitats.mostrarActivitatsAmbPlaces();
                    break;

                case 7:
                    // Detall d'una activitat (per nom)
                    System.out.print("Introdueix el nom de l'activitat: ");
                    String nomActDetall = scanner.nextLine();
                    llistaActivitats.mostrarDetallActivitatNom(nomActDetall);
                    break;

                case 8:
                    // Detall d'un usuari (per nom)
                    System.out.print("Introdueix l'àlies de l'usuari: ");
                    String aliesUsuari = scanner.nextLine();
                    llistaUsuaris.mostrarDetallUsuariNom(aliesUsuari);
                    break;

                case 9:
                    // Activitats d'un usuari
                    System.out.print("Introdueix l'àlies de l'usuari: ");
                    String aliesActUser = scanner.nextLine();
                    llistaActivitats.mostrarActivitatsUsuari(aliesActUser);
                    break;

                case 10:
                    // Inscriure un usuari a una activitat
                    inscriureUsuari(llistaActivitats, llistaUsuaris, avui);
                    break;

                case 11:
                    // Llistar inscrits i llista d'espera d'una activitat
                    menuLogic.mostrarUsuarisApuntatsIEspera(llistaActivitats);
                    break;

                case 12:
                    // Eliminar un usuari d'una activitat
                    menuLogic.eliminarUsuariActivitat(llistaActivitats);
                    break;

                case 13:
                    // Afegir nova activitat d'un dia
                    afegirActivitatUnDia(llistaActivitats, avui);
                    break;

                case 14:
                    // Afegir nova activitat periòdica
                    afegirActivitatPeriodica(llistaActivitats, avui);
                    break;

                case 15:
                    // Afegir nova activitat en línia
                    menuLogic.afegirActivitatOnline(llistaActivitats);
                    break;

                case 16:
                    // Valorar una activitat finalitzada
                    System.out.print("Nom de l'activitat a valorar: ");
                    String nomVal = scanner.nextLine();
                    System.out.print("Àlies de l'usuari: ");
                    String userVal = scanner.nextLine();
                    System.out.print("Puntuació (0-10): ");
                    try {
                        int punts = Integer.parseInt(scanner.nextLine());
                        llistaActivitats.valorarActivitat(nomVal, userVal, punts, avui);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: La puntuació ha de ser un número enter.");
                    }
                    break;

                case 17:
                    // Resum de valoracions (activitats acabades)
                    System.out.println("\n--- RESUM ACTIVITATS ACABADES ---");
                    llistaActivitats.mostrarResumActivitatAcabada(avui);
                    break;

                case 18:
                    // Resum de valoracions per usuari
                    menuLogic.mostrarResumValoracionsUsuari(llistaActivitats);
                    break;

                case 19:
                    // Mitjana de valoracions per col·lectiu
                    System.out.print("Introdueix el nom de l'activitat: ");
                    String nomMitjana = scanner.nextLine();
                    llistaActivitats.mostrarMitjanaValoracions(nomMitjana);
                    break;

                case 20:
                    // Usuari més actiu d'un col·lectiu
                    menuLogic.calcularUsuariMesActiuColectiu(llistaActivitats, llistaUsuaris);
                    break;

                case 21:
                    // Donar de baixa activitats amb poca participació
                    menuLogic.donarBaixaActivitats(llistaActivitats, avui);
                    break;

                case 22:
                    System.out.println("\n--- SORTIR DE L'APLICACIÓ ---");
                    System.out.print("Vols guardar les dades abans de sortir? (S/N): ");
                    String resp = scanner.nextLine().trim().toUpperCase();

                    if (resp.equals("S")) {
                        System.out.println("Guardant dades...");
                        llistaActivitats.guardarActivitatsFitxer("provaActivitats.txt");
                        llistaUsuaris.guardarUsuarisFitxer("prova.txt");
                        System.out.println("Dades guardades correctament.");
                        sortir = true;
                    } else if (resp.equals("N")) {
                        System.out.println("Sortint sense guardar. Adéu!");
                        sortir = true;
                    } else {
                        System.out.println("Opció no vàlida.");
                    }
                    break;
                    
                default:
                    System.out.println("Opció invàlida. Si us plau, tria una opció vàlida.");
                    break;
            }
        }
    }

    // MÈTODES AUXILIARS PER AL MAIN

    public static void mostrarMenu() {
        System.out.println("\n==================================================");
        System.out.println("--- MENÚ PRINCIPAL ---");
        // Opcions generals i de consulta per data
        System.out.println("1. Indicar les dades d'avui (Canviar data sistema)");
        System.out.println("2. Mostrar les dades de les llistes (Usuaris i Activitats)");
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
        System.out.println("==================================================");

        System.out.print("Escull una opció: ");
    }

    // Mètode auxiliar per inscriure usuari (Case 10)
    private static void inscriureUsuari(LlistaActivitats llistaAct, LlistaUsuaris llistaUsu, LocalDate dataActual) {
        System.out.println("\n--- INSCRIURE USUARI ---");
        System.out.print("Introdueix l'àlies de l'usuari: ");
        String alies = scanner.nextLine();
        
        // Busquem l'objecte Usuari
        Usuari usuariTrobat = null;
        for (int i = 0; i < llistaUsu.getNumUsuaris(); i++) {
            if (llistaUsu.getUsuari(i).getAlies().equalsIgnoreCase(alies)) {
                usuariTrobat = llistaUsu.getUsuari(i);
                break;
            }
        }

        if (usuariTrobat == null) {
            System.out.println("Error: Usuari no trobat al sistema.");
            return;
        }

        System.out.print("Introdueix el nom de l'activitat: ");
        String nomAct = scanner.nextLine();

        llistaAct.inscriureUsuariActivitat(usuariTrobat, nomAct, dataActual);
    }

    // Mètode auxiliar per afegir activitat d'un dia (Case 13)
    private static void afegirActivitatUnDia(LlistaActivitats llista, LocalDate avui) {
        try {
            System.out.println("\n--- AFEGIR ACTIVITAT D'UN DIA ---");
            System.out.print("Nom de l'activitat: ");
            String nom = scanner.nextLine();
            
            System.out.print("Colectius (separats per comes, ex: PDI,Estudiants): ");
            String[] colectius = scanner.nextLine().split(",");

            System.out.print("Data inici inscripció (AAAA-MM-DD): ");
            LocalDate dataInici = LocalDate.parse(scanner.nextLine());
            
            System.out.print("Data final inscripció (AAAA-MM-DD): ");
            LocalDate dataFi = LocalDate.parse(scanner.nextLine());

            System.out.print("Data de l'activitat (AAAA-MM-DD): ");
            LocalDate dataAct = LocalDate.parse(scanner.nextLine());
            
            System.out.print("Ciutat: ");
            String ciutat = scanner.nextLine();
            
            System.out.print("Preu: ");
            double preu = Double.parseDouble(scanner.nextLine());
            
            System.out.print("Places totals: ");
            int places = Integer.parseInt(scanner.nextLine());

            System.out.print("Horari (ex: 10:00): ");
            String horari = scanner.nextLine();
            
            ActivitatUnDia novaAct = new ActivitatUnDia(
                nom, colectius, dataInici, dataFi, 
                dataAct, ciutat, preu, places, horari
            );
            
            llista.afegirActivitatUnDia(novaAct);
        } catch (Exception e) {
            System.out.println("Error introduint dades: " + e.getMessage());
        }
    }

    // Mètode auxiliar per afegir activitat periòdica (Case 14)
    private static void afegirActivitatPeriodica(LlistaActivitats llista, LocalDate avui) {
        try {
            System.out.println("\n--- AFEGIR ACTIVITAT PERIÒDICA ---");
            System.out.print("Nom de l'activitat: ");
            String nom = scanner.nextLine();
            
            System.out.print("Colectius (separats per comes): ");
            String[] colectius = scanner.nextLine().split(",");

            System.out.print("Data inici inscripció (AAAA-MM-DD): ");
            LocalDate dataIniciInsc = LocalDate.parse(scanner.nextLine());
            
            System.out.print("Data final inscripció (AAAA-MM-DD): ");
            LocalDate dataFiInsc = LocalDate.parse(scanner.nextLine());

            System.out.print("Dia de la setmana (ex: Dilluns): ");
            String diaSetmana = scanner.nextLine();

            System.out.print("Horari (ex: 18:00): ");
            String horari = scanner.nextLine();

            System.out.print("Data inici activitat (AAAA-MM-DD): ");
            LocalDate dataIniciAct = LocalDate.parse(scanner.nextLine());

            System.out.print("Nombre de setmanes (durada): ");
            int nSetmanes = Integer.parseInt(scanner.nextLine());

            System.out.print("Places totals: ");
            int places = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Preu total: ");
            double preu = Double.parseDouble(scanner.nextLine());

            System.out.print("Nom del centre: ");
            String centre = scanner.nextLine();

            System.out.print("Ciutat: ");
            String ciutat = scanner.nextLine();
            
            ActivitatPeriodica novaAct = new ActivitatPeriodica(
                nom, colectius, dataIniciInsc, dataFiInsc,
                diaSetmana, horari, dataIniciAct, nSetmanes,
                places, preu, centre, ciutat
            );
            
            llista.afegirActivitatPeriodica(novaAct);
            System.out.println("Activitat periòdica afegida correctament.");

        } catch (Exception e) {
            System.out.println("Error introduint dades: " + e.getMessage());
        }
    }
}