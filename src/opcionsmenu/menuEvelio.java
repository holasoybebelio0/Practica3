package opcionsmenu;

import dades.activitats.Activitat;
import dades.activitats.ActivitatOnline;
import dades.activitats.ActivitatPeriodica;
import dades.activitats.ActivitatUnDia;
import dades.activitats.LlistaActivitats;
import dades.inscripcions.LlistaInscripcions;
import dades.inscripcions.inscripcions;
import dades.usuaris.*;
import java.time.LocalDate;
import java.util.Scanner;

public class menuEvelio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate dataSistema = LocalDate.now(); // Data inicial del sistema

        System.out.println("--- INICIALITZANT DADES DE PROVA ---");

        // 1. Creem llista d'usuaris i alguns usuaris
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(10);
        
        Usuari[] meusUsuaris = new Usuari[10];
        
        PDI u1 = new PDI("JGarcia", "joan.garcia", "Informàtica", "Sescelades");
        PTGAS u2 = new PTGAS("MRovira", "maria.rovira", "Catalunya");
        
        // Afegim a la llista del sistema
        llistaUsuaris.afegirUsuari(u1);
        llistaUsuaris.afegirUsuari(u2);
        
        // Afegim a l'array local per fer proves ràpides al menú
        meusUsuaris[0] = u1;
        meusUsuaris[1] = u2;

        System.out.println("Usuaris creats: JGarcia (PDI), MRovira (PTGAS)");

        // 2. Creem llista d'activitats i algunes activitats
        LlistaActivitats laMevaLlista = new LlistaActivitats(20);
        String[] colTots = {"PDI", "PTGAS"};
        String[] colPDI = {"PDI"};

        // Activitat 1: Futbol
        ActivitatPeriodica futbol = new ActivitatPeriodica(
            "Futbol", colTots, 
            LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 
            "Dilluns", "18:00", LocalDate.now().plusDays(5), 10, 
            20, 50.0, "Poliesportiu", "Barcelona"
        );

        // Activitat 2: Basket (Plena)
        ActivitatPeriodica basket = new ActivitatPeriodica(
            "Basket", colPDI, 
            LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 
            "Dimarts", "19:00", LocalDate.now().plusDays(5), 10, 
            2, 40.0, "Pavelló", "Girona"
        );
        basket.setnInscrits(2); 

        // Activitat 3: Java (Acabada)
        ActivitatOnline javaCurs = new ActivitatOnline(
            "Java", colTots, 
            LocalDate.now().minusMonths(2), LocalDate.now().minusMonths(1), 
            "zoom.us", LocalDate.now().minusMonths(1), 30 
        );
        
        // Inscripció manual inicial
        inscripcions inscripcioJava = new inscripcions("JGarcia", "PDI", LocalDate.now().minusMonths(1));
        javaCurs.afegirInscripcio(inscripcioJava, LocalDate.now().minusMonths(1));

        laMevaLlista.afegirActivitat(futbol);
        laMevaLlista.afegirActivitat(basket);
        laMevaLlista.afegirActivitat(javaCurs);

        System.out.println("Activitats creades: Futbol (Oberta), Basket (Plena), Java (Acabada/Tancada)");
        System.out.println("----------------------------------------\n");

        
        // ==========================================
        // MENU INTERACTIU
        // ==========================================
        boolean sortir = false;
        while (!sortir) {
            System.out.println("\n========================================");
            System.out.println("   MENÚ DE PROVES - DATA ACTUAL: " + dataSistema);
            System.out.println("========================================");
            System.out.println("1.  [Tasca 1]  Modificar Data del Sistema");
            System.out.println("3.  [Tasca 3]  Mostrar activitats disponibles (Inscripció oberta i places lliures)");
            System.out.println("6.  [Tasca 6]  Mostrar activitats amb places (sense filtre de data)");
            System.out.println("10. [Tasca 10] Inscriure usuari a activitat");
            System.out.println("13. [Tasca 13] Afegir nova Activitat d'Un Dia");
            System.out.println("16. [Tasca 16] Valorar una activitat (ha d'haver acabat)");
            System.out.println("19. [Tasca 19] Mostrar mitjana de valoracions per col·lectius");
            System.out.println("22. [Extra]    Guardar TOTES les inscripcions en fitxer serialitzat");
            System.out.println("0.  Sortir");
            System.out.print("Escull una opció: ");

            int opcio = -1;
            try {
                String input = scanner.nextLine();
                if (input.isEmpty()) continue;
                opcio = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Introdueix un número.");
                continue;
            }

            switch (opcio) {
                case 1: 
                    System.out.println("\n--- TASCA 1: MODIFICAR DATA ---");
                    dataSistema = laMevaLlista.moddataActual(dataSistema);
                    break;

                case 3: 
                    System.out.println("\n--- TASCA 3: DISPONIBLES PER INSCRIPCIÓ ---");
                    laMevaLlista.mostrarActivitatsDisponibles(dataSistema);
                    break;

                case 6: 
                    System.out.println("\n--- TASCA 6: DISPONIBLES (PLACES) ---");
                    laMevaLlista.mostrarActivitatsAmbPlaces();
                    break;

                case 10: 
                    System.out.println("\n--- TASCA 10: INSCRIURE USUARI ---");
                    System.out.println("Usuaris disponibles per prova: JGarcia, MRovira");
                    
                    System.out.print("Introdueix l'alias de l'usuari: ");
                    String alies = scanner.nextLine();
                    
                    System.out.print("Introdueix el nom de l'activitat (ex: Futbol): ");
                    String nomAct = scanner.nextLine();

                    Usuari usuariTrobat = null;
                    for (Usuari u : meusUsuaris) {
                        if (u != null && u.getAlies().equalsIgnoreCase(alies)) {
                            usuariTrobat = u;
                            break;
                        }
                    }

                    if (usuariTrobat != null) {
                        laMevaLlista.inscriureUsuariActivitat(usuariTrobat, nomAct, dataSistema);
                    } else {
                        System.out.println("Error: Usuari no trobat a la llista local de proves.");
                    }
                    break;

                case 13: 
                    System.out.println("\n--- TASCA 13: AFEGIR ACTIVITAT UN DIA ---");
                    try {
                        System.out.print("Nom de l'activitat: ");
                        String nom = scanner.nextLine();
                        System.out.print("Data de l'activitat (AAAA-MM-DD): ");
                        LocalDate data = LocalDate.parse(scanner.nextLine());
                        System.out.print("Ciutat: ");
                        String ciutat = scanner.nextLine();
                        System.out.print("Preu: ");
                        double preu = Double.parseDouble(scanner.nextLine());
                        System.out.print("Places totals: ");
                        int places = Integer.parseInt(scanner.nextLine());
                        
                        ActivitatUnDia novaAct = new ActivitatUnDia(
                            nom, colTots, 
                            dataSistema, data, 
                            data, ciutat, preu, places, "10:00"
                        );
                        laMevaLlista.afegirActivitatUnDia(novaAct);
                    } catch (Exception e) {
                        System.out.println("Error introduint dades: " + e.getMessage());
                    }
                    break;

                case 16: 
                    System.out.println("\n--- TASCA 16: VALORAR ACTIVITAT ---");
                    System.out.print("Nom de l'activitat a valorar: ");
                    String nomVal = scanner.nextLine();
                    System.out.print("Alias de l'usuari: ");
                    String usuariVal = scanner.nextLine();
                    System.out.print("Puntuació (0-10): ");
                    
                    try {
                        int punts = Integer.parseInt(scanner.nextLine());
                        laMevaLlista.valorarActivitat(nomVal, usuariVal, punts, dataSistema);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: La puntuació ha de ser un número enter.");
                    }
                    break;
                
                case 19: 
                    System.out.println("\n--- TASCA 19: MITJANES PER COL·LECTIU ---");
                    System.out.print("Introdueix el nom de l'activitat (Prova amb 'Java'): ");
                    String nomActivitat = scanner.nextLine();
                    laMevaLlista.mostrarMitjanaValoracions(nomActivitat);
                    break;

                case 22: 
                    System.out.println("\n--- TASCA EXTRA: GUARDAR TOTES LES INSCRIPCIONS EN UN FITXER ---");
                    
                    // 1. Creem una llista gran per guardar-ho tot
                    LlistaInscripcions llistaGlobal = new LlistaInscripcions(100);
                    int totalAfegits = 0;

                    // 2. Recorrem totes les activitats i acumulem les inscripcions
                    System.out.println("Recopilant inscripcions de totes les activitats...");
                    for (int i = 0; i < laMevaLlista.getnElems(); i++) {
                        Activitat actActual = laMevaLlista.getActivitat(i);
                        LlistaInscripcions inscripcionsAct = actActual.getLlistaInscripcions();
                        
                        if (inscripcionsAct != null && inscripcionsAct.getNumInscripcions() > 0) {
                            for(int j=0; j < inscripcionsAct.getNumInscripcions(); j++) {
                                inscripcions insc = inscripcionsAct.getInscripcio(j);
                                // Copiem la inscripció a la llista global
                                llistaGlobal.afegirInscripcio(insc.copia());
                                totalAfegits++;
                            }
                        }
                    }
                    System.out.println("-> S'han trobat " + totalAfegits + " inscripcions en total.");

                    // 3. Guardem al fitxer serialitzat
                    String nomFitxerGlobal = "totes_inscripcions.dat";
                    try {
                        System.out.println("Generant fitxer serialitzat '" + nomFitxerGlobal + "'...");
                        llistaGlobal.guardarLlistaSerialitzada(nomFitxerGlobal);
                        
                        // 4. Verificació (Llegir i mostrar)
                        System.out.println("\n[VERIFICACIÓ] Llegint el fitxer que acabem de crear...");
                        LlistaInscripcions recuperadaGlobal = LlistaInscripcions.carregarLlistaSerialitzada(nomFitxerGlobal);
                        System.out.println("CONTINGUT DEL FITXER:");
                        System.out.println(recuperadaGlobal.toString());
                        
                    } catch (Exception e) {
                        System.out.println("Error guardant/carregant: " + e.getMessage());
                        e.printStackTrace();
                    }
                    break;

                case 0:
                    sortir = true;
                    System.out.println("Sortint del programa...");
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
        scanner.close();
    }
}