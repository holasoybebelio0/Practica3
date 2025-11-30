package dades;

public class PTGAS extends Usuari {
    private String campus;

    public PTGAS(String alies, String correu, String campus) {
        super(alies, correu);
        this.campus = campus;
    }

    @Override
    public String getCorreuComplet() {
        return (getCorreu() + "@urv.cat");
    }

    public String getCampus() {
        return campus;
    }

    @Override
    public String toString() {
        return "Usuari PTGAS:\n" +
               "Alies: " + getAlies() + "\n" +
               "Correu: " + getCorreuComplet() + "\n" +
               "Campus: " + campus;
    }

    @Override
    public Usuari copia() {
        return new PTGAS(this.getAlies(), this.getCorreu(), this.campus);
    }

}
