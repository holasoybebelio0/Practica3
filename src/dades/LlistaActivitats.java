package dades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public void mostrarLlista(int filtre) { // IMPLEMENTAR EN EL VALIDACIOACTIVITAT las instrucciones de lo que vale cada filtro
        if (filtre == 0) {
            for (int i=0; i<nElems; i++) {
                System.out.println(llista[i].toString());
            }
        } else if (filtre != 0) {
            for (int i=0; i<nElems; i++) {
                if (llista[i].getTipus() == filtre) {
                    System.out.println(llista[i].toString());
                } else {
                    System.out.println("\nError: El valor del filtre només pot ser 0, 1 o 2.");

                }
            }
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
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        boolean algunaTrobada = false;

        for (int i = 0; i < nElems; i++) {
            Activitat act = llista[i];
            if (act.hihaPlaces()) {
                try {
                    // 2. Control de dates (Període d'inscripció) 
                    LocalDate iniciInscripcio = LocalDate.parse(act.getDataIniciInscripcio(), formatter);
                    LocalDate fiInscripcio = LocalDate.parse(act.getDataFinalInscripcio(), formatter);

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
    
}


