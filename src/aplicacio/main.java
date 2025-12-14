package aplicacio;
import opcionsmenu.*;

public class main {
    public static void main(String[] args) {
        menuEvelio menu = new menuEvelio();
        
        
        menu.modificarDataActual();
         
        System.out.println("Data actual simulada: " + menu.getDataActual());
        
        menu.mostrarActivitatsInscripcio();
    }
}