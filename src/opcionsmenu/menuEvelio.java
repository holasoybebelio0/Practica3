package opcionsmenu;

import dades.*;// Importem les classes (LlistaUsuaris, PDI, PTGAS, etc.)
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
        // ==========================================
        // 2. GESTIÓ D'ACTIVITATS
        // ==========================================
        
        // Crear la llista d'activitats
        LlistaActivitats laMevaLlista = new LlistaActivitats(10);

        // Definim els col·lectius
        String[] colPDI = {"PDI"};
        String[] colTots = {"PDI", "PTGAS"};

        // ACTIVITAT A: Futbol
        // CORRECCIÓ: Fem servir LocalDate.parse() per convertir el text a data real
        ActivitatPeriodica futbol = new ActivitatPeriodica(
            "Futbol Sala", colTots, 
            LocalDate.parse("2024-01-01"), LocalDate.parse("2024-03-31"), // Dates inscripció
            "Dilluns", "18:00", LocalDate.parse("2024-04-01"), 10,        // Data inici activitat
            20, 50.0, "Poliesportiu", "Barcelona"
        );

        // ACTIVITAT B: Basket
        ActivitatPeriodica basket = new ActivitatPeriodica(
            "Basket Pro", colPDI, 
            LocalDate.parse("2024-01-01"), LocalDate.parse("2025-12-31"), 
            "Dimarts", "19:00", LocalDate.parse("2026-01-09"), 10, 
            5, 40.0, "Pavelló", "Girona"
        );
        basket.setnInscrits(2); // Forcem que estigui PLENA (si places fos 2)

        // ACTIVITAT C: Curs Java
        // CORRECCIÓ: Afegim els dos paràmetres que faltaven al final (Data inici i durada)
        ActivitatOnline javaCurs = new ActivitatOnline(
            "Curs Java", colTots, 
            LocalDate.parse("2025-01-01"), LocalDate.parse("2025-12-31"), 
            "http://zoom.us/java",
            LocalDate.parse("2025-02-01"), // NOVA: Data inici activitat
            30                             // NOVA: Durada en dies
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