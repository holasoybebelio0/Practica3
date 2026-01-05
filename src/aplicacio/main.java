package aplicacio;

import dades.*;
import dades.usuaris.*;
import dades.activitats.*;
import dades.inscripcions.*;
import opcionsmenu.menuAran; // Asumo que usas las clases de tus compañeros
import java.time.LocalDate;
import java.util.Scanner;

public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. INICIALITZACIÓ DE LLISTES
        // Ajusta la mida (100) segons necessitis
        LlistaActivitats llistaActivitats = new LlistaActivitats(100);
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(100);
        LlistaInscripcions llistaInscripcions = new LlistaInscripcions(100); // Llista per al fitxer serialitzat
        
        // 2. CÀRREGA DE DADES INICIALS (PERSISTÈNCIA)
        System.out.println("Carregant dades del sistema...");
        try {
            // Carreguem usuaris i activitats dels fitxers de text
            llistaUsuaris.carregarUsuarisFitxer("prova.txt");
            llistaActivitats.carregarActivitatsFitxer("provaActivitats.txt");
            
            // Carreguem les inscripcions del fitxer serialitzat (.dat)
            // Nota: Si el fitxer no existeix (primera vegada), el mètode hauria de gestionar l'excepció internament o crear-ne un de buit.
            llistaInscripcions.carregarInscripcions("totes_inscripcions.dat");
            
            System.out.println("Dades carregades correctament.");
        } catch (Exception e) {
            System.out.println("Avís: No s'han pogut carregar totes les dades o és el primer cop que s'executa.");
            // e.printStackTrace(); // Descomentar per veure errors concrets
        }
        System.out.println("--------------------------------------------------");

        // Objectes auxiliars
        menuAran menuLogic = new menuAran(); 
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
                    avui = llistaActivitats.moddataActual(avui);
                    break;

                case 2:
                    System.out.println("\n--- LLISTA D'ACTIVITATS ---");
                    llistaActivitats.mostrarLlista(0); 
                    System.out.println("\n--- LLISTA D'USUARIS ---");
                    System.out.println(llistaUsuaris.toString());
                    break;

                case 3:
                    llistaActivitats.mostrarActivitatsDisponibles(avui);
                    break;

                case 4:
                    System.out.println("\n--- ACTIVITATS AMB CLASSE AVUI (" + avui + ") ---");
                    llistaActivitats.mostrarActivitatsClasseAvui(avui);
                    break;

                case 5:
                    menuLogic.mostrarActivitatsAvui(llistaActivitats, avui);
                    break;

                case 6:
                    llistaActivitats.mostrarActivitatsAmbPlaces();
                    break;

                case 7:
                    System.out.print("Introdueix el nom de l'activitat: ");
                    String nomActDetall = scanner.nextLine();
                    llistaActivitats.mostrarDetallActivitatNom(nomActDetall);
                    break;

                case 8:
                    System.out.print("Introdueix l'àlies de l'usuari: ");
                    String aliesUsuari = scanner.nextLine();
                    llistaUsuaris.mostrarDetallUsuariNom(aliesUsuari);
                    break;

                case 9:
                    System.out.print("Introdueix l'àlies de l'usuari: ");
                    String aliesActUser = scanner.nextLine();
                    llistaActivitats.mostrarActivitatsUsuari(aliesActUser);
                    break;

                case 10:
                    // Passem també la llista d'inscripcions per si cal guardar-ho allà
                    inscriureUsuari(llistaActivitats, llistaUsuaris, llistaInscripcions, avui);
                    break;

                case 11:
                    menuLogic.mostrarUsuarisApuntatsIEspera(llistaActivitats);
                    break;

                case 12:
                    menuLogic.eliminarUsuariActivitat(llistaActivitats);
                    break;

                case 13:
                    afegirActivitatUnDia(llistaActivitats);
                    break;

                case 14:
                    afegirActivitatPeriodica(llistaActivitats);
                    break;

                case 15:
                    menuLogic.afegirActivitatOnline(llistaActivitats);
                    break;

                case 16:
                    System.out.print("Nom de l'activitat a valorar: ");
                    String nomVal = scanner.nextLine();
                    System.out.print("Àlies de l'usuari: ");
                    String userVal = scanner.nextLine();
                    System.out.print("Puntuació (0-10): ");
                    try {
                        int punts = Integer.parseInt(scanner.nextLine());
                        llistaActivitats.valorarActivitat(nomVal, userVal, punts, avui);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Puntuació invàlida.");
                    }
                    break;

                case 17:
                    System.out.println("\n--- RESUM ACTIVITATS ACABADES ---");
                    llistaActivitats.mostrarResumActivitatAcabada(avui);
                    break;

                case 18:
                    menuLogic.mostrarResumValoracionsUsuari(llistaActivitats);
                    break;

                case 19:
                    System.out.print("Introdueix el nom de l'activitat: ");
                    String nomMitjana = scanner.nextLine();
                    llistaActivitats.mostrarMitjanaValoracions(nomMitjana);
                    break;

                case 20:
                    menuLogic.calcularUsuariMesActiuColectiu(llistaActivitats, llistaUsuaris);
                    break;

                case 21:
                    menuLogic.donarBaixaActivitats(llistaActivitats, avui);
                    break;

                case 22:
                    System.out.println("\n--- SORTIR I GUARDAR ---");
                    System.out.print("Vols guardar els canvis als fitxers? (S/N): ");
                    String resp = scanner.nextLine().trim().toUpperCase();

                    if (resp.equals("S")) {
                        System.out.println("Guardant dades...");
                        try {
                            // GUARDAR TOTES LES LLISTES ALS SEUS FITXERS CORRESPONENTS
                            llistaActivitats.guardarActivitatsFitxer("provaActivitats.txt");
                            llistaUsuaris.guardarUsuarisFitxer("prova.txt");
                            
                            // Guardar el fitxer serialitzat d'inscripcions
                            llistaInscripcions.guardarInscripcions("totes_inscripcions.dat");
                            
                            System.out.println("Dades guardades correctament a:");
                            System.out.println("- prova.txt");
                            System.out.println("- provaActivitats.txt");
                            System.out.println("- totes_inscripcions.dat");
                            
                        } catch (Exception e) {
                            System.out.println("Error guardant els fitxers: " + e.getMessage());
                        }
                        sortir = true;
                    } else if (resp.equals("N")) {
                        System.out.println("Sortint sense guardar. Els canvis es perdran.");
                        sortir = true;
                    } else {
                        System.out.println("Opció no vàlida.");
                    }
                    break;
                    
                default:
                    System.out.println("Opció invàlida.");
                    break;
            }
        }
    }

    // --- MÈTODES AUXILIARS ---

    public static void mostrarMenu() {
        System.out.println("\n==================================================");
        System.out.println("--- MENÚ PRINCIPAL BENESTAR URV ---");
        System.out.println("1. Canviar data sistema");
        System.out.println("2. Mostrar Llistes (Usuaris i Activitats)");
        System.out.println("3. Activitats en període d'inscripció");
        System.out.println("4. Activitats amb classe avui");
        System.out.println("5. Activitats actives avui");
        System.out.println("6. Activitats amb places disponibles");
        System.out.println("7. Detall d'una activitat");
        System.out.println("8. Detall d'un usuari");
        System.out.println("9. Activitats d'un usuari");
        System.out.println("10. Inscriure un usuari a una activitat");
        System.out.println("11. Llistar inscrits i llista d'espera");
        System.out.println("12. Eliminar un usuari d'una activitat");
        System.out.println("13. Afegir activitat d'un dia");
        System.out.println("14. Afegir activitat periòdica");
        System.out.println("15. Afegir activitat en línia");
        System.out.println("16. Valorar activitat finalitzada");
        System.out.println("17. Resum valoracions activitats acabades");
        System.out.println("18. Resum valoracions per usuari");
        System.out.println("19. Mitjana valoracions per col·lectiu");
        System.out.println("20. Usuari més actiu d'un col·lectiu");
        System.out.println("21. Donar de baixa activitats (baixa participació)");
        System.out.println("22. SORTIR I GUARDAR");
        System.out.println("==================================================");
        System.out.print("Selecciona opció: ");
    }

    private static void inscriureUsuari(LlistaActivitats llistaAct, LlistaUsuaris llistaUsu, LlistaInscripcions llistaIns, LocalDate dataActual) {
        System.out.println("\n--- INSCRIURE USUARI ---");
        System.out.print("Introdueix l'àlies de l'usuari: ");
        String alies = scanner.nextLine();
        
        Usuari usuariTrobat = null;
        // Busquem l'usuari manualment si la llista no té mètode de cerca directa
        for (int i = 0; i < llistaUsu.getNumUsuaris(); i++) {
            if (llistaUsu.getUsuari(i).getAlies().equalsIgnoreCase(alies)) {
                usuariTrobat = llistaUsu.getUsuari(i);
                break;
            }
        }

        if (usuariTrobat == null) {
            System.out.println("Error: Usuari no trobat.");
            return;
        }

        System.out.print("Introdueix el nom de l'activitat: ");
        String nomAct = scanner.nextLine();

        // Realitzem la inscripció a la llista d'activitats
        // NOTA: Si la lògica d'inscripció també ha d'actualitzar LlistaInscripcions,
        // s'hauria de fer aquí o dins del mètode inscriureUsuariActivitat si es modifiqués.
        // Assumim que la lògica principal està a LlistaActivitats.
        
        try {
            llistaAct.inscriureUsuariActivitat(usuariTrobat, nomAct, dataActual);
            // Si cal afegir-ho explícitament a la llista d'inscripcions global (pel fitxer .dat):
            // llistaIns.afegirInscripcio(novaInscripcio...); 
            // *Això depèn de com tingueu implementat el constructor d'Inscripció*
        } catch (Exception e) {
            System.out.println("Error en la inscripció: " + e.getMessage());
        }
    }

    private static void afegirActivitatUnDia(LlistaActivitats llista) {
        try {
            System.out.println("\n--- AFEGIR ACTIVITAT D'UN DIA ---");
            System.out.print("Nom: "); String nom = scanner.nextLine();
            System.out.print("Colectius (separats per comes): "); String[] colectius = scanner.nextLine().split(",");
            System.out.print("Data inici inscripció (AAAA-MM-DD): "); LocalDate dataInici = LocalDate.parse(scanner.nextLine());
            System.out.print("Data fi inscripció (AAAA-MM-DD): "); LocalDate dataFi = LocalDate.parse(scanner.nextLine());
            System.out.print("Data activitat (AAAA-MM-DD): "); LocalDate dataAct = LocalDate.parse(scanner.nextLine());
            System.out.print("Ciutat: "); String ciutat = scanner.nextLine();
            System.out.print("Preu: "); double preu = Double.parseDouble(scanner.nextLine());
            System.out.print("Places: "); int places = Integer.parseInt(scanner.nextLine());
            System.out.print("Horari: "); String horari = scanner.nextLine();
            
            ActivitatUnDia act = new ActivitatUnDia(nom, colectius, dataInici, dataFi, dataAct, ciutat, preu, places, horari);
            llista.afegirActivitatUnDia(act);
            System.out.println("Activitat afegida.");
        } catch (Exception e) {
            System.out.println("Error dades: " + e.getMessage());
        }
    }

    private static void afegirActivitatPeriodica(LlistaActivitats llista) {
        try {
            System.out.println("\n--- AFEGIR ACTIVITAT PERIÒDICA ---");
            System.out.print("Nom: "); String nom = scanner.nextLine();
            System.out.print("Colectius: "); String[] colectius = scanner.nextLine().split(",");
            System.out.print("Data inici inscripció: "); LocalDate di = LocalDate.parse(scanner.nextLine());
            System.out.print("Data fi inscripció: "); LocalDate df = LocalDate.parse(scanner.nextLine());
            System.out.print("Dia setmana: "); String dia = scanner.nextLine();
            System.out.print("Horari: "); String hora = scanner.nextLine();
            System.out.print("Data inici curs: "); LocalDate dic = LocalDate.parse(scanner.nextLine());
            System.out.print("Num setmanes: "); int setm = Integer.parseInt(scanner.nextLine());
            System.out.print("Places: "); int places = Integer.parseInt(scanner.nextLine());
            System.out.print("Preu: "); double preu = Double.parseDouble(scanner.nextLine());
            System.out.print("Centre: "); String centre = scanner.nextLine();
            System.out.print("Ciutat: "); String ciutat = scanner.nextLine();

            ActivitatPeriodica act = new ActivitatPeriodica(nom, colectius, di, df, dia, hora, dic, setm, places, preu, centre, ciutat);
            llista.afegirActivitatPeriodica(act);
            System.out.println("Activitat afegida.");
        } catch (Exception e) {
            System.out.println("Error dades: " + e.getMessage());
        }
    }
}