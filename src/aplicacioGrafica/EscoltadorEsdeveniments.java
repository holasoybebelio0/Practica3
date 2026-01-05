package aplicacioGrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EscoltadorEsdeveniments implements ActionListener {

    private FinestraPrincipal finestra;

    public EscoltadorEsdeveniments(FinestraPrincipal finestra) {
        this.finestra = finestra;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boto = (JButton) e.getSource();
        String text = boto.getText();

        // 1. Botó de control de data
        if (text.equals("Canviar Data")) {
            finestra.recarregarData();
        }
        // 2. Botons de Filtre
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
        // 3. Botons de Dia (són números)
        else {
            try {
                int dia = Integer.parseInt(text);
                
                String informacio = finestra.getTextDetallDia(dia);
                
                JOptionPane.showMessageDialog(null, 
                    informacio, 
                    "Detall del dia " + dia, 
                    JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException ex) {
                // Ignorar clics que no siguin botons vàlids
            }
        }
    }
}