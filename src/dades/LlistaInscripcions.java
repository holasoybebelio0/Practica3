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

    public void Indicardatavui() {
        
        
        
        
    }
    

    public void setInscripcioPosicio(int posicio, inscripcions novaInscripcio){
        if (posicio >= 0 && posicio < LlistaIncripcions.length) {
            if(LlistaIncripcions[posicio] == null){
                numInscripcions++;
            }
            LlistaIncripcions[posicio] = novaInscripcio.copia();
        } 
        else {
            System.out.println("Index invàlid");
        }
    }

    /**
     * Elimina una inscripció per nom.
     * @param nom
     * @return true si s'ha eliminat l'inscripció, false si no s'ha trobat.
     */
    public boolean eliminarInscripcio(String nom) {
        int pos = -1;
        int i = 0;

        while (i < numInscripcions && pos == -1) {
            if (LlistaIncripcions[i].getNomParticipant().equalsIgnoreCase(nom)) {
                pos = i;
            }
            i++;
        }

        if (pos == -1) {
            return false;
        }

        for (i = pos; i < numInscripcions - 1; i++) {
            LlistaIncripcions[i] = LlistaIncripcions[i + 1];
        }
        
        LlistaIncripcions[numInscripcions - 1] = null;
        numInscripcions--;
        
        return true;
    }


    /**
     * Treu i retorna la primera inscripció de la llista (funcio per la llista d'espera).
     * @return La primera inscripció o null si la llista està buida.
     */
    public inscripcions treurePrimer() {

        if (numInscripcions == 0) {
            return null;
        }
        else{
            inscripcions primer = LlistaIncripcions[0];
            
            for (int i = 0; i < numInscripcions - 1; i++) {
                LlistaIncripcions[i] = LlistaIncripcions[i + 1];
            }
            
            LlistaIncripcions[numInscripcions - 1] = null;
            numInscripcions--;
            
            return primer;
        }
        
    }

}
