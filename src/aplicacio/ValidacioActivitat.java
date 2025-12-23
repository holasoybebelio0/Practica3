package aplicacio;
import dades.*;
import java.time.LocalDate;

public class ValidacioActivitat{
    public static void main(String[] args) throws Exception {
        System.out.println("==================================================");
        System.out.println("      INICIANT BATERIA DE PROVES: ACTIVITATS      ");
        System.out.println("==================================================");

        // ---------------------------------------------------------------
        // 1. PROVA DE LA CLASSE ACTIVITAT D'UN DIA
        // ---------------------------------------------------------------
        System.out.println("\n[PROVA 1] Creant ActivitatUnDia...");
        
        String[] colectiusTaller = {"Estudiants", "PDI"};

        LocalDate dataIniciInscripcio = LocalDate.of(2025, 12, 2);
        LocalDate dataFinalInscripcio = LocalDate.of(2025, 12, 2);
        LocalDate dataActivitat = LocalDate.of(2025, 12, 2);
        ActivitatUnDia taller = new ActivitatUnDia(
            "Taller Java",              // nom
            colectiusTaller,            // colectius
            dataIniciInscripcio,               // inici inscripcio
            dataFinalInscripcio,               // final inscripcio
            dataActivitat,               // data activitat
            "Tarragona",                    // ciutat
            15.50,                          // preu (Posem poques per provar d'omplir-ho)
            3,                      // places
            "10:00"                 // horari
        );

        // Verificació de dades bàsiques
        if (taller.getNom().equals("Taller Java") && taller.getPreu() == 15.50) {
            System.out.println("   -> [OK] Dades bàsiques correctes.");
        } else {
            System.out.println("   -> [ERROR] Les dades no coincideixen.");
        }

        // ---------------------------------------------------------------
        // 2. PROVA DE GESTIÓ DE PLACES (hihaPlaces i incNumInscrits)
        // ---------------------------------------------------------------
        System.out.println("\n[PROVA 2] Verificant gestió d'aforament...");
        
        System.out.println("   -> Estat inicial: " + taller.getnInscrits() + "/" + taller.getPlaces() + " inscrits.");
        
        // Inscrivim 3 persones (omplim l'activitat)
        taller.incnInscrits();
        taller.incnInscrits();
        taller.incnInscrits();
        
        if (taller.getnInscrits() == 3 && !taller.hihaPlaces()) {
            System.out.println("   -> [OK] Activitat plena detectada correctament (hihaPlaces = false).");
        } else {
            System.out.println("   -> [ERROR] La lògica de places plenes falla.");
        }

        // Intentem inscriure una 4a persona (no hauria de deixar)
        taller.incnInscrits();
        if (taller.getnInscrits() == 3) {
            System.out.println("   -> [OK] El sistema ha impedit inscriure més gent del límit.");
        } else {
            System.out.println("   -> [ERROR] S'ha superat el límit de places!");
        }

        // ---------------------------------------------------------------
        // 3. PROVA DE LA CLASSE ACTIVITAT PERIÒDICA
        // ---------------------------------------------------------------
        System.out.println("\n[PROVA 3] Creant ActivitatPeriodica...");
        
        String[] colectiusCurs = {"PTGAS"};
        ActivitatPeriodica curs = new ActivitatPeriodica(
            "Curs Ioga", 
            colectiusCurs, 
            LocalDate.of(2025, 9, 1), LocalDate.of(2025, 9, 30),
            "Dilluns",          // dia setmana
            "18:00",            // horari
            LocalDate.of(2025, 10, 01),       // data inici
            10,                 // num setmanes
            20,                 // places
            120.00,             // preu total
            "Gimnàs URV",       // centre
            "Reus"              // ciutat
        );

        System.out.println("   -> toString() resultat: " + curs.toString());
        if (curs.getPreu() == 120.00 && curs.getnSetmanes() == 10) {
            System.out.println("   -> [OK] Dades de l'activitat periòdica correctes.");
        } else {
            System.out.println("   -> [ERROR] Dades incorrectes.");
        }

        // ---------------------------------------------------------------
        // 4. PROVA DEL PATRÓ COPIA (DEEP COPY)
        // ---------------------------------------------------------------
        System.out.println("\n[PROVA 4] Verificant mètode copia()...");
        
        // Fem una còpia del curs de Ioga
        Activitat copiaCurs = curs.copia();
        
        // Modifiquem la còpia (li canviem el nom)
        copiaCurs.setNom("Curs Ioga AVANÇAT");
        
        // Comprovem que l'original NO hagi canviat
        if (curs.getNom().equals("Curs Ioga") && copiaCurs.getNom().equals("Curs Ioga AVANÇAT")) {
            System.out.println("   -> [OK] La còpia és independent (l'original no s'ha modificat).");
        } else {
            System.out.println("   -> [ERROR] Modificar la còpia ha afectat l'original!");
        }

        // ---------------------------------------------------------------
        // 5. PROVA DE LA LLISTA D'ACTIVITATS
        // ---------------------------------------------------------------
        System.out.println("\n[PROVA 5] Verificant LlistaActivitats...");
        
        LlistaActivitats llista = new LlistaActivitats(10); // Capacitat 10
        
        llista.afegirActivitat(taller);
        llista.afegirActivitat(curs);
        
        if (llista.getnElems() == 2) {
            System.out.println("   -> [OK] S'han afegit 2 activitats correctament.");
        }
        
        // Cerca per nom
        Activitat resultatCerca = llista.getActivitatPerNom("curs ioga"); // Provem minúscules
        if (resultatCerca != null && resultatCerca.getNom().equals("Curs Ioga")) {
            System.out.println("   -> [OK] Cerca per nom funciona (ignorant majúscules/minúscules).");
        } else {
            System.out.println("   -> [ERROR] No s'ha trobat l'activitat per nom.");
        }
        
        // Cerca d'algo que no existeix
        if (llista.getActivitatPerNom("Zumba") == null) {
            System.out.println("   -> [OK] Cercar una activitat inexistent retorna null.");
        }
        // activitat online prova
        System.out.println("\n[PROVA 6] Creant ActivitatOnline...");
        String[] colectiusOnline = {"Tothom"}; 
        int periodeDiesOnline = 30;         
        ActivitatOnline webinar = new ActivitatOnline(
            "Webinar Programació",      // nom
            colectiusOnline,            // colectius
            LocalDate.of(2025, 9, 05),               // inici inscripcio
            LocalDate.of(2025, 9, 20),               // final inscripcio
            "https://example.com/webinar", // enllaç
            LocalDate.of(2025, 9, 17),
            periodeDiesOnline
        );
        System.out.println("   -> toString() resultat: " + webinar.toString());
        if (webinar.getPreu() == 0.0 && webinar.hihaPlaces()) {
            System.out.println("   -> [OK] Dades de l'activitat online correctes.");
        } else {
            System.out.println("   -> [ERROR] Dades incorrectes.");
        }
        
        // ---------------------------------------------------------------
        // ACT 4. Activitats que tenen classe avui
        // ---------------------------------------------------------------
        llista.mostrarActivitatsClasseAvui(LocalDate.of(2025, 12, 2));

        System.out.println("\n==================================================");
        System.out.println("            FI DE LES PROVES - RESULTAT           ");
        System.out.println("==================================================");
    
        

        
    }
    
}