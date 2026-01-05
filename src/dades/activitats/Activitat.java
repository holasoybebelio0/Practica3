package dades.activitats;

import dades.inscripcions.LlistaInscripcions;
import dades.inscripcions.inscripcions;
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

    public boolean conteUsuari (String nom) {
        return llistaInscripcions.conteUsuari(nom);
    }
    
    public double mitjanaValoracions () {
        return llistaInscripcions.mitjanaValoracions();
    }

   public boolean estaInscrit(String nomParticipant) {
        for (int i = 0; i < llistaInscripcions.getNumInscripcions(); i++) {
            if (llistaInscripcions.getInscripcio(i).getNomParticipant().equalsIgnoreCase(nomParticipant)) {
                return true;
            }
        }
        return false;
    }
    
    public LlistaInscripcions getLlistaInscripcions() {
        return llistaInscripcions;
    }

    public LlistaInscripcions getLlistaEspera() {
        return llistaEspera;
    }


    public boolean haacabat(LocalDate dataActual) {
        return dataActual.isAfter(dataFinalInscripcio);
    }

     private boolean esColectiuPermes(String tipusUsuari) {
        for (String colectiu : colectius) {
            if (colectiu.equalsIgnoreCase(tipusUsuari)) {
                return true;
            }
        }
        return false;
    }

    public boolean afegirInscripcio(inscripcions inscripcio, LocalDate dataActual) {
    // 1. Verificar si estamos en período de inscripción
    if (dataActual.isBefore(dataIniciInscripcio)) {
        System.out.println("ERROR: Encara no s'ha obert el període d'inscripció. Data d'inici: " + dataIniciInscripcio);
        return false;
    }
    
    if (dataActual.isAfter(dataFinalInscripcio)) {
        System.out.println("ERROR: El període d'inscripció ja ha finalitzat. Data final: " + dataFinalInscripcio);
        return false;
    }
    
    // 2. Verificar si el colectivo está permitido
    if (!esColectiuPermes(inscripcio.getTipusUsuari())) {
        System.out.println("ERROR: El col·lectiu " + inscripcio.getTipusUsuari() + " no està permès per a aquesta activitat.");
        System.out.println("Col·lectius permesos: " + java.util.Arrays.toString(colectius));
        return false;
    }
    
    // 3. Verificar si el usuario ya está inscrito
    if (estaInscrit(inscripcio.getNomParticipant())) {
        System.out.println("ERROR: L'usuari " + inscripcio.getNomParticipant() + " ja està inscrit en aquesta activitat.");
        return false;
    }
    
    // 4. Verificar si hay plazas disponibles (o lista de espera)
    if (!llistaInscripcions.hihaPlaces() && llistaInscripcions.getNumEspera() >= 10) {
        System.out.println("ERROR: Activitat completa. No hi ha places disponibles ni espai a la llista d'espera.");
        return false;
    }
    
    // 5. Añadir la inscripción
    boolean inscripcioExitosa = llistaInscripcions.afegirInscripcio(inscripcio);
    
    if (inscripcioExitosa) {
        String tipusInscripcio = (llistaInscripcions.getNumInscripcions() <= llistaInscripcions.getPlacesMaximes()) ? 
                                 "inscrit" : "a la llista d'espera";
        System.out.println("ÈXIT: " + inscripcio.getNomParticipant() + " s'ha " + tipusInscripcio + 
                         " correctament a l'activitat '" + nom + "'.");
        return true;
    } else {
        System.out.println("ERROR: No s'ha pogut completar la inscripció.");
        return false;
    }
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

    public abstract boolean haAcabat(LocalDate dia);
}
