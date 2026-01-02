package dades;

public class LlistaInscripcions {
    private inscripcions[] LlistaInscripcions;
    private int numInscripcions;
    //CONSTUCTOR
    public LlistaInscripcions(int capacitat){
        LlistaInscripcions = new inscripcions[capacitat];
        numInscripcions = 0;
    }

    //GETTERS I SETTERS
    public inscripcions getInscripcio(int index) {
        if (index >= 0 && index < numInscripcions) {
            return LlistaInscripcions[index];
        } else {
            System.out.println("Índex fora de rang.");
            return null;
        }
    }   
    public int getNumInscripcions() {
        return numInscripcions;
    }
    public void afegirInscripcio(inscripcions inscripcio) {
        if (numInscripcions < LlistaInscripcions.length) {
            LlistaInscripcions[numInscripcions] = inscripcio;
            numInscripcions++;
        } else {
            System.out.println("No es poden afegir més inscripcions, capacitat plena.");
        }
    }
    public LlistaInscripcions copia() {
        LlistaInscripcions llistaCopiada = new LlistaInscripcions(LlistaInscripcions.length);
        for (int i = 0; i < numInscripcions; i++) {
            llistaCopiada.afegirInscripcio(LlistaInscripcions[i]);
        }
        return llistaCopiada;
    }
    @Override
    public String toString() {
        String resultat = "Llista de "+numInscripcions+" inscripcions:\n";
        for (int i = 0; i < numInscripcions; i++) {
            resultat += LlistaInscripcions[i].toString() + "\n";
        }
        return resultat;
    }
    public LlistaInscripcions LlistaEspera() {
        return new LlistaInscripcions(10);
    }   
   
    public void Indicardatavui() {
        
        
        
        
    }
    

    public void setInscripcioPosicio(int posicio, inscripcions novaInscripcio){
        if (posicio >= 0 && posicio < LlistaInscripcions.length) {
            if(LlistaInscripcions[posicio] == null){
                numInscripcions++;
            }
            LlistaInscripcions[posicio] = novaInscripcio.copia();
        } 
        else {
            System.out.println("Index invàlid");
        }
    }

    public boolean conteUsuari (String nom) {
        boolean conte = false;
        for (int i=0; i<numInscripcions; i++) {
            if (LlistaInscripcions[i].getNomParticipant().equalsIgnoreCase(nom)) {
                conte = true; 
            }
        }
        return conte;
    }

    public double mitjanaValoracions() {
        double suma = 0;
        int nvaloracions = 0;
        double resultat;

        for (int i=0; i<numInscripcions; i++) {
            int valoracio = LlistaInscripcions[i].getValoracio();
            if (valoracio != -1) {
                suma += LlistaInscripcions[i].getValoracio();
                nvaloracions++;
            }
        }
        if (nvaloracions == 0) {
            resultat = 0.0;
        } else {
            resultat = (suma/nvaloracions);
        }
        
        return resultat;
    }
}
