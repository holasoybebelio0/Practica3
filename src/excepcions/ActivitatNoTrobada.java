package excepcions;

public class ActivitatNoTrobada extends BenestarExcepcio {
    
    public ActivitatNoTrobada(String nomActivitat) {
        super("Error: No s'ha trobat cap activitat amb el nom " + nomActivitat + ".");
    }
}