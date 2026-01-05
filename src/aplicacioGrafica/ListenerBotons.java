package aplicacioGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ListenerBotons implements ActionListener {

    private FinestraPrincipal finestra;

    public ListenerBotons(FinestraPrincipal finestra) {
        this.finestra = finestra;
    }
    
    public void actionPerformed(ActionEvent evt) {
        JButton boto = (JButton) evt.getSource();
        String text = boto.getText();

        // Boto de canviar la data
        if (text.equals("Aplicar Data")) {
            finestra.recarregarData();
        }
        // Botons dels filtres
        else if (text.equals("Totes")) {
            finestra.canviarFiltre(0);
        } 
        else if (text.equals("Un Dia")) {
            finestra.canviarFiltre(1);
        } 
        else if (text.equals("Periòdiques")) {
            finestra.canviarFiltre(2);
        } 
        else if (text.equals("Online")) {
            finestra.canviarFiltre(3);
        } 

        // Botons dels dies del calendari
        else {
            int dia = Integer.parseInt(text);
            String detalls = finestra.detallDia(dia);   
            JOptionPane.showMessageDialog(null, detalls, "Detall del dia " + dia, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}