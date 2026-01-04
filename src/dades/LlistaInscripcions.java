package dades;

import java.io.*; // Necessari per a la serialització i fitxers

public class LlistaInscripcions implements Serializable {
    
    private static final long serialVersionUID = 1L; // Versión para serialización

    private inscripcions[] LlistaInscripcions;
    private int numInscripcions;

    // CONSTRUCTOR
    public LlistaInscripcions(int capacitat){
        LlistaInscripcions = new inscripcions[capacitat];
        numInscripcions = 0;
    }

    // GETTERS I SETTERS
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

    public boolean afegirInscripcio(inscripcions inscripcio) {
        boolean espotafegir = false;
        if (numInscripcions < LlistaInscripcions.length) {
            LlistaInscripcions[numInscripcions] = inscripcio;
            numInscripcions++;
            espotafegir = true;
        } else {
            System.out.println("No es poden afegir més inscripcions, capacitat plena.");
        }
        return espotafegir;
    }

    public LlistaInscripcions copia() {
        LlistaInscripcions novaLlista = new LlistaInscripcions(LlistaInscripcions.length);
        for (int i = 0; i < numInscripcions; i++) {
            novaLlista.LlistaInscripcions[i] = LlistaInscripcions[i].copia();
        }
        novaLlista.numInscripcions = numInscripcions;
        return novaLlista;
    }

    @Override
    public String toString() {
        String resultat = "Llista de "+numInscripcions+" inscripcions:\n";
        for (int i = 0; i < numInscripcions; i++) {
            resultat += LlistaInscripcions[i].toString() + "\n";
        }
        return resultat;
    }

    public void Indicardatavui() {
        // Mètode buit
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

    // CORREGIT: Un sol return
    public boolean conteUsuari (String nom) {
        boolean trobat = false;
        for (int i=0; i < numInscripcions && !trobat; i++) {
            if (LlistaInscripcions[i].getNomParticipant().equalsIgnoreCase(nom)) {
                trobat = true; 
            }
        }
        return trobat;
    }

    public double mitjanaValoracions() {
        double suma = 0;
        int nvaloracions = 0;
        double resultat;

        for (int i=0; i<numInscripcions; i++) {
            int valoracio = LlistaInscripcions[i].getValoracio();
            if (valoracio != -1) {
                suma += valoracio;
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
   
    public inscripcions getInscripcioPerNom(String nomParticipant) {
        for (int i = 0; i < numInscripcions; i++) {
            if (LlistaInscripcions[i].getNomParticipant().equalsIgnoreCase(nomParticipant)) {
                return LlistaInscripcions[i];
            }
        }
        return null;
    }

    public boolean estaInscrit(String nomParticipant) {
        for (int i = 0; i < numInscripcions; i++) {
            if (LlistaInscripcions[i].getNomParticipant().equalsIgnoreCase(nomParticipant)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean hihaPlaces() {
        return numInscripcions < LlistaInscripcions.length;
    }

    public int getNumEspera() {
        // Aquest mètode sembla retornar 0 segons el codi original, potser hauria de retornar
        // el nombre actual de la llista, però com que aquesta classe s'usa per les dues coses,
        // mantenim el codi original o retornem numInscripcions si fos necessari.
        return 0;
    }

    public int getPlacesMaximes() {
        return LlistaInscripcions.length;
    }

    // TASCA 19: Calcular mitjana per a un col·lectiu específic
    public double calcularMitjanaPerCol(String tipusObjectiu) {
        double suma = 0;
        int comptador = 0;

        for (int i = 0; i < numInscripcions; i++) {
            inscripcions ins = LlistaInscripcions[i];
            
            if (ins.getTipusUsuari().equalsIgnoreCase(tipusObjectiu) && ins.getValoracio() != -1) {
                suma += ins.getValoracio();
                comptador++;
            }
        }

        if (comptador > 0) {
            return suma / comptador;
        } else {
            return 0.0;
        }
    }

    public boolean eliminarInscripcio(String nom) {
        int pos = -1;
        int i = 0;

        while (i < numInscripcions && pos == -1) {
            if (LlistaInscripcions[i].getNomParticipant().equalsIgnoreCase(nom)) {
                pos = i;
            }
            i++;
        }

        if (pos == -1) {
            return false;
        }

        for (i = pos; i < numInscripcions - 1; i++) {
            LlistaInscripcions[i] = LlistaInscripcions[i + 1];
        }
        
        LlistaInscripcions[numInscripcions - 1] = null;
        numInscripcions--;
        
        return true;
    }

    public inscripcions treurePrimer() {
        if (numInscripcions == 0) {
            return null;
        }
        else {
            inscripcions primer = LlistaInscripcions[0];
            
            for (int i = 0; i < numInscripcions - 1; i++) {
                LlistaInscripcions[i] = LlistaInscripcions[i + 1];
            }
            
            LlistaInscripcions[numInscripcions - 1] = null;
            numInscripcions--;
            
            return primer;
        }
    }

   

    /**
     * Guarda l'estat actual de la llista en un fitxer binari.
     * @param nomFitxer Ruta del fitxer
     * @throws IOException Si hi ha error d'escriptura
     */
    public void guardarLlistaSerialitzada(String nomFitxer) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFitxer));
        oos.writeObject(this);
        oos.close();
        System.out.println("Llista d'inscripcions guardada correctament a " + nomFitxer);
    }

    /**
     * Carrega una llista des d'un fitxer binari.
     * @param nomFitxer Ruta del fitxer
     * @return LlistaInscripcions recuperada
     * @throws IOException Si hi ha error de lectura
     * @throws ClassNotFoundException Si la classe no coincideix
     */
    public static LlistaInscripcions carregarLlistaSerialitzada(String nomFitxer) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFitxer));
        LlistaInscripcions llista = (LlistaInscripcions) ois.readObject();
        ois.close();
        return llista;
    }
}