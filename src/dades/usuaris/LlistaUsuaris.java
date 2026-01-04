package dades.usuaris;
import java.io.*;

public class LlistaUsuaris {

    private Usuari[] usuaris;
    private int numUsuaris;

    public LlistaUsuaris(int capacitat) {
        usuaris = new Usuari[capacitat];
        numUsuaris = 0;
    }

    public void afegirUsuari(Usuari usuari) {
        if (numUsuaris < usuaris.length) {
            usuaris[numUsuaris] = usuari.copia();
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
        String resultat = "Llista de "+numUsuaris+" usuaris:\n";
        for (int i = 0; i < numUsuaris; i++) {
            resultat += usuaris[i].toString() + "\n";
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

    public void mostrarDetallUsuariNom(String alies) {
        boolean trobat = false;
        for (int i=0; i<numUsuaris; i++) {
            if (usuaris[i].getAlies().equalsIgnoreCase(alies)){
                System.out.println(usuaris[i].toString());
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("\nNo s'han trobat coincidències.\n");
        }
    }


    /**
     * Guarda la llista d'usuaris en un fitxer de text.
     * @param fitxer Nom del fitxer on es guardaran els usuaris.
     * @throws IOException 
     */
    public void guardarUsuarisFitxer(String fitxer) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fitxer));
            for (int i = 0; i < numUsuaris; i++) {
                Usuari uActual = usuaris[i];
        
                // Amb instanceof i els if else if es busca el tipus de l'usuari i s'escriu al fitxer segons el seu tipus
                if (uActual instanceof PDI) {
                    PDI usuariPDI = (PDI) uActual;
                    writer.write("PDI;" + usuariPDI.getAlies() + ";" + usuariPDI.getCorreu() + ";" + usuariPDI.getDepartament() + ";" + usuariPDI.getCampus());
                } 
                else if (uActual instanceof PTGAS) {
                    PTGAS usuariPTGAS = (PTGAS) uActual;
                    writer.write("PTGAS;" + usuariPTGAS.getAlies() + ";" + usuariPTGAS.getCorreu() + ";" + usuariPTGAS.getCampus());
                } 
                else if (uActual instanceof Estudiant) {
                    Estudiant usuariEstudiant = (Estudiant) uActual;
                    writer.write("ESTUDIANT;" + usuariEstudiant.getAlies() + ";" + usuariEstudiant.getCorreu() + ";" + usuariEstudiant.getEnsenyament() + ";" + usuariEstudiant.getAnyInici());
                }
        
                writer.newLine();
            }
            writer.close();
            System.out.println("Usuaris guardats correctament a " + fitxer);
        }
        catch (IOException e) {
            System.out.println("Error al guardar els usuaris al fitxer.");
        }
    }



    public void carregarUsuarisFitxer(String fitxerUsuaris) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(fitxerUsuaris));
                String linia;

                this.numUsuaris = 0; 
    
                while ((linia = reader.readLine()) != null) {
                    String[] dades = linia.split(";");
    
                    if (dades.length > 0) {
                        String tipus = dades[0];
                        Usuari nouUsuari = null;
    
                        if (tipus.equalsIgnoreCase("PDI")) {
                                nouUsuari = new PDI(dades[1], dades[2], dades[3], dades[4]);
                        } 
                        else if (tipus.equalsIgnoreCase("PTGAS")) {
                                nouUsuari = new PTGAS(dades[1], dades[2], dades[3]);
                        } 
                        else if (tipus.equalsIgnoreCase("ESTUDIANT")) {
                                    int any = Integer.parseInt(dades[4]);
                                    nouUsuari = new Estudiant(dades[1], dades[2], dades[3], any);
                        }
                        if (nouUsuari != null) {
                            this.afegirUsuari(nouUsuari);
                        }
                    }
                }
                reader.close();
                System.out.println("S'han carregat " + this.numUsuaris + " usuaris correctament.");
    
            } 
            catch (FileNotFoundException e) {
                System.out.println("Fitxer d'entrada d'usuaris no trobat.");
            }
            catch (IOException e) {
                System.out.println("S'ha produit un error al fitxer d'entrada d'usuaris.");
            }
        }

}
