package opcionsmenu;
import dades.*;

import java.time.LocalDate;
import java.util.Scanner;

public class menuAran {
/*
•    5. Mostrar activitats actives avui (dins període).
•    11. Mostrar usuaris apuntats i llista d’espera d’una activitat.
•    12. Eliminar usuari d’una activitat i promocionar llista d’espera.
•    15. Afegir una activitat online.
•    18. Mostrar el resum de valoracions que ha fet un usuari.
•    20. Calcular l’usuari més actiu d’un col·lectiu.
•    21. Donar de baixa activitats amb poca participació.
*/
private Scanner scanner;

public menuAran() {
        this.scanner = new Scanner(System.in);
}


public void mostrarActivitatsAvui(LlistaActivitats llista){
    String result = "Activitats actives avui():\n";
    for(int i = 0; i < llista.getnElems(); i++){
        
    }
}

public void mostrarUsuarisApuntatsIEspera(){

}

public void eliminarUsuariActivitat(LlistaInscripcions llista){
    boolean trobat = false;
    int i = 0;
    System.out.println("Quin usuari vols esborrar?\n");
    String nom = scanner.nextLine();

    while(!trobat && i < llista.getNumInscripcions()){
        if(nom.equals(llista.getInscripcio(i).getNomParticipant())){
            for(int j = i; j  < llista.getNumInscripcions() - 1; j++){
                llista.setInscripcioPosicio(j, llista.getInscripcio(j+1));
            }
            trobat = true;
        }
        else{
            i++;
        }
    }
    

}

public void afegirActivitatOnline(){

}

public void mostrarResumValoracionsUsuari(){

}

public void calcularUsuariMesActiuColectiu(){

}

public void donarBaixaActivitats(){

}
}
