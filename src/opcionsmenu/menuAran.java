package opcionsmenu;
import dades.*;

import java.time.LocalDate;
import java.util.Scanner;

public class menuAran {
    /*
    5. Mostrar activitats actives avui (dins període).
    11. Mostrar usuaris apuntats i llista d’espera d’una activitat.
    12. Eliminar usuari d’una activitat i promocionar llista d’espera.
    15. Afegir una activitat online.
    18. Mostrar el resum de valoracions que ha fet un usuari.
    20. Calcular l’usuari més actiu d’un col·lectiu.
    21. Donar de baixa activitats amb poca participació.
    */
    private Scanner scanner;

    public menuAran() {
            this.scanner = new Scanner(System.in);
    }

    /**
     * Opció 5 del menú: Mostra per pantalla el nom de les activitats actives en la data indicada.
     * @param llista Llista d'activitats on buscar les activitats actives.
     * @param dataActual Data actual per comparar amb les dates d'inscripció de les activitats.
     */
    public void mostrarActivitatsAvui(LlistaActivitats llista, LocalDate dataActual) {
        System.out.println("--- ACTIVITATS ACTIVES AVUI ( " + dataActual + " ) ---");

        boolean algunaTrobada = false;
        int comptador = 0;

        for (int i = 0; i < llista.getnElems(); i++) {
            Activitat act = llista.getActivitat(i);

            if (act.esActiva(dataActual)) {
                comptador++;
                System.out.println("Activitat "+comptador+".\t"+ act.getNom() + "(Inscripcions obertes del " + act.getDataIniciInscripcio() + " al " + act.getDataFinalInscripcio() + ")");
                algunaTrobada = true;
            }
        }
        if (!algunaTrobada) {
            System.out.println("No hi ha cap activitat activa avui.");
        }
    }

    public void mostrarUsuarisApuntatsIEspera(){

    }

    public void eliminarUsuariActivitat(LlistaInscripcions llista){
        boolean trobat = false;
        int i = 0;
        System.out.println("Quin usuari vols esborrar?\n");
        String nom = scanner.nextLine();

        while(!trobat && i < llista.getNumInscripcions()){
            if(nom.equals(llista.getInscripcio(i).getNomParticipant())){
                for(int j = i; j  < llista.getNumInscripcions() - 1; j++){
                    llista.setInscripcioPosicio(j, llista.getInscripcio(j+1));
                }
                trobat = true;
            }
            else{
                i++;
            }
        }
        

    }

    public void afegirActivitatOnline(){

    }

    public void mostrarResumValoracionsUsuari(){

    }

    public void calcularUsuariMesActiuColectiu(){

    }

    public void donarBaixaActivitats(){

    }

    // =========================================================================
    // JOC DE PROVES ACTUALITZAT (AMB LOCALDATE) (borrar luego, lo dejo por si quereis probar algo)
    // =========================================================================
    public void executarJocDeProves() {
        System.out.println("\n*******************************************************");
        System.out.println("       INICIANT JOC DE PROVES: ACTIVITATS AVUI");
        System.out.println("*******************************************************");

        // 1. Definim la data simulada "AVUI": 15 d'Octubre de 2025
        LocalDate dataSimuladaAvui = LocalDate.of(2025, 10, 15);
        System.out.println("-> Data simulada del sistema: " + dataSimuladaAvui);

        // 2. Creem una llista buida
        LlistaActivitats llistaProva = new LlistaActivitats(10);
        String[] cols = {"Tothom"}; 

        // Dates comunes per inscripció (per no repetir codi)
        LocalDate iniciInsc = LocalDate.of(2025, 9, 1);
        LocalDate fiInsc = LocalDate.of(2025, 10, 10);

        try {
            // --- CAS 1: Activitat d'Un Dia ---
            
            // A) Coincideix EXACTAMENT amb avui (15/10/2025) -> HA DE SORTIR
            ActivitatUnDia unDiaOK = new ActivitatUnDia(
                "Taller Java (OK)", cols, iniciInsc, fiInsc,
                LocalDate.of(2025, 10, 15), // DATA ACTIVITAT = AVUI
                "Tarragona", 10.0, 20, "10:00"
            );

            // B) És per demà (16/10/2025) -> NO HA DE SORTIR
            ActivitatUnDia unDiaKO = new ActivitatUnDia(
                "Taller Python (KO - Demà)", cols, iniciInsc, fiInsc,
                LocalDate.of(2025, 10, 16), // DATA ACTIVITAT = DEMÀ
                "Reus", 10.0, 20, "10:00"
            );

            // --- CAS 2: Activitat Periòdica ---
            
            // C) Comença l'1/10 i dura 4 setmanes. El 15/10 està al mig. -> HA DE SORTIR
            ActivitatPeriodica periodicaOK = new ActivitatPeriodica(
                "Curs Ioga (OK)", cols, iniciInsc, fiInsc,
                "Dilluns", "18:00", 
                LocalDate.of(2025, 10, 1), // DATA INICI
                4, // 4 Setmanes de durada
                20, 120.0, "Gym", "Tarragona"
            );

            // D) Va començar a l'agost i durava 2 setmanes. Ja ha acabat. -> NO HA DE SORTIR
            ActivitatPeriodica periodicaKO = new ActivitatPeriodica(
                "Curs Pilates (KO - Caducat)", cols, iniciInsc, fiInsc,
                "Dimarts", "18:00", 
                LocalDate.of(2025, 8, 1), // DATA INICI (Agost)
                2, // 2 Setmanes
                20, 120.0, "Gym", "Reus"
            );

            // --- CAS 3: Activitat Online ---
            
            // E) Comença el 10/10 i dura 10 dies (fins al 20/10). El 15/10 està DINS. -> HA DE SORTIR
            ActivitatOnline onlineOK = new ActivitatOnline(
                "Webinar Spring (OK)", cols, iniciInsc, fiInsc,
                "http://urv.cat", 
                LocalDate.of(2025, 10, 10), // DATA INICI
                10 // DIES de visualització
            );

            // F) Comença al novembre. -> NO HA DE SORTIR
            ActivitatOnline onlineKO = new ActivitatOnline(
                "Webinar IA (KO - Futur)", cols, iniciInsc, fiInsc,
                "http://urv.cat", 
                LocalDate.of(2025, 11, 1), // DATA INICI
                10
            );

            // 3. Afegim tot a la llista
            llistaProva.afegirActivitat(unDiaOK);
            llistaProva.afegirActivitat(unDiaKO);
            llistaProva.afegirActivitat(periodicaOK);
            llistaProva.afegirActivitat(periodicaKO);
            llistaProva.afegirActivitat(onlineOK);
            llistaProva.afegirActivitat(onlineKO);

            // 4. EXECUTEM LA FUNCIÓ
            System.out.println("\n-------------------------------------------------------");
            System.out.println("RESULTAT ESPERAT: Han de sortir 3 activitats (Java, Ioga, Spring)");
            System.out.println("-------------------------------------------------------");
            
            this.mostrarActivitatsAvui(llistaProva, dataSimuladaAvui);

        } catch (Exception e) {
            System.out.println("Error creant les dades de prova: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("*******************************************************\n");
    }
}
