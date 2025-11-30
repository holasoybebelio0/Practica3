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

    public int getNumUsuaris() {
        return numUsuaris;
    }

    @Override
    public String toString() {
        String resultat = "Llista d'usuaris:\n";
        for (int i = 0; i < numUsuaris; i++) {
            resultat += usuaris[i].toString() + "\n\n";
        }
        return resultat;
    }

    public LlistaUsuaris copia() {
        LlistaUsuaris llistaCopiada = new LlistaUsuaris(usuaris.length);
        for (int i = 0; i < numUsuaris; i++) {
            llistaCopiada.afegirUsuari(usuaris[i].copia());
        }
        return llistaCopiada;
    }
}
