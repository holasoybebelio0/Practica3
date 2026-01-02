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
    

// 2. AÑADE este nuevo método para que funcione la llamada desde Activitat
// NOTA: Asegúrate de que el orden (nom, tipus, data) coincide con el constructor de tu clase 'inscripcions'
public boolean afegirInscripcio(String nomParticipant, String tipusUsuari, java.time.LocalDate dataActual) {
    if (numInscripcions < LlistaIncripcions.length) {
        // Aquí creamos el objeto inscripcions dentro de la lista
        inscripcions nova = new inscripcions(nomParticipant, tipusUsuari, dataActual);
        LlistaIncripcions[numInscripcions] = nova;
        numInscripcions++;
        return true;
    } else {
        return false;
    }
}
    public LlistaInscripcions copia() {
        LlistaInscripcions novaLlista = new LlistaInscripcions(LlistaIncripcions.length);
        for (int i = 0; i < numInscripcions; i++) {
            novaLlista.LlistaIncripcions[i] = LlistaIncripcions[i].copia();
        }
        novaLlista.numInscripcions = numInscripcions;
        return novaLlista;
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
   
    public inscripcions getInscripcioPerNom(String nomParticipant) {
    for (int i = 0; i < numInscripcions; i++) {
        if (LlistaIncripcions[i].getNomParticipant().equalsIgnoreCase(nomParticipant)) {
            return LlistaIncripcions[i];
        }
    }
    return null;
}

    public boolean estaInscrit(String nomParticipant) {
        for (int i = 0; i < numInscripcions; i++) {
            if (LlistaIncripcions[i].getNomParticipant().equalsIgnoreCase(nomParticipant)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hihaPlaces() {
        return numInscripcions < LlistaIncripcions.length;
    }

    public int getNumEspera() {
        return 0;
    }

    public int getPlacesMaximes() {
        return LlistaIncripcions.length;
    }
    // TASCA 19: Calcular mitjana per a un col·lectiu específic
    public double calcularMitjanaPerCol(String tipusObjectiu) {
        double suma = 0;
        int comptador = 0;

        for (int i = 0; i < numInscripcions; i++) {
            inscripcions ins = LlistaIncripcions[i];
            
            // Comprovem si és del tipus que busquem (ignorant majúscules/minúscules)
            // I comprovem que l'usuari hagi valorat (valoracio != -1)
            if (ins.getTipusUsuari().equalsIgnoreCase(tipusObjectiu) && ins.getValoracio() != -1) {
                suma += ins.getValoracio();
                comptador++;
            }
        }

        if (comptador > 0) {
            return suma / comptador;
        } else {
            return 0.0; // Retornem 0 si ningú d'aquest grup ha valorat
        }
    }
}
