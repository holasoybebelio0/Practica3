package dades;

public class ActivitatOnline extends Activitat {
    private String enllac;

    public ActivitatOnline(String nom, String[] colectius, String dataIniciInscripcio, String dataFinalInscripcio, String enllac) {
        super(nom, colectius, dataIniciInscripcio, dataFinalInscripcio, 1);
        this.enllac = enllac;
    }

    public String getEnllaç() {
        return enllac;
    }

    public void setUrl(String enllac) {
        this.enllac = enllac;
    }

    @Override
    public String toString() {
        return super.toString() + ", enllaç=" + enllac + "]";
    }

    @Override
    public Activitat copia() {
        return new ActivitatOnline(this.nom, this.colectius.clone(), this.dataIniciInscripcio, this.dataFinalInscripcio, this.enllac);
    }
    
    @Override
    public boolean hihaPlaces() {
        return true; // Les activitats online sempre tenen places disponibles
    }
    @Override
    public double getPreu() {   
        return 0.0; // Les activitats online són gratuïtes
    }
    
}
