package dades;

public class PDI extends Usuari {
    private String departament;
    private String campus;

    public PDI(String alies, String correu, String departament, String campus) {
        super(alies, correu);
        this.departament = departament;
        this.campus = campus;
    }

    @Override
    public String getCorreuComplet() {
        return (super.getCorreu() + "@urv.cat");
    }

    public String getDepartament() {
        return departament;
    }

    public String getCampus() {
        return campus;
    }

    @Override
    public String toString() {
        return "Usuari PDI:\n" +
               "Alies: " + getAlies() + "\n" +
               "Correu: " + getCorreuComplet() + "\n" +
               "Departament: " + departament + "\n" +
               "Campus: " + campus;
    }

    @Override
    public Usuari copia() {
        return new PDI(this.getAlies(), this.getCorreu(), this.departament, this.campus);
    }
    
}
