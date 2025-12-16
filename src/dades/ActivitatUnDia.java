package dades;

import java.time.LocalDate;


public class ActivitatUnDia extends Activitat{
    private LocalDate data;
    private String ciutat; 
    private double preu; 
    private int places; 
    private String horari; 
    private int nInscrits; 

    public ActivitatUnDia (String nom, String[] colectius, LocalDate dataIniciInscripcio, LocalDate dataFinalInscripcio, LocalDate data, String ciutat, double preu, int places, String horari) {
        super(nom, colectius, dataIniciInscripcio, dataFinalInscripcio, places);
        this.data = data;
        this.horari = horari; 
        this.places = places;
        this.preu = preu; 
        this.ciutat = ciutat;
        this.nInscrits = 0; 
    }


    //Getters i setters
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
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
    public int getnInscrits() {
        return nInscrits;
    }
    public void incnInscrits() {
        if (hihaPlaces()) {
            nInscrits++;
        }
    }
    public void setninscrits (int numInscrits){
        nInscrits = numInscrits;
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
                + ", Inscrits= "+ nInscrits+", horari=" + horari + ", toString=" + toString() + "]";
    }

    @Override
    public boolean hihaPlaces() {
        return nInscrits<places;
    }
    
    @Override
    public boolean esActiva(LocalDate dataObjectiu) {
        try {
            return data.equals(dataObjectiu);
        } catch (Exception e) {
            return false;
        }
    }
    
}
    