package aplicacio;
import dades.*;


public class ValidacioInscripcions {
    public static void main(String[] args) throws Exception {
        //INSCRIPCIO BASE (EVELIO)
        inscripcions inscripcio1 = new inscripcions(10, 20);
        System.out.println("Validacio Inscripcions");
        System.out.println("Numero d'inscripcions: " + inscripcio1.getNumInscripcions());
        System.out.println("Numero de places: " + inscripcio1.getNumPlaces());
       
        //Probar Setters
        System.out.println("Actualitzant numero d'inscripcions a 15 i places a 25");
        inscripcio1.setNumInscripcions(15);
        inscripcio1.setNumPlaces(25);
        System.out.println("Numero d'inscripcions actualitzat: " + inscripcio1.getNumInscripcions());
        System.out.println("Numero de places actualitzat: " + inscripcio1.getNumPlaces());

        //Probar toString
        System.out.println("Detalls de la inscripcio: " + inscripcio1.toString());

        //Probar llistainscripcions
        LlistaInscripcions llista = new LlistaInscripcions(10);
        llista.afegirInscripcio(inscripcio1);   


    }
}
