package aplicacio;
import dades.*;

public class ValidacioUsuaris{
    public static void main(String[] args) throws Exception {

        //VALIDACIO PDI (ARAN)
        PDI prova1 = new PDI("Josep", "josep.garcia","DEIM", "Sescelades");
        System.out.println("---------------------Validació PDI---------------------\n");
        System.out.println(prova1);

        //VALIDACIO PTGAS (ARAN)
        PTGAS prova2 = new PTGAS("Marta03", "RUIZ.MARTA","Catalunya");
        System.out.println("--------------------Validació PTGAS-------------------\n");
        System.out.println(prova2);

        //VALIDACIO Estudiant (EVELIO)
        Estudiant prova3 = new Estudiant("evelio", "evelio123", "Informatica", 2023);
        System.out.println("--------------------Validació Estudiant-------------------\n");
        System.out.println(prova3);

        //VALIDACIO LLISTAUSUARIS (ARAN)
        LlistaUsuaris prova4 = new LlistaUsuaris(5);
        
        prova4.afegirUsuari(prova1);
        prova4.afegirUsuari(prova2);
        prova4.afegirUsuari(prova3);
        prova4.afegirUsuari(new PTGAS("Fred","ptgas.fred","Vila-Seca"));

        System.out.println("----------------Validació LlistaUsuaris (4/5 usuaris registrats)----------------\n");
        System.out.println(prova4);

        System.out.println("\n-->\tIntentant afegir el 5è usuari...");
        prova4.afegirUsuari(prova1);
        System.out.println(prova4);

        System.out.println("\n-->\tIntentant afegir un 6è usuari (hauria de ser error)...");
        prova4.afegirUsuari(prova3);




    }


}