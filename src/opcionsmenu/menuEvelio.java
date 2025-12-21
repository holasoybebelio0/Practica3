package opcionsmenu;

import dades.*; // Importem les classes (LlistaUsuaris, PDI, PTGAS, etc.)
import dades.usuaris.*;
import java.time.LocalDate;

public class menuEvelio {

    public static void main(String[] args) {
        
        // ==========================================
        // 1. GESTIÓ D'USUARIS (NOU: Afegim usuaris reals)
        // ==========================================
        System.out.println("--- INICIALITZANT USUARIS ---");
        
        // Creem la llista d'usuaris
        LlistaUsuaris llistaUsuaris = new LlistaUsuaris(10);

        // Creem usuaris reals (PDI i PTGAS) en lloc de dades buides
        PDI usuari1 = new PDI("JGarcia", "joan.garcia", "Informàtica", "Sescelades");
        PTGAS usuari2 = new PTGAS("MRovira", "maria.rovira", "Catalunya");

        // Els afegim a la llista
        llistaUsuaris.afegirUsuari(usuari1);
        llistaUsuaris.afegirUsuari(usuari2);

        // Opcional: Mostrem els usuaris que hem creat
        System.out.println(llistaUsuaris.toString());


        // ==========================================
        // 2. GESTIÓ D'ACTIVITATS
        // ==========================================
        
        // Crear la llista d'activitats
        LlistaActivitats laMevaLlista = new LlistaActivitats(10);

        // Definim els col·lectius basant-nos en els usuaris que tenim (PDI, PTGAS, etc.)
        String[] colPDI = {"PDI"};
        String[] colTots = {"PDI", "PTGAS"};

        // ACTIVITAT A: Futbol (Per a PDI i PTGAS). Periodo 2024.
        ActivitatPeriodica futbol = new ActivitatPeriodica(
            "Futbol Sala", colTots, 
            "2024-01-01", "2024-03-31", // Inscripció tancada
            "Dilluns", "18:00", "2024-04-01", 10, 
            20, 50.0, "Poliesportiu", "Barcelona"
        );

        // ACTIVITAT B: Basket (Només PDI). Periodo 2024-2026.
        ActivitatPeriodica basket = new ActivitatPeriodica(
            "Basket Pro", colPDI, 
            "2024-01-01", "2025-12-31", // Inscripció oberta
            "Dimarts", "19:00", "2026-01-09", 10, 
            5, 40.0, "Pavelló", "Girona"
        );
        basket.setnInscrits(2); // Forcem que estigui PLENA (0 places lliures)

        // ACTIVITAT C: Curs Java (Online, sempre hi ha lloc). Per a tothom.
        ActivitatOnline javaCurs = new ActivitatOnline(
            "Curs Java", colTots, 
            "2025-01-01", "2025-12-31", // Inscripció oberta
            "http://zoom.us/java"
        );

        // Afegim les activitats a la llista
        laMevaLlista.afegirActivitat(futbol);
        laMevaLlista.afegirActivitat(basket);
        laMevaLlista.afegirActivitat(javaCurs);


        // ==========================================
        // 3. PROVA DE TASQUES (Data i Disponibilitat)
        // ==========================================
        
        LocalDate dataSistema = LocalDate.now();

        System.out.println("\n========================================");
        System.out.println("   PROVA DE TASCA 1 i TASCA 3");
        System.out.println("========================================");

        // EJECUTAR TASCA 1: Modificar la data
        // NOTA: Assegura't que el mètode es diu 'MostractDisp' o 'moddataActual' a la teva classe LlistaActivitats.
        // He fet servir 'MostractDisp' perquè és el que vam arreglar abans.
        dataSistema = laMevaLlista.moddataActual(dataSistema);
        
        System.out.println("\n----------------------------------------");
        System.out.println("Data actualitzada del sistema: " + dataSistema);
        System.out.println("----------------------------------------");

        // EJECUTAR TASCA 3: Veure quines activitats toquen avui
        laMevaLlista.mostrarActivitatsDisponibles(dataSistema);
    }
}