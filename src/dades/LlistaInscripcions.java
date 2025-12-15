package dades;

public class LlistaInscripcions {
    private inscripcions[] LlistaIncripcions;
    private int numInscripcions;
    //CONSTUCTOR
    public LlistaInscripcions(int capacitat){
        LlistaIncripcions = new inscripcions[capacitat];
        numInscripcions = 0;
    }

    //GETTERS I SETTERS
    public inscripcions getInscripcio(int index) {
        if (index >= 0 && index < numInscripcions) {
            return LlistaIncripcions[index];
        } else {
            System.out.println("Índex fora de rang.");
            return null;
        }
    }   
    public int getNumInscripcions() {
        return numInscripcions;
    }
    public void afegirInscripcio(inscripcions inscripcio) {
        if (numInscripcions < LlistaIncripcions.length) {
            LlistaIncripcions[numInscripcions] = inscripcio;
            numInscripcions++;
        } else {
            System.out.println("No es poden afegir més inscripcions, capacitat plena.");
        }
    }
    public LlistaInscripcions copia() {
        LlistaInscripcions llistaCopiada = new LlistaInscripcions(LlistaIncripcions.length);
        for (int i = 0; i < numInscripcions; i++) {
            llistaCopiada.afegirInscripcio(LlistaIncripcions[i]);
        }
        return llistaCopiada;
    }
    @Override
    public String toString() {
        String resultat = "Llista de "+numInscripcions+" inscripcions:\n";
        for (int i = 0; i < numInscripcions; i++) {
            resultat += LlistaIncripcions[i].toString() + "\n";
        }
        return resultat;
    }
    public LlistaInscripcions LlistaEspera() {
        return new LlistaInscripcions(10);
    }   

    public void setInscripcioPosicio(int index){
        if (index >= 0 && index < numInscripcions) {
            
        } 
        else {
    }

}
