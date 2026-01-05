package aplicacio;

import dades.activitats.*;
import dades.inscripcions.*;
import dades.usuaris.*;
import java.time.LocalDate;
import java.util.Scanner;
import opcionsmenu.menuAran;

public class main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. INICIALITZACIÓ DE LLISTES
        LlistaActivitats llistaActivitats = new LlistaActivitats(100);
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(100);
        LlistaInscripcions llistaInscripcions; // La definim però la instanciem al carregar

        // 2. CÀRREGA DE DADES
        System.out.println("Carregant dades del sistema...");
        
        // Càrrega d'Usuaris (està a l'arrel del projecte)
        llistaUsuaris.carregarUsuarisFitxer("prova.txt");

        // Càrrega d'Activitats (està dins de la carpeta src)
        // MODIFICAT: Afegim "src/" perquè el trobi sense moure'l ni canviar la classe
        llistaActivitats.carregarActivitatsFitxer("src/provaActivitats.txt");

        // Càrrega d'Inscripcions (fitxer serialitzat)
        try {
            // Assignem el resultat a la variable (IMPORTANT: no cridar-ho a l'aire)
            llistaInscripcions = LlistaInscripcions.carregarLlistaSerialitzada("totes_inscripcions.dat");
            System.out.println("Inscripcions carregades correctament.");
        } catch (Exception e) {
            System.out.println("Avís: Creant nova llista d'inscripcions (fitxer no existent o buit).");
            llistaInscripcions = new LlistaInscripcions(100);
        }
        System.out.println("--------------------------------------------------");

        // Objectes auxiliars
        menuAran menuLogic = new menuAran(); 
        LocalDate avui = LocalDate.now(); 
        
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
                case 1: // Canviar data
                    avui = llistaActivitats.moddataActual(avui);
                    break;

                case 2: // Mostrar llistes
                    System.out.println("\n--- LLISTA D'ACTIVITATS ---");
                    llistaActivitats.mostrarLlista(0); 
                    System.out.println("\n--- LLISTA D'USUARIS ---");
                    System.out.println(llistaUsuaris.toString());
                    break;

                case 3: // Període inscripció
                    llistaActivitats.mostrarActivitatsDisponibles(avui);
                    break;

                case 4: // Amb classe avui
                    System.out.println("\n--- ACTIVITATS AMB CLASSE AVUI (" + avui + ") ---");
                    llistaActivitats.mostrarActivitatsClasseAvui(avui);
                    break;

                case 5: // Actives avui
                    menuLogic.mostrarActivitatsAvui(llistaActivitats, avui);
                    break;

                case 6: // Places disponibles
                    llistaActivitats.mostrarActivitatsAmbPlaces();
                    break;

                case 7: // Detall activitat
                    System.out.print("Introdueix el nom de l'activitat: ");
                    String nomActDetall = scanner.nextLine();
                    llistaActivitats.mostrarDetallActivitatNom(nomActDetall);
                    break;

                case 8: // Detall usuari
                    System.out.print("Introdueix l'àlies de l'usuari: ");
                    String aliesUsuari = scanner.nextLine();
                    llistaUsuaris.mostrarDetallUsuariNom(aliesUsuari);
                    break;

                case 9: // Activitats usuari
                    System.out.print("Introdueix l'àlies de l'usuari: ");
                    String aliesActUser = scanner.nextLine();
                    llistaActivitats.mostrarActivitatsUsuari(aliesActUser);
                    break;

                case 10: // Inscriure
                    inscriureUsuari(llistaActivitats, llistaUsuaris, llistaInscripcions, avui);
                    break;

                case 11: // Llistar inscrits
                    menuLogic.mostrarUsuarisApuntatsIEspera(llistaActivitats);
                    break;

                case 12: // Eliminar usuari
                    menuLogic.eliminarUsuariActivitat(llistaActivitats);
                    break;

                case 13: // Afegir activitat dia
                    afegirActivitatUnDia(llistaActivitats);
                    break;

                case 14: // Afegir activitat periòdica
                    afegirActivitatPeriodica(llistaActivitats);
                    break;

                case 15: // Afegir activitat online
                    menuLogic.afegirActivitatOnline(llistaActivitats);
                    break;

                case 16: // Valorar
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

                case 17: // Resum acabades
                    System.out.println("\n--- RESUM ACTIVITATS ACABADES ---");
                    llistaActivitats.mostrarResumActivitatAcabada(avui);
                    break;

                case 18: // Resum usuari
                    menuLogic.mostrarResumValoracionsUsuari(llistaActivitats);
                    break;

                case 19: // Mitjana col·lectiu
                    System.out.print("Introdueix el nom de l'activitat: ");
                    String nomMitjana = scanner.nextLine();
                    llistaActivitats.mostrarMitjanaValoracions(nomMitjana);
                    break;

                case 20: // Usuari més actiu
                    menuLogic.calcularUsuariMesActiuColectiu(llistaActivitats, llistaUsuaris);
                    break;

                case 21: // Baixa per poca participació
                    menuLogic.donarBaixaActivitats(llistaActivitats, avui);
                    break;

                case 22: // SORTIR I GUARDAR
    System.out.println("\n--- SORTIR DE L'APLICACIÓ ---");
    System.out.print("Vols guardar les dades abans de sortir? (S/N): ");
    String resp = scanner.nextLine().trim().toUpperCase();

    if (resp.equals("S")) {
        System.out.println("Guardant dades...");
        try {
            // 1. Guardar Usuaris
            llistaUsuaris.guardarUsuarisFitxer("prova.txt");
            
            // 2. Guardar Activitats
            llistaActivitats.guardarActivitatsFitxer("src/provaActivitats.txt");
            
            // 3. RECOPILAR I GUARDAR INSCRIPCIONS (Això és el que faltava)
            // Creem una llista nova per ajuntar totes les inscripcions actuals
            LlistaInscripcions llistaGlobalParaGuardar = new LlistaInscripcions(1000);
            
            // Recorrem totes les activitats per treure'n les inscripcions
            for (int i = 0; i < llistaActivitats.getnElems(); i++) {
                Activitat act = llistaActivitats.getActivitat(i);
                LlistaInscripcions inscripcionsAct = act.getLlistaInscripcions();
                
                if (inscripcionsAct != null) {
                    for(int j = 0; j < inscripcionsAct.getNumInscripcions(); j++) {
                        // Copiem cada inscripció a la llista global
                        llistaGlobalParaGuardar.afegirInscripcio(inscripcionsAct.getInscripcio(j).copia());
                    }
                }
            }
            
            
            llistaGlobalParaGuardar.guardarLlistaSerialitzada("totes_inscripcions.dat");
                    
            System.out.println("Dades guardades correctament.");
                } catch (Exception e) {
                    System.out.println("Error al guardar: " + e.getMessage());
                    e.printStackTrace(); 
                }
                sortir = true;
                    } else if (resp.equals("N")) {
                        System.out.println("Sortint sense guardar.");
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
        System.out.println("16. Valorar una activitat finalitzada");
        System.out.println("17. Resum de valoracions (activitats acabades)");
        System.out.println("18. Resum de valoracions per usuari");
        System.out.println("19. Mitjana de valoracions per col·lectiu");
        System.out.println("20. Usuari més actiu d'un col·lectiu");
        System.out.println("21. Donar de baixa activitats amb poca participació");
        System.out.println("22. Sortir de l'aplicació");
        System.out.println("==================================================");
        System.out.print("Escull una opció: ");
    }

    private static void inscriureUsuari(LlistaActivitats llistaAct, LlistaUsuaris llistaUsu, LlistaInscripcions llistaIns, LocalDate dataActual) {
        System.out.println("\n--- INSCRIURE USUARI ---");
        System.out.print("Introdueix l'àlies de l'usuari: ");
        String alies = scanner.nextLine();
        
        Usuari usuariTrobat = null;
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

        // Intentem inscriure a l'Activitat
        llistaAct.inscriureUsuariActivitat(usuariTrobat, nomAct, dataActual);
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
        } catch (Exception e) {
            System.out.println("Error introduint dades: " + e.getMessage());
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
            System.out.println("Error introduint dades: " + e.getMessage());
        }
    }
}