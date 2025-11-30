package dades;

public class ActivitatUnDia extends Activitat{
    private String data;
    private String ciutat; 
    private double preu; 
    private int places; 
    private String horari; 

    public ActivitatUnDia (String nom, String[] colectius, String dataIniciInscripcio, String dataFinalInscripcio, String data, String ciutat, double preu, int places, String horari) {
        super(nom, colectius, dataIniciInscripcio, dataFinalInscripcio);
        this.data = data;
        this.horari = horari; 
        this.places = places;
        this.preu = preu; 
        this.ciutat = ciutat;
    }


    //Getters i setters
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getCiutat() {
        return ciutat;
    }
    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }
    public double getPreu() {
        return preu;
    }
    public void setPreu(double preu) {
        this.preu = preu;
    }
    public int getPlaces() {
        return places;
    }
    public void setPlaces(int places) {
        this.places = places;
    }
    public String getHorari() {
        return horari;
    }
    public void setHorari(String horari) {
        this.horari = horari;
    }
    
    //Copia
    @Override
    public Activitat copia() {
        return new ActivitatUnDia(
            this.nom,
            this.colectius,           
            this.dataIniciInscripcio,
            this.dataFinalInscripcio,
            this.data, 
            this.ciutat, 
            this.preu,  
            this.places,  
            this.horari
        );
    }

    @Override
    public String toString() {
        return "ActivitatUnDia [data=" + data + ", ciutat=" + ciutat + ", preu=" + preu + ", places=" + places
                + ", horari=" + horari + ", toString=" + toString() + "]";
    }

    @Override
    public boolean hihaPlaces() {
        return places > 0;
    }
    
}
    