package dades.activitats;
import dades.inscripcions.*;
import dades.usuaris.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

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

    public void mostrarLlista(int filtre) { // IMPLEMENTAR EN EL VALIDACIOACTIVITAT las instrucciones de lo que vale cada filtro
        if (filtre == 0) {
            for (int i=0; i<nElems; i++) {
                System.out.println(llista[i].toString());
            }
        } else if (filtre != 0) {
            for (int i=0; i<nElems; i++) {
                if (llista[i].getTipus() == filtre) {
                    System.out.println(llista[i].toString());
                } else {
                    System.out.println("\nError: El valor del filtre només pot ser 0, 1 o 2.");

                }
            }
        } 
    }

    public void mostrarActivitatsClasseAvui (LocalDate dia) {
        for (int i=0; i<nElems; i++) {
            if (llista[i].teClasseAvui(dia)) {
                System.out.println(llista[i].toString());
            }
        }
    }
    /**
     * Tasca 1: Modificar Data Actual del sistema.
     * @param dataActual
     * @return
     */
    public LocalDate moddataActual(LocalDate dataActual) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("La data actual del sistem es: "+ dataActual);
        System.out.println("Introdueix la data actual: ");
        
       
       if (scanner.hasNextLine()) {
        String entrada = scanner.nextLine();
        try {
            dataActual = LocalDate.parse(entrada);
            System.out.println("Data actualitzada correctament a: " + dataActual);
        } catch (DateTimeParseException e) {
            System.out.println("ERROR: El format de la data no és vàlid. Ha de ser AAAA-MM-DD (ex: 2025-10-15).");
        }
       

       }
       scanner.close();
        return dataActual;

    }
   /**
     * Tasca 3: Mostra les activitats en període d'inscripció i amb places.
     * @param dataSimulacio La data actual del sistema (del menú).
     */
    public void mostrarActivitatsDisponibles(LocalDate dataSimulacio) {
        System.out.println("--- ACTIVITATS EN PERÍODE D'INSCRIPCIÓ I AMB PLACES (" + dataSimulacio + ") ---");
        
        boolean algunaTrobada = false;

        for (int i = 0; i < nElems; i++) {
            Activitat act = llista[i];
            if (act.hihaPlaces()) {
                try {

                    //Control de dates (Període d'inscripció) 


                    LocalDate iniciInscripcio = act.getDataIniciInscripcio();
                    LocalDate fiInscripcio = act.getDataFinalInscripcio();

                    // Comprovem si dataSimulacio està dins el rang [inici, final]
                    boolean dinsPeriode = !dataSimulacio.isBefore(iniciInscripcio) && !dataSimulacio.isAfter(fiInscripcio);

                    if (dinsPeriode) {
                        System.out.println(act.toString());
                        algunaTrobada = true;
                    }

                } catch (DateTimeParseException e) {
                    
                }
            }
        }

        if (!algunaTrobada) {
            System.out.println("No s'han trobat activitats disponibles en aquesta data.");
        }
    }


    //TASCA 6 mostrar activitats amb placçes disponibles
    public void mostrarActivitatsAmbPlaces() {
        System.out.println("--- ACTIVITATS AMB PLACES DISPONIBLES ---");
        boolean algunaTrobada = false;

        for (int i = 0; i < nElems; i++) {
            Activitat act = llista[i];
            if (act.hihaPlaces()) {
                System.out.println(act.toString());
                algunaTrobada = true;
            }
        }

        if (!algunaTrobada) {
            System.out.println("No hi ha activitats amb places disponibles.");
        }
    }

    
    //TASCA 10 Inscriures a una activitat
    // TASCA 10 Inscriures a una activitat
public boolean inscriureUsuariActivitat(Usuari usuari, String nomActivitat, LocalDate dataActual) {
    //Buscar la actividad
    Activitat activitat = getActivitatPerNom(nomActivitat);
    if (activitat == null) {
        System.out.println("ERROR: L'activitat '" + nomActivitat + "' no existeix.");
        return false;
    }
    
    //Verificar período de inscripción
    if (dataActual.isBefore(activitat.getDataIniciInscripcio()) || 
        dataActual.isAfter(activitat.getDataFinalInscripcio())) {
        System.out.println("ERROR: No estàs dins del període d'inscripció.");
        return false;
    }
    
    //Determinar tipo de usuario
    String tipusUsuari = "";
    if (usuari instanceof Estudiant) {
        tipusUsuari = "Estudiant";
    } else if (usuari instanceof PDI) {
        tipusUsuari = "PDI";
    } else if (usuari instanceof PTGAS) {
        tipusUsuari = "PTGAS";
    }       
    
    //Verificar si ya está inscrito
    if (activitat.estaInscrit(usuari.getAlies())) {
        System.out.println("ERROR: L'usuari ja està inscrit en aquesta activitat.");
        return false;
    }
    
    // CREAR OBJETO inscripcions PRIMERO
    inscripcions novaInscripcio = new inscripcions(usuari.getAlies(), tipusUsuari, dataActual);
    
    // Llamar a la nueva versión del método
    boolean inscrit = activitat.afegirInscripcio(novaInscripcio, dataActual);
    
    if (inscrit) {
        System.out.println("SUCCESS: " + usuari.getAlies() + " inscrit correctament a " + nomActivitat);
        return true;
    } else {
        System.out.println("ERROR: No s'ha pogut inscriure a " + nomActivitat + 
                         ". Places plenes i llista d'espera completa.");
        return false;
    }
}

    //tasca 13 Afegir una nova activitat d'un dia
    public void afegirActivitatUnDia(ActivitatUnDia act) {
        if (nElems < llista.length) {
            llista[nElems] = act;
            nElems++;
            System.out.println("Activitat d'un dia afegida correctament: " + act.getNom());
        } else {
            System.out.println("Error: La llista és plena i no es pot afegir més activitats.");
        }
    }

 /**
     * TASCA 16: Valorar una activitat per part d'un assistent.
     * Requisits: L'activitat ha d'haver acabat i l'usuari hi ha d'haver assistit.
     */
    public void valorarActivitat(String nomActivitat, String aliesUsuari, int puntuacio, LocalDate dataActual) {
        Activitat act = getActivitatPerNom(nomActivitat);

        if (act == null) {
            System.out.println("Error: L'activitat " + nomActivitat + " no existeix.");
            return;
        }

        //Validar puntuació (0-10) 
        if (puntuacio < 0 || puntuacio > 10) {
            System.out.println("Error: La puntuació ha de ser entre 0 i 10.");
            return;
        }

        //Comprovar si l'activitat ha acabat 
        // Si no has implementat haAcabat a Activitat, et donarà error aquí.
        if (!act.haacabat(dataActual)) {
            System.out.println("Error: No es pot valorar l'activitat perquè encara no ha acabat.");
            return;
        }

        //Comprovar si l'usuari va assistir (està a la llista d'inscripcions)
        LlistaInscripcions llistaInscripcions = act.getLlistaInscripcions();
        inscripcions inscripcioUsuari = llistaInscripcions.getInscripcioPerNom(aliesUsuari);

        if (inscripcioUsuari != null) {
            // Guardem la valoració a l'objecte inscripció
            inscripcioUsuari.valorarExperiencia(puntuacio);
            System.out.println("Valoració registrada correctament: Usuari " + aliesUsuari + " -> " + puntuacio + " punts.");
        } else {
            System.out.println("Error: L'usuari " + aliesUsuari + " no consta com a inscrit en aquesta activitat.");
        }
    }

    /**
     * TASCA 19: Calcular la mitjana de valoracions per col·lectius.
     */
    public void mostrarMitjanaValoracions(String nomActivitat) {
        Activitat act = getActivitatPerNom(nomActivitat);

        if (act == null) {
            System.out.println("Error: L'activitat no existeix.");
            return;
        }

        System.out.println("--- ESTADÍSTIQUES DE VALORACIÓ PER: " + act.getNom().toUpperCase() + " ---");
        
        LlistaInscripcions llista = act.getLlistaInscripcions();
        
        
        double mitjanaPDI = llista.calcularMitjanaPerCol("PDI");
        double mitjanaPTGAS = llista.calcularMitjanaPerCol("PTGAS");
        double mitjanaEst = llista.calcularMitjanaPerCol("Estudiant");

        System.out.printf("Mitjana PDI:       %.2f\n", mitjanaPDI);
        System.out.printf("Mitjana PTGAS:     %.2f\n", mitjanaPTGAS);
        System.out.printf("Mitjana Estudiant: %.2f\n", mitjanaEst);
        System.out.println("--------------------------------------------------");
    }



    public void mostrarDetallActivitatNom (String nom) {
        boolean trobat = false;
        for (int i=0; i<nElems; i++) {
            if (llista[i].getNom().equalsIgnoreCase(nom)) {
                System.out.println(llista[i].toString());
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("\nNo s'han trobat coincidències.");
        }
    }

    public void afegirActivitatPeriodica (ActivitatPeriodica actperiod) {
        if (nElems < llista.length) {
            llista[nElems] = actperiod.copia();
            nElems++;
        } else {
            System.out.println("No es poden afegir més usuaris, capacitat plena.");
        }
    }

    public void mostrarActivitatsUsuari (String nom) {
        for (int i = 0; i<nElems; i++) {
            if (llista[i].conteUsuari(nom)) {
                System.out.println(llista[i].toString());
            }
        }
    }

    public void mostrarResumActivitatAcabada (LocalDate date) {
        boolean acabada = false; 
        for (int i=0; i<nElems; i++) {
            if (llista[i].haAcabat(date)) {
                System.out.println(" - " + llista[i].mitjanaValoracions());
                acabada = true;
            }
        }

        if (!acabada) {
            System.out.println("En la data "+date+" no s'han acabat les activitats, o no hi han.");
        }
    }
    
    /**
     * Elimina l'activitat que es troba a la posició indicada i desplaça les activitats restants
     * @param index Posició de l'activitat a esborrar.
     * @return true si s'ha esborrat, false si l'índex no era vàlid.
     */
    public boolean eliminarActivitat(int index) {
        boolean trobat;
        if (index < 0 || index >= nElems) {
            trobat = false;
        }
        else{
            for (int i = index; i < nElems - 1; i++) {
                llista[i] = llista[i + 1];
            }
    
            nElems--;
            llista[nElems] = null;
            trobat = true;
        }
        return trobat;
    }

    /**
     * Guarda la llista d'activitats en un fitxer de text.
     * @param fitxer Nom del fitxer (ex: "activitats.txt")
     */
    public void guardarActivitatsFitxer(String fitxer) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fitxer))) {
            for (int i = 0; i < nElems; i++) {
                Activitat act = llista[i];
                
                // Convertim l'array de col·lectius a un String separat per comes per guardar-lo
                String colStr = String.join(",", act.getColectius());

                if (act instanceof ActivitatUnDia) {
                    ActivitatUnDia a = (ActivitatUnDia) act;
                    // Format: UNDIA;nom;col·lectius;iniciInsc;fiInsc;data;ciutat;preu;places;horari
                    writer.write(String.format("UNDIA;%s;%s;%s;%s;%s;%s;%.2f;%d;%s",
                        a.getNom(), colStr, a.getDataIniciInscripcio(), a.getDataFinalInscripcio(),
                        a.getData(), a.getCiutat(), a.getPreu(), a.getPlaces(), a.getHorari()));

                } else if (act instanceof ActivitatPeriodica) {
                    ActivitatPeriodica a = (ActivitatPeriodica) act;
                    // Format: PERIODICA;nom;col·lectius;iniciInsc;fiInsc;diaSetmana;horari;dataInici;nSetmanes;places;preu;centre;ciutat
                    writer.write(String.format("PERIODICA;%s;%s;%s;%s;%s;%s;%s;%d;%d;%.2f;%s;%s",
                        a.getNom(), colStr, a.getDataIniciInscripcio(), a.getDataFinalInscripcio(),
                        a.getDiaSetmana(), a.getHorari(), a.getDataInici(), a.getnSetmanes(), a.getPlaces(), a.getPreu(), a.getNomCentre(), a.getCiutat()));

                } else if (act instanceof ActivitatOnline) {
                    ActivitatOnline a = (ActivitatOnline) act;
                    // Format: ONLINE;nom;col·lectius;iniciInsc;fiInsc;enllac;dataIniciActivitat;periodeDies
                    writer.write(String.format("ONLINE;%s;%s;%s;%s;%s;%s;%d",
                        a.getNom(), colStr, a.getDataIniciInscripcio(), a.getDataFinalInscripcio(),
                        a.getEnllaç(), a.getDataIniciActivitat(), a.getPeriodeDies()));
                }
                
                writer.newLine();
            }
            System.out.println("Activitats guardades correctament a " + fitxer);
        } catch (IOException e) {
            System.out.println("Error al guardar les activitats al fitxer: " + e.getMessage());
        }
    }

    /**
     * Carrega les activitats des d'un fitxer de text.
     * @param fitxer Nom del fitxer (ex: "activitats.txt")
     */
    public void carregarActivitatsFitxer(String fitxer) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fitxer))) {
            String linia;
            this.nElems = 0; // Reiniciem la llista

            while ((linia = reader.readLine()) != null) {
                String[] dades = linia.split(";");
                if (dades.length > 0) {
                    String tipus = dades[0];
                    Activitat novaActivitat = null;
                    
                    // Recuperem els col·lectius (separats per coma)
                    String[] col = dades[2].split(",");

                    // Formatejador per llegir les dates (si calgués, però LocalDate.parse accepta YYYY-MM-DD per defecte)
                    // Nota: Si al guardar uses toString(), el format és YYYY-MM-DD.
                    
                    if (tipus.equalsIgnoreCase("UNDIA")) {
                        // UNDIA;nom;col·lectius;iniciInsc;fiInsc;data;ciutat;preu;places;horari
                        novaActivitat = new ActivitatUnDia(
                            dades[1], col,
                            LocalDate.parse(dades[3]), LocalDate.parse(dades[4]), // Dates inscripció
                            LocalDate.parse(dades[5]), // Data activitat
                            dades[6], Double.parseDouble(dades[7].replace(",", ".")), // Preu (replace per si decimal ve amb coma)
                            Integer.parseInt(dades[8]), dades[9]
                        );

                    } else if (tipus.equalsIgnoreCase("PERIODICA")) {
                        // PERIODICA;nom;col·lectius;iniciInsc;fiInsc;diaSetmana;horari;dataInici;nSetmanes;places;preu;centre;ciutat
                        novaActivitat = new ActivitatPeriodica(
                            dades[1], col,
                            LocalDate.parse(dades[3]), LocalDate.parse(dades[4]),
                            dades[5], dades[6],
                            LocalDate.parse(dades[7]), // Data inici activitat (ja és LocalDate a la teva classe)
                            Integer.parseInt(dades[8]), Integer.parseInt(dades[9]),
                            Double.parseDouble(dades[10].replace(",", ".")), 
                            dades[11], dades[12]
                        );

                    } else if (tipus.equalsIgnoreCase("ONLINE")) {
                        // ONLINE;nom;col·lectius;iniciInsc;fiInsc;enllac;dataIniciActivitat;periodeDies
                        novaActivitat = new ActivitatOnline(
                            dades[1], col,
                            LocalDate.parse(dades[3]), LocalDate.parse(dades[4]),
                            dades[5],
                            LocalDate.parse(dades[6]), // Data inici activitat online
                            Integer.parseInt(dades[7])
                        );
                    }

                    if (novaActivitat != null) {
                        this.afegirActivitat(novaActivitat);
                    }
                }
            }
            System.out.println("S'han carregat " + this.nElems + " activitats correctament.");

        } catch (FileNotFoundException e) {
            System.out.println("Fitxer d'activitats no trobat (" + fitxer + "). Es crearà en guardar.");
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al carregar les activitats: " + e.getMessage());
        }
    }
}

