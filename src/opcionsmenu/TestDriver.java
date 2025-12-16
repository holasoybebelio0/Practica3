package opcionsmenu;
import dades.*;
import java.time.LocalDate;

public class TestDriver {

    public static void main(String[] args) {
        // 1. Crear la lista (La "base de datos" en memoria)
        LlistaActivitats laMevaLlista = new LlistaActivitats(10);

        // 2. Crear datos de prueba (Colectivos dummy)
        String[] colectius = {"Joves", "Adults"};

        // ACTIVIDAD A: Periodo 2024 (Enero a Marzo). Tiene 20 plazas.
        ActivitatPeriodica futbol = new ActivitatPeriodica(
            "Futbol Sala", colectius, 
            "2024-01-01", "2024-03-31", // Inscripción
            "Dilluns", "18:00", "2024-04-01", 10, 
            20, 50.0, "Poliesportiu", "Barcelona"
        );

        // ACTIVIDAD B: Periodo 2024 (Enero a Diciembre). Tiene 0 plazas (LLENA).
        ActivitatPeriodica basket = new ActivitatPeriodica(
            "Basket Pro", colectius, 
            "2024-01-01", "2025-12-31", // Inscripción
            "Dimarts", "19:00", "2024-04-01", 10, 
            5, 40.0, "Pavelló", "Girona"
        );
        basket.setnInscrits(4); // Forzamos que esté llena (5 de 5 plazas ocupadas)

        // ACTIVIDAD C: Online (Siempre tiene plazas). Periodo todo 2025.
        ActivitatOnline javaCurs = new ActivitatOnline(
            "Curs Java", colectius, 
            "2025-01-01", "2025-12-31", // Inscripción
            "http://zoom.us/java"
        );

        // Añadimos a la lista
        laMevaLlista.afegirActivitat(futbol);
        laMevaLlista.afegirActivitat(basket);
        laMevaLlista.afegirActivitat(javaCurs);


        // --- INICIO DE LA PRUEBA ---
        
        // Inicializamos fecha simulada (Por ejemplo, hoy)
        LocalDate dataSistema = LocalDate.now();

        System.out.println("========================================");
        System.out.println("   PROVA DE TASCA 1 i TASCA 3");
        System.out.println("========================================");

        // EJECUTAR TASCA 1: Modificar la fecha
        // Guardamos el resultado en dataSistema para usarlo después
        dataSistema = laMevaLlista.moddataActual(dataSistema);
        
        System.out.println("\n----------------------------------------");
        System.out.println("Data actualitzada del sistema: " + dataSistema);
        System.out.println("----------------------------------------");

        // EJECUTAR TASCA 3: Ver qué actividades tocan hoy
        laMevaLlista.mostrarActivitatsDisponibles(dataSistema);
    }
}