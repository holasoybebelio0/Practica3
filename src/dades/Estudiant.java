package dades;

public class Estudiant extends Usuari{
    private String ensenyament;
    private int anyInici;
    
    public Estudiant(String alies, String correu, String ensenyament, int anyInici){
        super(alies, correu);
        this.ensenyament = ensenyament;
        this.anyInici = anyInici;
    }

    @Override
    public String getCorreuComplet() {
        return getAlies() + "@estudiants.urv.cat";
    }
   

    @Override
    public String toString() {
        return "Usuari Estudiant:\n" +
               "Alies: " + getAlies() + "\n" +
               "Correu: " + getCorreuComplet() + "\n" +
               "Ensenyament: " + ensenyament + "\n" +
               "Any d'inici: " + anyInici + "\n";
    }
   
    @Override
    public Usuari copia() {
        return new Estudiant(this.getAlies(), this.getCorreu(), this.ensenyament, this.anyInici);
    }

    public String getEnsenyament() {
        return ensenyament;
    }

    public void setEnsenyament(String ensenyament) {
        this.ensenyament = ensenyament;
    }

    public int getAnyInici() {
        return anyInici;
    }

    public void setAnyInici(int anyInici) {
        this.anyInici = anyInici;
    }

    


    
}
