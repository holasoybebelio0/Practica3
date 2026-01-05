package dades.activitats;

import java.time.LocalDate;

public class ActivitatOnline extends Activitat {
    private String enllac;
    private LocalDate dataIniciActivitat;
    private int periodeDies;

    public ActivitatOnline(String nom, String[] colectius, LocalDate dataIniciInscripcio, LocalDate dataFinalInscripcio, String enllac, LocalDate dataIniciActivitat, int periodeDies) {
        super(nom, colectius, dataIniciInscripcio, dataFinalInscripcio, 8000);
        this.enllac = enllac;
        this.dataIniciActivitat = dataIniciActivitat;
        this.periodeDies = periodeDies;
    }

    public String getEnllaç() {
        return enllac;
    }

    public void setUrl(String enllac) {
        this.enllac = enllac;
    }

    public LocalDate getDataIniciActivitat() {
        return dataIniciActivitat;
    }

    public void setDataIniciActivitat(LocalDate dataIniciActivitat) {
        this.dataIniciActivitat = dataIniciActivitat;
    }

    public int getPeriodeDies() {
        return periodeDies;
    }
    
    public void setPeriodeDies(int periodeDies) {
        this.periodeDies = periodeDies;
    }

    @Override
    public String toString() {
        return super.toString() + ", enllaç=" + enllac + "]";
    }

    @Override
    public Activitat copia() {
        return new ActivitatOnline(this.nom, this.colectius.clone(), this.dataIniciInscripcio, this.dataFinalInscripcio, this.enllac, this.dataIniciActivitat, this.periodeDies);
    }
    
    @Override
    public boolean hihaPlaces() {
        return true; // Les activitats online sempre tenen places disponibles
    }
    @Override
    public double getPreu() {   
        return 0.0; // Les activitats online són gratuïtes
    }
    @Override
    public int getTipus() {
        return 1;
    }

    @Override
    public boolean esActiva(LocalDate dataObjectiu) {
        try {
            LocalDate fi = dataIniciActivitat.plusDays(this.periodeDies);
            
            return !dataObjectiu.isBefore(dataIniciActivitat) && !dataObjectiu.isAfter(fi);
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean teClasseAvui(LocalDate dia) {
        return esActiva(dia);
    }

    @Override
    public boolean haAcabat(LocalDate avui) {
        try {
            if (dataIniciActivitat == null) return false;
            LocalDate fi = dataIniciActivitat.plusDays(this.periodeDies);
            return avui.isAfter(fi);
        } catch (Exception e) {
            return false;
        }
    }
}
