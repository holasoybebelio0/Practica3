package dades;


public class inscripcions { 
    private String nomParticipant;
    private String dataInscripcio;
    private int valoracio;

    public inscripcions(String nomParticipant, String dataInscripcio) {
        this.nomParticipant = nomParticipant;
        this.dataInscripcio = dataInscripcio;
        this.valoracio = -1;
    }
    public String getNomParticipant() {
        return nomParticipant;
    }
    public String getDataInscripcio() {
        return dataInscripcio;
    }
    public int getValoracio() {
        return valoracio;
    }
    @Override
    public String toString() {
        return "Inscripcions [nomParticipant=" + nomParticipant + ", dataInscripcio=" + dataInscripcio + "]";
    }   

    public inscripcions copia() {
        inscripcions inscopia = new inscripcions(nomParticipant, dataInscripcio);
        if (this.valoracio != -1) inscopia.valorarExperiencia(this.valoracio);
        return inscopia;
    }
    //SI esta llena la lista, se añade a otra lista de espera que maximo puede tener 10 personas

    public LlistaInscripcions LlistaEspera() {
        return new LlistaInscripcions(10);
    }
    //Al acabar la actividad los usuarios valoraran del 1-10 la experiencia
   
    public void valorarExperiencia(int valoracio) {
        if (valoracio < 1 || valoracio > 10) {
            System.out.println("Valoració invàlida. Si us plau, introdueix un valor entre 1 i 10.");
        } else {
            this.valoracio = valoracio; //Es guarda la valoracio de l'usuari
            System.out.println("Gràcies per la teva valoració de " + valoracio + "!");
        }
    }

}
