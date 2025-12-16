package dades;



public abstract class Activitat {
    protected String nom; 
    protected String[] colectius;
    protected String dataIniciInscripcio;
    protected String dataFinalInscripcio;

    public Activitat (String nom, String[] colectius, String dataIniciInscripcio, String dataFinalInscripcio) {
        this.nom = nom; 
        this.dataFinalInscripcio = dataFinalInscripcio;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.colectius = colectius;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String[] getColectius() {
        return colectius;
    }

    public void setColectius(String[] colectius) {
        this.colectius = colectius;
    }

    public String getDataIniciInscripcio() {
        return dataIniciInscripcio;
    }

    public void setDataIniciInscripcio(String dataIniciInscripcio) {
        this.dataIniciInscripcio = dataIniciInscripcio;
    }

    public String getDataFinalInscripcio() {
        return dataFinalInscripcio;
    }

    public void setDataFinalInscripcio(String dataFinalInscripcio) {
        this.dataFinalInscripcio = dataFinalInscripcio;
    }

   
    

    @Override
    public String toString() {
        return "Activitat [nom=" + nom + ", colectius=" + colectius.toString() + ", dataIniciInscripcio="
                + dataIniciInscripcio + ", dataFinalInscripcio=" + dataFinalInscripcio + "]";
    }

    public abstract Activitat copia();

    public abstract boolean hihaPlaces();

    public abstract double getPreu();
}
