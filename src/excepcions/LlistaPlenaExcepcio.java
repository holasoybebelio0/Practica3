package excepcions;

/**
 * Excepció específica per quan s'intenta afegir un element a una llista
 * (array estàtic) que ja ha arribat a la seva capacitat màxima.
 */
public class LlistaPlenaExcepcio extends BenestarExcepcio {
    
    public LlistaPlenaExcepcio(String nomLlista) {
        super("Error: La llista de " + nomLlista + " està plena. No es poden afegir més elements.");
    }
}