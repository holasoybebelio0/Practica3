package aplicacio.proves;
import dades.inscripcions.LlistaInscripcions;
import dades.inscripcions.inscripcions;
import java.time.LocalDate;

public class ValidacioInscripcions {
    public static void main(String[] args) {
        System.out.println("VALIDACIÓ INSCRIPCIONS\n");

        System.out.println("1. Creació d'objectes 'inscripcions'");
        // Hem canviat els noms a equivalents catalans
        inscripcions p1 = new inscripcions("Joan Puig", "PDI", LocalDate.parse("2025-12-01"));
        inscripcions p2 = new inscripcions("Maria Serra", "Estudiant", LocalDate.parse("2025-12-02"));
        inscripcions p3 = new inscripcions("Carles Roig", "PTGAS", LocalDate.parse("2025-12-03"));

        // ToString
        System.out.println("Participant 1: " + p1.toString());
        
        // VALORACIÓ EXPERIÈNCIA
        System.out.println("\nProvant valorarExperiencia:");
        p1.valorarExperiencia(15); // Hauria de donar error
        p1.valorarExperiencia(9);  // Hauria de funcionar

        // PROVEM LLISTAINSCRIPCIONS
        System.out.println("\n2. Creació de la Llista (Capacitat: 2)");
        // Creem una llista petita per provar què passa quan s'omple
        LlistaInscripcions miLista = new LlistaInscripcions(2);

        // AFEGIM INSCRIPCIONS
        System.out.println("\n3. Afegint inscripcions");
        
        System.out.println("Afegint en Joan...");
        miLista.afegirInscripcio(p1);
        
        System.out.println("Afegint la Maria...");
        miLista.afegirInscripcio(p2);
        
        System.out.println("Intentant afegir en Carles (La llista està plena)...");
        miLista.afegirInscripcio(p3); // Aquí hauria de saltar el missatge de "capacitat plena"

        // GETTERS
        System.out.println("\n4. Estat actual de la llista");
        System.out.println("Nombre d'inscrits: " + miLista.getNumInscripcions());
        System.out.println(miLista.toString());

        // PROVA D'ÍNDEX
        System.out.println("Obtenint índex 0: " + miLista.getInscripcio(0).getNomParticipant());
        System.out.println("Obtenint índex 5 (invàlid): ");
        miLista.getInscripcio(5);

        // CÒPIA
        System.out.println("\n5. Provant mètode copia()");
        LlistaInscripcions listaCopia = miLista.copia();
        System.out.println("CÒPIA de la llista:");
        System.out.println(listaCopia.toString());

        // LLISTA D'ESPERA
        System.out.println("\n6. Provant creació Llista d'Espera");
        // Encara no està acabada la funció de llista d'espera
        

        
        
    }
}