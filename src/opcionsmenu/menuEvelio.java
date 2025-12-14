package opcionsmenu;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class menuEvelio {
    
    private LocalDate dataSimulacio; 
    private Scanner scanner;

    public menuEvelio() {
        this.scanner = new Scanner(System.in);
     
        this.dataSimulacio = LocalDate.of(2025, 9, 1); 
    }

    /**
     * Tasca 1: Indicar la data del dia d’avui (Simulada).
     */
    public void modificarDataActual() {
        System.out.println("--- MODIFICAR DATA DE SIMULACIÓ ---");
        System.out.println("Data actual del sistema: " + this.dataSimulacio);
        System.out.print("Introdueix la nova data (format AAAA-MM-DD): ");
        String entrada = scanner.nextLine();

        try {
 
            LocalDate novaData = LocalDate.parse(entrada);
            
  
            this.dataSimulacio = novaData;
            
            System.out.println("Data actualitzada correctament. Ara és: " + this.dataSimulacio);
            
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: El format de la data no és vàlid. Ha de ser AAAA-MM-DD (ex: 2025-10-15).");
        }
    }


    public LocalDate getDataActual() {
        return this.dataSimulacio;
    }

    //Mostrar activitats en periode d'inscripcio
    public void mostrarActivitatsInscripcio() {
        System.out.println("--- ACTIVITATS EN PERÍODE D'INSCRIPCIÓ ---");
        System.out.println("Funcionalitat no implementada encara.");
    }
}