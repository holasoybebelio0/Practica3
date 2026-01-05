package opcionsmenu;

import dades.activitats.Activitat;
import dades.activitats.ActivitatOnline;
import dades.activitats.ActivitatPeriodica;
import dades.activitats.ActivitatUnDia;
import dades.activitats.LlistaActivitats;
import dades.inscripcions.LlistaInscripcions;
import dades.inscripcions.inscripcions;
import dades.usuaris.*;
import excepcions.ActivitatNoTrobada;
import excepcions.BenestarExcepcio;
import java.time.LocalDate;
import java.util.Scanner;

public class menuAran {
    /*
    5. Mostrar activitats actives avui (dins període).
    11. Mostrar usuaris apuntats i llista d’espera d’una activitat.
    12. Eliminar usuari d’una activitat i promocionar llista d’espera.
    15. Afegir una activitat online.
    18. Mostrar el resum de valoracions que ha fet un usuari.
    20. Calcular l’usuari més actiu d’un col·lectiu.
    21. Donar de baixa activitats amb poca participació.
    */
    private Scanner scanner;

    public menuAran() {
            this.scanner = new Scanner(System.in);
    }

    /**
     * Opció 5: Mostra per pantalla el nom de les activitats actives en la data indicada.
     * @param llista Llista d'activitats on buscar les activitats actives.
     * @param dataActual Data actual per comparar amb les dates d'inscripció de les activitats.
     */
    public void mostrarActivitatsAvui(LlistaActivitats llista, LocalDate dataActual) {
        System.out.println("--- ACTIVITATS ACTIVES AVUI ( " + dataActual + " ) ---");

        boolean algunaTrobada = false;
        int comptador = 0;

        for (int i = 0; i < llista.getnElems(); i++) {
            Activitat act = llista.getActivitat(i);

            if (act.esActiva(dataActual)) {
                comptador++;
                System.out.println("Activitat "+comptador+".\t"+ act.getNom() + "(Inscripcions obertes del " + act.getDataIniciInscripcio() + " al " + act.getDataFinalInscripcio() + ")");
                algunaTrobada = true;
            }
        }
        if (!algunaTrobada) {
            System.out.println("No hi ha cap activitat activa avui.");
        }
    }

    /**
     * Opció 11: Mostrar els usuaris apuntats i la llista d'espera d'una activitat.
     * @param llista La llista de totes les activitats disponibles.
     */
    public void mostrarUsuarisApuntatsIEspera(LlistaActivitats llista) {

        try{
            System.out.println("\nIntrodueix el nom de l'activitat:");
            String nomActivitat = scanner.nextLine(); 
    
            Activitat act = llista.getActivitatPerNom(nomActivitat);
    
            //si la activitat no existeix, llançar excepció
            if(act == null) {
                throw new ActivitatNoTrobada(nomActivitat);
            }

            System.out.println("\nUsuaris a l'activitat: " + act.getNom());
            
            System.out.println("\n--- LLISTA D'INSCRITS ---");
            
            if (act.getLlistaInscripcions() != null && act.getLlistaInscripcions().getNumInscripcions() > 0) {
                System.out.println(act.getLlistaInscripcions().toString());
            } else {
                System.out.println("   No hi ha ningú inscrit.");
            }

            System.out.println("\n--- LLISTA D'ESPERA ---");
            if (act.getLlistaEspera() != null && act.getLlistaEspera().getNumInscripcions() > 0) {
                System.out.println(act.getLlistaEspera().toString());
            } else {
                System.out.println("   La llista d'espera és buida.");
            }
        }
        catch (BenestarExcepcio e){
            System.out.println("Error: " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Error inesperat: " + e.getMessage());
        }


    }

    /**
     * Opció 12: Eliminar un usuari d'una activitat i promocionar un usuari de la llista d'espera.
     * @param llistaActivitats La llista de totes les activitats disponibles.
     */
    public void eliminarUsuariActivitat(LlistaActivitats llistaActivitats) {
        System.out.println("\n--- ELIMINAR USUARI D'ACTIVITAT ---");
        
        try {
            // Demanar activitat
            System.out.println("Introdueix el nom de l'activitat: ");
            String nomActivitat = scanner.nextLine();
            
            Activitat act = llistaActivitats.getActivitatPerNom(nomActivitat);

            // Si no existeix la activitat, llançar excepció
            if (act == null) {
                throw new ActivitatNoTrobada(nomActivitat);
            }

            // Demanar usuari
            System.out.println("Introdueix l'usuari a esborrar de l'activitat " + act.getNom() + ":");
            String usuari = scanner.nextLine();

            LlistaInscripcions llistaInscrits = act.getLlistaInscripcions();
            LlistaInscripcions llistaEspera = act.getLlistaEspera();

            // Intentar esborrar de la llista d'inscrits, si no està, provar a la llista d'espera
            boolean esborrat = llistaInscrits.eliminarInscripcio(usuari);

            if (esborrat) {
                System.out.println("L'usuari " + usuari + " ha estat eliminat correctament.");

                // Promocionar el primer de la llista d'espera, si n'hi ha
                if (llistaEspera.getNumInscripcions() > 0) {
                    inscripcions primerEnEspera = llistaEspera.treurePrimer();
                    if (primerEnEspera != null) {
                        llistaInscrits.afegirInscripcio(primerEnEspera);
                        System.out.println("L'usuari " + primerEnEspera.getNomParticipant() +" ha passat de la llista d'espera a la llista d'inscrits.");
                    }
                }
            } else {
                // Mirar si l'usuari estava a la llista d'espera
                boolean esborratEspera = llistaEspera.eliminarInscripcio(usuari);
                if (esborratEspera) {
                    System.out.println("L'usuari " + usuari + " ha estat eliminat de la llista d'espera.");
                } else {
                    System.out.println("Error: L'usuari no consta ni com a inscrit ni a la llista d'espera.");
                }
            }

        } 
        catch (BenestarExcepcio e) {
            System.out.println(e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }
    }

    /**
     * Opció 15: Afegir una nova activitat online.
     * @param llista La llista d'activitats on s'afegirà la nova.
     */
    public void afegirActivitatOnline(LlistaActivitats llista) {
        try {
            System.out.println("Nom de l'activitat:");
            String nom = scanner.nextLine();

            System.out.println("Data inici inscripció (AAAA-MM-DD):");
            LocalDate dataInici = LocalDate.parse(scanner.nextLine());

            System.out.println("Data final inscripció (AAAA-MM-DD):");
            LocalDate dataFi = LocalDate.parse(scanner.nextLine());

            System.out.println("Data d'inici de l'activitat (AAAA-MM-DD):");
            LocalDate dataIniciActivitat = LocalDate.parse(scanner.nextLine());

            System.out.println("Número de dies de visualització:");
            int dies = Integer.parseInt(scanner.nextLine());

            System.out.println("Enllaç (URL):");
            String url = scanner.nextLine();

            System.out.println("A quins col·lectius va dirigida? (Separats per comes sense espais, ex: PDI,Estudiants)");
            String colectiusString = scanner.nextLine();
            String[] colectius = colectiusString.split(",");

            ActivitatOnline novaActivitat = new ActivitatOnline(
                nom, 
                colectius, 
                dataInici, 
                dataFi, 
                url, 
                dataIniciActivitat, 
                dies
            );

            llista.afegirActivitat(novaActivitat);
            
            System.out.println("-> Activitat online " + nom + " afegida correctament!\n");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Error: El format de la data és incorrecte. Ha de ser AAAA-MM-DD.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Has d'introduir un número vàlid per als dies.");
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }
    }

    /**
     * Opció 18: Mostrar el resum de valoracions que ha fet un usuari.
     * @param llistaActivitats Llista de totes les activitats
     */
    public void mostrarResumValoracionsUsuari(LlistaActivitats llistaActivitats) {
    
        System.out.println("Introdueix l'usuari que vols consultar: ");
        String alies = scanner.nextLine();

        boolean algunaValoracio = false;
        int activitatsTrobades = 0;

        System.out.println("Valoracions realitzades per l'usuari " + alies + ":");

        //Es reorren totes les activitats buscant les inscripcions de l'usuari i si tenen valoració
        for (int i = 0; i < llistaActivitats.getnElems(); i++) {
            Activitat act = llistaActivitats.getActivitat(i);
            LlistaInscripcions inscrits = act.getLlistaInscripcions();

            for (int j = 0; j < inscrits.getNumInscripcions(); j++) {
                inscripcions insc = inscrits.getInscripcio(j);
                
                if (insc.getNomParticipant().equalsIgnoreCase(alies)) {
                    activitatsTrobades++;
                    
                    // Si la valoracio es -1, vol dir que no s'ha valorat
                    if (insc.getValoracio() != -1) {
                        System.out.println(" - Activitat: " + act.getNom() + " | Nota: " + insc.getValoracio());
                        algunaValoracio = true;
                    } else {
                        System.out.println(" - Activitat: " + act.getNom() + " | (Sense valoració)");
                    }
                }
            }
        }

        // Missatges segons si no s'han trobat activitats o valoracions
        if (activitatsTrobades == 0) {
            System.out.println("(Aquest usuari no està inscrit a cap activitat o no existeix)");
        } else if (!algunaValoracio) {
            System.out.println("(L'usuari ha participat en activitats però no n'ha valorat cap)");
        }
    }

    /**
     * Opció 20: Calcular l'usuari més actiu d'un cert col·lectiu.
     * Versió amb DO-WHILE per validar l'entrada.
     */
    public void calcularUsuariMesActiuColectiu(LlistaActivitats llistaActivitats, LlistaUsuaris llistaUsuaris) {
        try {
            int opcio = 0;
            do {
                System.out.println("Escull el col·lectiu:");
                System.out.println("1. PDI");
                System.out.println("2. PTGAS");
                System.out.println("3. Estudiants");
                System.out.println("Opció:");
                try {
                    opcio = Integer.parseInt(scanner.nextLine());
                } 
                catch (NumberFormatException e) {
                    opcio = -1; // Si escriuen lletres es força que sigui incorrecte
                }
                if (opcio < 1 || opcio > 3) {
                    System.out.println("Opció incorrecta. Si us plau, tria 1, 2 o 3.\n");
                }

            } while (opcio < 1 || opcio > 3);

            Usuari usuariMesActiu = null;
            int maxInscripcions = -1;

            // Recorrer tots els usuaris
            for (int i = 0; i < llistaUsuaris.getNumUsuaris(); i++) {
                Usuari usuariActual = llistaUsuaris.getUsuari(i);
                boolean esDelColectiu = false;

                // Comprovar el tipus d'usuari
                if (opcio == 1 && usuariActual instanceof PDI) {
                    esDelColectiu = true;
                } 
                else if (opcio == 2 && usuariActual instanceof PTGAS) {
                    esDelColectiu = true;
                } 
                else if (opcio == 3 && usuariActual instanceof Estudiant) {
                    esDelColectiu = true;
                }

                
                if (esDelColectiu) {
                    int nInscripcions = comptarInscripcionsDeUsuari(usuariActual.getAlies(), llistaActivitats);

                    if (nInscripcions > maxInscripcions) {
                        maxInscripcions = nInscripcions;
                        usuariMesActiu = usuariActual;
                    }
                }
            }

            // Mostrar resultat
            if (usuariMesActiu != null && maxInscripcions > 0) {
                String nomColectiu;
                if (opcio == 1){
                    nomColectiu = "PDI";
                }
                else if(opcio == 2){
                    nomColectiu = "PTGAS";
                }
                else{
                    nomColectiu = "Estudiants";
                }
                System.out.println("\nL'usuari més actiu del col·lectiu " + nomColectiu + " és " + usuariMesActiu.getAlies() + " amb " + maxInscripcions + " inscripcions.");
            } 
            else {
                System.out.println("\nNo s'ha trobat cap usuari actiu per al col·lectiu seleccionat.");
            }

        } 
        catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }
    }

    /**
     * Opció 21: Donar de baixa activitats amb poca participació un cop tancada la inscripció.
     * @param llista La llista d'activitats
     * @param avui La data actual
     */
    public void donarBaixaActivitats(LlistaActivitats llista, LocalDate avui) {
        try {
            
            int eliminades = 0;

            //Es recorren les activitats des del final per evitar problemes en eliminar
            for (int i = llista.getnElems() - 1; i >= 0; i--) {
                Activitat act = llista.getActivitat(i);


                if (avui.isAfter(act.getDataFinalInscripcio())) {
                    boolean eliminar = false;
                    int numInscrits = 0;

                    // Conseguim el nombre d'inscrits
                    if (act.getLlistaInscripcions() != null) {
                        numInscrits = act.getLlistaInscripcions().getNumInscripcions();
                    }
                    // Si es una activitat online i té menys de 20 inscrits
                    if (act instanceof ActivitatOnline) {
                        if (numInscrits < 20) {
                            eliminar = true;
                            System.out.println(" Eliminant l'activitat online " + act.getNom() + " amb " + numInscrits + " inscrits...");
                        }
                    } 
                    // Si es una activitat presencial i té menys del 10% de les places ocupades
                    else {
                        int placesTotals = 0;
                        
                        if (act instanceof ActivitatUnDia) {
                            placesTotals = ((ActivitatUnDia)act).getPlaces();
                        } 
                        else if (act instanceof ActivitatPeriodica) {
                            placesTotals = ((ActivitatPeriodica)act).getPlaces();
                        }
                        
                        double minimRequerit = placesTotals * 0.10;

                        if (numInscrits < minimRequerit) {
                            eliminar = true;
                            System.out.println(" Eliminant l'activitat presencial " + act.getNom() + " amb " + numInscrits + "/" + placesTotals + " places...");
                        }
                    }

                    //Elmiminar si s'ha marcat per eliminar
                    if (eliminar) {
                        llista.eliminarActivitat(i);
                        eliminades++;
                    }
                }
            }

            if (eliminades > 0) {
                System.out.println("\nProcés finalitzat. S'han eliminat " + eliminades + " activitats.");
            } else {
                System.out.println("\nNo s'ha eliminat cap activitat.");
            }

        }
        catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }
    }

    /**
     * Mètode auxiliar per comptar a quantes activitats està apuntat un usuari
     * @param usuari L'àlies de l'usuari a comptar
     * @param llistaActivitats La llista d'activitats on buscar les inscripcions
     * @return El nombre d'activitats on l'usuari està inscrit
     */
    private int comptarInscripcionsDeUsuari(String usuari, LlistaActivitats llistaActivitats) {
        int comptador = 0;

        for (int i = 0; i < llistaActivitats.getnElems(); i++) {
            Activitat act = llistaActivitats.getActivitat(i);
            LlistaInscripcions inscrits = act.getLlistaInscripcions();

            if (inscrits != null) {
                int j = 0;
                boolean trobat = false;

                while (j < inscrits.getNumInscripcions() && !trobat) {
                    
                    if (inscrits.getInscripcio(j).getNomParticipant().equalsIgnoreCase(usuari)) {
                        comptador++;
                        trobat = true; // Surt del while per revisar la següent activitat
                    }
                    j++;
                }
            }
        }
        return comptador;
    }
}