package dades;

public class LlistaUsuaris {

    private Usuari[] usuaris;
    private int numUsuaris;

    public LlistaUsuaris(int capacitat) {
        usuaris = new Usuari[capacitat];
        numUsuaris = 0;
    }

    public void afegirUsuari(Usuari usuari) {
        if (numUsuaris < usuaris.length) {
            usuaris[numUsuaris] = usuari;
            numUsuaris++;
        } else {
            System.out.println("No es poden afegir més usuaris, capacitat plena.");
        }
    }

    public Usuari getUsuari(int index) {
        if (index >= 0 && index < numUsuaris) {
            return usuaris[index];
        } else {
            System.out.println("Índex fora de rang.");
            return null;
        }
    }



}
