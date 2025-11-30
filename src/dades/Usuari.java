package dades;

 abstract class Usuari {
    private String alies;
    private String correu;

    public Usuari(String alies, String correu) {
        this.alies = alies;
        this.correu = correu;
    }

    public String getAlies() {
        return alies;
    }

    public String getCorreu() {
        return correu;
    }

    public abstract String getCorreuComplet();

    public abstract String toString();

    public abstract Usuari copia();
}
