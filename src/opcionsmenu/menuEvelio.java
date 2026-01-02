package opcionsmenu;

import dades.*;
import dades.usuaris.*;
import java.time.LocalDate;
import java.util.Scanner;

public class menuEvelio {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LocalDate dataSistema = LocalDate.now(); // Data inicial del sistema

        System.out.println("--- INICIALITZANT DADES DE PROVA ---");

        //Creem llista d'usuaris i alguns usuaris
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(10);
        
        Usuari[] meusUsuaris = new Usuari[10];
        
        PDI u1 = new PDI("JGarcia", "joan.garcia", "Informàtica", "Sescelades");
        PTGAS u2 = new PTGAS("MRovira", "maria.rovira", "Catalunya");
        
        // Afegim a la llista del sistema
        llistaUsuaris.afegirUsuari(u1);
        llistaUsuaris.afegirUsuari(u2);
        
        // Afegim a l'array local per fer proves
        meusUsuaris[0] = u1;
        meusUsuaris[1] = u2;

        System.out.println("Usuaris creats: JGarcia (PDI), MRovira (PTGAS)");

        // 2. Creem llista d'activitats i algunes activitats
        LlistaActivitats laMevaLlista = new LlistaActivitats(20);
        String[] colTots = {"PDI", "PTGAS"};
        String[] colPDI = {"PDI"};

        // Activitat 1: Futbol (Periodica) - Ja activa
        ActivitatPeriodica futbol = new ActivitatPeriodica(
            "Futbol", colTots, 
            LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), // Inscripció oberta
            "Dilluns", "18:00", LocalDate.now().plusDays(5), 10, 
            20, 50.0, "Poliesportiu", "Barcelona"
        );

        // Activitat 2: Basket (Periodica) - Plena (simulació)
        ActivitatPeriodica basket = new ActivitatPeriodica(
            "Basket", colPDI, 
            LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 
            "Dimarts", "19:00", LocalDate.now().plusDays(5), 10, 
            2, 40.0, "Pavelló", "Girona" // Només 2 places
        );
        basket.setnInscrits(2); // Forcem que estigui plena per provar Tasca 6

        // Activitat 3: Java (Online) - Passada (per provar valoracions)
        ActivitatOnline javaCurs = new ActivitatOnline(
            "Java", colTots, 
            LocalDate.now().minusMonths(2), LocalDate.now().minusMonths(1), 
            "zoom.us", LocalDate.now().minusMonths(1), 30 // Ja ha acabat
        );
        // Inscrivim a JGarcia manualment al curs de Java per poder valorar-lo després
        javaCurs.afegirInscripcio("JGarcia", LocalDate.now().minusMonths(1), "PDI");

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
            System.out.println("0.  Sortir");
            System.out.print("Escull una opció: ");

            int opcio = -1;
            try {
                opcio = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Introdueix un número.");
                continue;
            }

            switch (opcio) {
                case 1: // TASCA 1
                    System.out.println("\n--- TASCA 1: MODIFICAR DATA ---");
                    // Cridem al mètode de la llista que demana la nova data per teclat
                    dataSistema = laMevaLlista.moddataActual(dataSistema);
                    break;

                case 3: // TASCA 3
                    System.out.println("\n--- TASCA 3: DISPONIBLES PER INSCRIPCIÓ ---");
                    // Mostra activitats on dataSistema està dins del període d'inscripció I hi ha places
                    laMevaLlista.mostrarActivitatsDisponibles(dataSistema);
                    break;

                case 6: // TASCA 6
                    System.out.println("\n--- TASCA 6: DISPONIBLES (PLACES) ---");
                    // Mostra activitats que tenen places (ignora dates)
                    // Hauria de sortir 'Futbol' i 'Java', però NO 'Basket' (està plena)
                    laMevaLlista.mostrarActivitatsAmbPlaces();
                    break;

                case 10: // TASCA 10
                    System.out.println("\n--- TASCA 10: INSCRIURE USUARI ---");
                    System.out.println("Usuaris disponibles per prova: JGarcia, MRovira");
                    System.out.print("Introdueix l'alias de l'usuari: ");
                    String alies = scanner.nextLine();
                    
                    System.out.print("Introdueix el nom de l'activitat (ex: Futbol): ");
                    String nomAct = scanner.nextLine();

                    // Busquem l'objecte usuari en el nostre array local
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

                case 13: // TASCA 13
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
                        
                        // Per agilitzar, posem inscripció d'avui a data activitat
                        ActivitatUnDia novaAct = new ActivitatUnDia(
                            nom, colTots, 
                            dataSistema, data, // Període inscripció
                            data, ciutat, preu, places, "10:00"
                        );
                        
                        laMevaLlista.afegirActivitatUnDia(novaAct);
                        
                    } catch (Exception e) {
                        System.out.println("Error introduint dades: " + e.getMessage());
                    }
                    break;

                case 16: // TASCA 16
                    System.out.println("\n--- TASCA 16: VALORAR ACTIVITAT ---");
                    System.out.println("NOTA: Per valorar, l'activitat ha d'haver acabat respecte a la Data Actual.");
                    System.out.println("Suggeriment: Prova amb l'activitat 'Java' i usuari 'JGarcia'.");
                    
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
                
                case 19: // TASCA 19
                    System.out.println("\n--- TASCA 19: MITJANES PER COL·LECTIU ---");
                    System.out.print("Introdueix el nom de l'activitat (Prova amb 'Curs Java'): ");
                    String nomActivitat = scanner.nextLine();
                    laMevaLlista.mostrarMitjanaValoracions(nomActivitat);
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