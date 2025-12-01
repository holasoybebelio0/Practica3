package aplicacio;
import dades.*;

public class ValidacioUsuaris{
    public static void main(String[] args) throws Exception {

        //VALIDACIO Estudiant (EVELIO)
        //ESTE SI ESO MUEVELO AL FINAL DEL TODO
        Estudiant estudiant1 = new Estudiant("evelio", "evelio123", "Informatica", 2023);
        System.out.println("Validacio Usuaris");
        System.out.println("Alias: " + estudiant1.getAlies());  
        System.out.println("Correu Complet: " + estudiant1.getCorreuComplet());
        System.out.println("Enensenyament: " + estudiant1.getEnsenyament());


    }


}