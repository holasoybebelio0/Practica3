package dades;

import java.time.LocalDate;


public class inscripcions { 
    private String nomParticipant;
    private String dataInscripcio;
    private String tipususuari;
    private int valoracio = -1; // Valoració de l'experiència (1-10)


    public inscripcions(String nomParticipant, String tipususuari, LocalDate dataActual) {
        this.nomParticipant = nomParticipant;
        this.dataInscripcio = dataActual.toString();
        this.valoracio = -1; // Inicialment sense valoració
        this.tipususuari = tipususuari;
    }
    public String getNomParticipant() {
        return nomParticipant;
    }
    public String getDataInscripcio() {
        return dataInscripcio;
    }
    public int getValoracio()  {
        return valoracio;
    }
    @Override
    public String toString() {
        return "Inscripcions [nomParticipant=" + nomParticipant + ", dataInscripcio=" + dataInscripcio + "]";
    }   

    public inscripcions copia() {

        inscripcions inscopia = new inscripcions(nomParticipant, tipususuari, LocalDate.parse(dataInscripcio));
        if (this.valoracio != -1) inscopia.valorarExperiencia(this.valoracio);
        return inscopia;

    }
    //SI esta llena la lista, se añade a otra lista de espera que maximo puede tener 10 personas

    public LlistaInscripcions LlistaEspera() {
        return new LlistaInscripcions(10);
    }
    //Al acabar la actividad los usuarios valoraran del 1-10 la experiencia
   
   public void valorarExperiencia(int valoracio) {
        if (valoracio < 0 || valoracio > 10) {
            System.out.println("Valoració invàlida (0-10).");
        } else {

            this.valoracio = valoracio;
            System.out.println("Valoració de " + valoracio + " registrada per " + nomParticipant);

        }
    }

    public String getTipusUsuari() {
        return tipususuari;
    }

    

}
