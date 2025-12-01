package dades;


public class inscripcions { 
    private int numInscripcions;
    private int numPlaces;

    /**CONSTRUCTOR */
    public inscripcions (int numInscripcions, int numPlaces){
        this.numInscripcions = numInscripcions;
        this.numPlaces = numPlaces;
    }

    //GETTERS I SETTERS
    public int getNumInscripcions() {
        return numInscripcions;
    }   

    public int getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    public void setNumInscripcions(int insc){
        this.numInscripcions = insc;

    }
    
    
}
