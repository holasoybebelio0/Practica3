package dades;

 abstract class Usuari {
    private String alies;
    private String correu;

    /**
     * Constructor de la classe Usuari
     * @param alies Alies de l'usuari
     * @param correu Correu de l'usuari
     */
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
