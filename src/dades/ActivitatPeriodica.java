package dades;

public class ActivitatPeriodica extends Activitat{
    private String diaSetmana; 
    private String horari; 
    private String dataInici; 
    private int nSetmanes;
    private int places;
    private double preu; 
    private String nomCentre;
    private String ciutat;

    private int nInscrits; // Número d'inscrits per a verificar si hi queden places.

    public ActivitatPeriodica (String nom, String[] colectius, String dataIniciInscripcio, String dataFinalInscripcio, String diaSetmana, String horari, String dataInici, int nSetmanes, int places, double preu, String nomCentre, String ciutat) {
        super(nom, colectius, dataIniciInscripcio, dataFinalInscripcio);
        this.diaSetmana = diaSetmana;
        this.horari = horari;
        this.dataInici = dataInici;
        this.nSetmanes = nSetmanes;
        this.places = places;
        this.preu = preu; 
        this.nomCentre = nomCentre;
        this.ciutat = ciutat;
        this.nInscrits = 0; 
    }

    //Getters i setters
    public String getDiaSetmana() {
        return diaSetmana;
    }

    public void setDiaSetmana(String diaSetmana) {
        this.diaSetmana = diaSetmana;
    }

    public String getHorari() {
        return horari;
    }

    public void setHorari(String horari) {
        this.horari = horari;
    }

    public String getDataInici() {
        return dataInici;
    }

    public void setDataInici(String dataInici) {
        this.dataInici = dataInici;
    }

    public int getnSetmanes() {
        return nSetmanes;
    }

    public void setnSetmanes(int nSetmanes) {
        this.nSetmanes = nSetmanes;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    public String getNomCentre() {
        return nomCentre;
    }

    public void setNomCentre(String nomCentre) {
        this.nomCentre = nomCentre;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public int getnInscrits() {
        return nInscrits;
    }

    public void incnInscrits(int nInscrits) { 
        if (hihaPlaces()) this.nInscrits++;
    }
    
    public void setnInscrits(int nInscrits) { 
        this.nInscrits = nInscrits;
    }

    //To string
    @Override
    public String toString() {
        return "ActivitatPeriodica [diaSetmana=" + diaSetmana + ", horari=" + horari + ", dataInici=" + dataInici
                + ", nSetmanes=" + nSetmanes + ", places=" + places + ", preu=" + preu + ", nomCentre=" + nomCentre
                + ", ciutat=" + ciutat + "]";
    }
    
    @Override
    public Activitat copia() {
        return new ActivitatPeriodica(
            this.nom,
            this.colectius,
            this.dataIniciInscripcio,
            this.dataFinalInscripcio,
            this.diaSetmana,
            this.horari,
            this.dataInici,
            this.nSetmanes,
            this.places,
            this.preu,
            this.nomCentre,
            this.ciutat
        );

    }

    @Override
    public boolean hihaPlaces(){
        return (this.nInscrits<places);
    }

    
}