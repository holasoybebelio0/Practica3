package dades;
import dades.usuaris.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class LlistaActivitats  {
    private Activitat[] llista; 
    private int nElems; 

    public LlistaActivitats (int capacitat) {
        this.llista = new Activitat[capacitat];
        this.nElems = 0;
    }

    public void afegirActivitat (Activitat act) {
        if (nElems<llista.length) {
            llista[nElems] = act; 
            nElems++;
        } else {
            System.out.println("Error: La llista és plena i no permet més activitats.");
        }
    }

    public Activitat getActivitat (int index) {
        if (index >= 0 && index < nElems) {
            return llista[index];
        }
        return null; 
    }

    public Activitat getActivitatPerNom(String nom) {
        for (int i = 0; i<nElems; i++) {
            if (llista[i].getNom().equalsIgnoreCase(nom)){
                return llista[i];
            }
        }
        return null; 
    }

    public int getnElems() {
        return nElems; 
    }

    public void mostrarLlista() {
        for (int i = 0; i<nElems; i++) {
            System.out.println(llista[i].toString());
        }
    }
    /**
     * Tasca 1: Modificar Data Actual del sistema.
     * @param dataActual
     * @return
     */
    public LocalDate moddataActual(LocalDate dataActual) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("La data actual del sistem es: "+ dataActual);
        System.out.println("Introdueix la data actual: ");
        
       
       if (scanner.hasNextLine()) {
        String entrada = scanner.nextLine();
        try {
            dataActual = LocalDate.parse(entrada);
            System.out.println("Data actualitzada correctament a: " + dataActual);
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: El format de la data no és vàlid. Ha de ser AAAA-MM-DD (ex: 2025-10-15).");
        }
       

       }
        return dataActual;

    }
   /**
     * Tasca 3: Mostra les activitats en període d'inscripció i amb places.
     * @param dataSimulacio La data actual del sistema (del menú).
     */
    public void mostrarActivitatsDisponibles(LocalDate dataSimulacio) {
        System.out.println("--- ACTIVITATS EN PERÍODE D'INSCRIPCIÓ I AMB PLACES (" + dataSimulacio + ") ---");
        
        boolean algunaTrobada = false;

        for (int i = 0; i < nElems; i++) {
            Activitat act = llista[i];
            if (act.hihaPlaces()) {
                try {

                    // 2. Control de dates (Període d'inscripció) 


                    LocalDate iniciInscripcio = act.getDataIniciInscripcio();
                    LocalDate fiInscripcio = act.getDataFinalInscripcio();

                    // Comprovem si dataSimulacio està dins el rang [inici, final]
                    boolean dinsPeriode = !dataSimulacio.isBefore(iniciInscripcio) && !dataSimulacio.isAfter(fiInscripcio);

                    if (dinsPeriode) {
                        System.out.println(act.toString());
                        algunaTrobada = true;
                    }

                } catch (DateTimeParseException e) {
                    
                }
            }
        }

        if (!algunaTrobada) {
            System.out.println("No s'han trobat activitats disponibles en aquesta data.");
        }
    }


    //TASCA 6 mostrar activitats amb placçes disponibles
    public void mostrarActivitatsAmbPlaces() {
        System.out.println("--- ACTIVITATS AMB PLACES DISPONIBLES ---");
        boolean algunaTrobada = false;

        for (int i = 0; i < nElems; i++) {
            Activitat act = llista[i];
            if (act.hihaPlaces()) {
                System.out.println(act.toString());
                algunaTrobada = true;
            }
        }

        if (!algunaTrobada) {
            System.out.println("No hi ha activitats amb places disponibles.");
        }
    }
    
    //TASCA 10 Inscriures a una activitat
     public boolean inscriureUsuariActivitat(Usuari usuari, String nomActivitat, LocalDate dataActual) {
        // 1. Buscar la actividad
        Activitat activitat = getActivitatPerNom(nomActivitat);
        if (activitat == null) {
            System.out.println("ERROR: L'activitat '" + nomActivitat + "' no existeix.");
            return false;
        }
        
        // 2. Verificar período de inscripción
        if (dataActual.isBefore(activitat.getDataIniciInscripcio()) || 
            dataActual.isAfter(activitat.getDataFinalInscripcio())) {
            System.out.println("ERROR: No estàs dins del període d'inscripció.");
            return false;
        }
        
        // 3. Determinar tipo de usuario
        String tipusUsuari = "";
        if (usuari instanceof Estudiant) {
            tipusUsuari = "Estudiant";
        } else if (usuari instanceof PDI) {
            tipusUsuari = "PDI";
        } else if (usuari instanceof PTGAS) {
            tipusUsuari = "PTGAS";
        }
        
        // 4. Verificar si ya está inscrito
        if (activitat.estaInscrit(usuari.getAlies())) {
            System.out.println("ERROR: L'usuari ja està inscrit en aquesta activitat.");
            return false;
        }
        
        // 5. Intentar inscribir
        boolean inscrit = activitat.afegirInscripcio(usuari.getAlies(), dataActual, tipusUsuari);
        
        if (inscrit) {
            System.out.println("SUCCESS: " + usuari.getAlies() + " inscrit correctament a " + nomActivitat);
            return true;
        } else {
            System.out.println("ERROR: No s'ha pogut inscriure a " + nomActivitat + 
                             ". Places plenes i llista d'espera completa.");
            return false;
        }
    }


    //tasca 13 Afegir una nova activitat d'un dia
    public void afegirActivitatUnDia(ActivitatUnDia act) {
        if (nElems < llista.length) {
            llista[nElems] = act;
            nElems++;
            System.out.println("Activitat d'un dia afegida correctament: " + act.getNom());
        } else {
            System.out.println("Error: La llista és plena i no es pot afegir més activitats.");
        }
    }

 /**
     * TASCA 16: Valorar una activitat per part d'un assistent.
     * Requisits: L'activitat ha d'haver acabat i l'usuari hi ha d'haver assistit.
     */
    public void valorarActivitat(String nomActivitat, String aliesUsuari, int puntuacio, LocalDate dataActual) {
        Activitat act = getActivitatPerNom(nomActivitat);

        if (act == null) {
            System.out.println("Error: L'activitat " + nomActivitat + " no existeix.");
            return;
        }

        // 1. Validar puntuació (0-10) 
        if (puntuacio < 0 || puntuacio > 10) {
            System.out.println("Error: La puntuació ha de ser entre 0 i 10.");
            return;
        }

        // 2. Comprovar si l'activitat ha acabat (Necessites implementar haAcabat a Activitat)
        // Si no has implementat haAcabat a Activitat, et donarà error aquí.
        if (!act.haacabat(dataActual)) {
            System.out.println("Error: No es pot valorar l'activitat perquè encara no ha acabat.");
            return;
        }

        // 3. Comprovar si l'usuari va assistir (està a la llista d'inscripcions)
        LlistaInscripcions llistaInscripcions = act.getLlistaInscripcions();
        inscripcions inscripcioUsuari = llistaInscripcions.getInscripcioPerNom(aliesUsuari);

        if (inscripcioUsuari != null) {
            // Guardem la valoració a l'objecte inscripció
            inscripcioUsuari.valorarExperiencia(puntuacio);
            System.out.println("Valoració registrada correctament: Usuari " + aliesUsuari + " -> " + puntuacio + " punts.");
        } else {
            System.out.println("Error: L'usuari " + aliesUsuari + " no consta com a inscrit en aquesta activitat.");
        }
    }
}
