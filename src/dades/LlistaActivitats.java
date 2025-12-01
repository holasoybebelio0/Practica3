package dades;

public class LlistaActivitats  {
    private Activitat[] llista; 
    private int nElems; 

    public LlistaActivitats (int capacitat) {
        this.llista = new Activitat[capacitat];
        this.nElems = 0;
    }

    public void afegirActivitat (Activitat act) {
        if (nElems<llista.length) {
            llista[nElems] = act; 
            nElems++;
        } else {
            System.out.println("Error: La llista és plena i no permet més activitats.");
        }
    }

    public Activitat getActivitat (int index) {
        if (index >= 0 && index < nElems) {
            return llista[index];
        }
        return null; 
    }

    public Activitat getActivitatPerNom(String nom) {
        for (int i = 0; i<nElems; i++) {
            if (llista[i].getNom().equalsIgnoreCase(nom)){
                return llista[i];
            }
        }
        return null; 
    }

    public int getnElems() {
        return nElems; 
    }

    public void mostrarLlista() {
        for (int i = 0; i<nElems; i++) {
            System.out.println(llista[i].toString());
        }
    }
}
