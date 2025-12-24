package dades;

import java.time.LocalDate;

public abstract class Activitat {
    protected String nom; 
    protected String[] colectius;
    protected LocalDate dataIniciInscripcio;
    protected LocalDate dataFinalInscripcio;
    protected LlistaInscripcions llistaInscripcions;
    protected LlistaInscripcions llistaEspera;

    public Activitat (String nom, String[] colectius, LocalDate dataIniciInscripcio, LocalDate dataFinalInscripcio, int places) {
        this.nom = nom; 
        this.dataFinalInscripcio = dataFinalInscripcio;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.colectius = colectius;
        this.llistaInscripcions = new LlistaInscripcions(places);
        this.llistaEspera = new LlistaInscripcions(10);
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

    public LocalDate getDataIniciInscripcio() {
        return dataIniciInscripcio;
    }

    public void setDataIniciInscripcio(LocalDate dataIniciInscripcio) {
        this.dataIniciInscripcio = dataIniciInscripcio;
    }

    public LocalDate getDataFinalInscripcio() {
        return dataFinalInscripcio;
    }

    public void setDataFinalInscripcio(LocalDate dataFinalInscripcio) {
        this.dataFinalInscripcio = dataFinalInscripcio;
    }

    public LlistaInscripcions getLlistaInscripcions() {
        return llistaInscripcions;
    }

    public LlistaInscripcions getLlistaEspera() {
        return llistaEspera;
    }

    @Override
    public String toString() {
        return "Activitat [nom=" + nom + ", colectius=" + colectius.toString() + ", dataIniciInscripcio="
                + dataIniciInscripcio + ", dataFinalInscripcio=" + dataFinalInscripcio + "]";
    }

    public abstract Activitat copia();

    public abstract boolean hihaPlaces();

    public abstract double getPreu();

    public abstract int getTipus();

    public abstract boolean esActiva(LocalDate dataObjectiu);

    public abstract boolean teClasseAvui(LocalDate dia);
}
