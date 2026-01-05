package aplicacio;

import dades.activitats.Activitat;
import dades.activitats.ActivitatOnline;
import dades.activitats.ActivitatPeriodica;
import dades.activitats.ActivitatUnDia;
import dades.activitats.LlistaActivitats;
import dades.inscripcions.inscripcions;
import dades.usuaris.*;
import java.time.LocalDate;
import java.util.Scanner;
import opcionsmenu.menuAran;

public class TestMenuAran {

    private static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      TEST INTERACTIU MENU ARAN (BASE DE DADES CARREGADA)");
        System.out.println("==================================================");

        // 1. INICIALITZACIÓ DE DADES (Base de dades simulada)
        LlistaActivitats llistaActivitats = new LlistaActivitats(20);
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(20);

        inicialitzarDades(llistaActivitats, llistaUsuaris);

        menuAran menuLogic = new menuAran(); // Aquest objecte té el seu propi Scanner
        boolean sortir = false;
        LocalDate avui = LocalDate.now();

        // 2. BUCLE DE PROVES
        while (!sortir) {
            System.out.println("\n--------------------------------------------------");
            System.out.println(" QUINA OPCIÓ VOLS PROVAR?");
            System.out.println("--------------------------------------------------");
            System.out.println(" 5.  Activitats actives avui (Mostra llista)");
            System.out.println(" 11. Usuaris apuntats i llista d'espera");
            System.out.println(" 12. Eliminar usuari d'una activitat");
            System.out.println(" 15. Afegir una activitat online");
            System.out.println(" 18. Resum valoracions d'un usuari");
            System.out.println(" 20. Usuari més actiu d'un col·lectiu");
            System.out.println(" 21. Donar de baixa activitats amb poca participació");
            System.out.println(" 0.  Sortir");
            System.out.println("--------------------------------------------------");
            System.out.print("Escull una opció: ");
            
            int opcio;
            try {
                opcio = Integer.parseInt(teclat.nextLine());
            } catch (NumberFormatException e) {
                opcio = -1;
            }

            switch (opcio) {
                case 5:
                    System.out.println("\n[CONTEXT] Data simulada d'avui: " + avui);
                    System.out.println("[ACCIÓ] Cridant mostrarActivitatsAvui()...");
                    menuLogic.mostrarActivitatsAvui(llistaActivitats, avui);
                    break;

                case 11:
                    System.out.println("\n[CONTEXT] Llista d'activitats disponibles per consultar:");
                    llistaActivitats.mostrarLlista(0); // 0 = Mostrar totes
                    System.out.println("\n[ACCIÓ] Ara el programa et demanarà el nom d'una d'aquestes activitats.");
                    menuLogic.mostrarUsuarisApuntatsIEspera(llistaActivitats);
                    break;

                case 12:
                    System.out.println("\n[CONTEXT] Llista d'activitats per triar:");
                    llistaActivitats.mostrarLlista(0);
                    System.out.println("\n[NOTA] Usuaris suggerits per eliminar: 'JGarcia' (PDI), 'Marta03' (PTGAS), 'evelio' (Estudiant)");
                    System.out.println("[ACCIÓ] Cridant eliminarUsuariActivitat()...");
                    menuLogic.eliminarUsuariActivitat(llistaActivitats);
                    break;

                case 15:
                    System.out.println("\n[CONTEXT] Activitats actuals abans d'afegir-ne una de nova:");
                    llistaActivitats.mostrarLlista(0);
                    System.out.println("\n[ACCIÓ] Introdueix les dades de la nova activitat ONLINE.");
                    menuLogic.afegirActivitatOnline(llistaActivitats);
                    
                    System.out.println("\n[RESULTAT] Llista actualitzada:");
                    llistaActivitats.mostrarLlista(0);
                    break;

                case 18:
                    System.out.println("\n[CONTEXT] Usuaris registrats al sistema:");
                    System.out.println(llistaUsuaris.toString());
                    System.out.println("[ACCIÓ] Introdueix l'àlies de l'usuari per veure valoracions.");
                    menuLogic.mostrarResumValoracionsUsuari(llistaActivitats);
                    break;

                case 20:
                    System.out.println("\n[CONTEXT] Usuaris i les seves inscripcions ja estan carregats.");
                    System.out.println("[ACCIÓ] Cridant calcularUsuariMesActiuColectiu()...");
                    menuLogic.calcularUsuariMesActiuColectiu(llistaActivitats, llistaUsuaris);
                    break;

                case 21:
                    System.out.println("\n[CONTEXT] Llista ABANS de la neteja:");
                    llistaActivitats.mostrarLlista(0);
                    System.out.println("\n[NOTA] L'activitat 'Webinar Antic' (Online) té pocs inscrits i ja ha passat.");
                    System.out.println("[ACCIÓ] Executant neteja amb data d'avui (" + avui + ")...");
                    menuLogic.donarBaixaActivitats(llistaActivitats, avui);
                    
                    System.out.println("\n[RESULTAT] Llista DESPRÉS de la neteja:");
                    llistaActivitats.mostrarLlista(0);
                    break;

                case 0:
                    System.out.println("Sortint del test...");
                    sortir = true;
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
            
            if (!sortir) {
                System.out.println("\n(Prem Enter per continuar)");
                teclat.nextLine();
            }
        }
    }

    /**
     * Mètode auxiliar per crear un entorn de proves realista.
     */
    private static void inicialitzarDades(LlistaActivitats activitats, LlistaUsuaris usuaris) {
        System.out.println("--> Inicialitzant Activitats, Usuaris i Inscripcions...");

        // 1. CREACIÓ D'USUARIS
        PDI pdi = new PDI("JGarcia", "joan.garcia", "Informàtica", "Sescelades");
        PTGAS ptgas = new PTGAS("Marta03", "marta.ruiz", "Catalunya");
        Estudiant est = new Estudiant("evelio", "evelio.alumne", "GEI", 2023);
        Estudiant est2 = new Estudiant("maria", "maria.alumne", "Mecànica", 2022);

        usuaris.afegirUsuari(pdi);
        usuaris.afegirUsuari(ptgas);
        usuaris.afegirUsuari(est);
        usuaris.afegirUsuari(est2);

        // 2. CREACIÓ D'ACTIVITATS
        LocalDate avui = LocalDate.now();

        // A. Activitat Un Dia (Futura)
        ActivitatUnDia taller = new ActivitatUnDia(
            "Taller Java", new String[]{"Estudiants", "PDI"},
            avui.minusDays(5), avui.plusDays(10), // Inscripció oberta
            avui.plusDays(15), "Tarragona", 10.0, 5, "10:00"
        );

        // B. Activitat Periòdica (En curs)
        ActivitatPeriodica ioga = new ActivitatPeriodica(
            "Ioga Matins", new String[]{"Tothom"},
            avui.minusDays(20), avui.plusDays(20), // Inscripció oberta
            "Dilluns", "08:00", avui.plusDays(5), 10, 20, 50.0, "Gimnàs", "Reus"
        );

        // C. Activitat Online (Antiga i amb pocs inscrits -> Candidata a esborrar per opció 21)
        ActivitatOnline webinarAntic = new ActivitatOnline(
            "Webinar Antic", new String[]{"PDI"},
            avui.minusDays(60), avui.minusDays(10), // Inscripció tancada fa dies
            "http://zoom.us", avui.minusDays(5), 30
        );

        activitats.afegirActivitat(taller);
        activitats.afegirActivitat(ioga);
        activitats.afegirActivitat(webinarAntic);

        // 3. INSCRIPCIONS (Simulem que la gent s'apunta)
        
        // JGarcia s'apunta a tot
        inscriureUsuari(taller, "JGarcia", 9); // Valoració 9
        inscriureUsuari(ioga, "JGarcia", -1);  // Sense valorar

        // Marta03 s'apunta al taller
        inscriureUsuari(taller, "Marta03", 8);

        // Evelio s'apunta al Ioga
        inscriureUsuari(ioga, "evelio", -1);

        // Maria s'apunta al Webinar Antic (és l'única, així que té <20 inscrits)
        inscriureUsuari(webinarAntic, "maria", 5);

        System.out.println("--> Base de dades carregada amb èxit! (" + usuaris.getNumUsuaris() + " usuaris, " + activitats.getnElems() + " activitats).");
    }

    private static void inscriureUsuari(Activitat act, String alies, int valoracio) {
        // Creem la inscripció
        inscripcions ins = new inscripcions(alies, "", LocalDate.now());
        
        // Si té valoració, la posem
        if (valoracio != -1) {
            ins.valorarExperiencia(valoracio);
        }

        // Afegim a la llista d'inscrits de l'activitat
        if (act.hihaPlaces()) {
            act.getLlistaInscripcions().afegirInscripcio(ins);
            
            // IMPORTANT: Actualitzem comptadors interns específics de cada tipus
            if (act instanceof ActivitatUnDia) {
                ((ActivitatUnDia) act).incnInscrits();
            } else if (act instanceof ActivitatPeriodica) {
                ((ActivitatPeriodica) act).incnInscrits();
            }
            // ActivitatOnline no té comptador de places limitat, no cal fer res especial
        } else {
            act.getLlistaEspera().afegirInscripcio(ins);
        }
    }
}