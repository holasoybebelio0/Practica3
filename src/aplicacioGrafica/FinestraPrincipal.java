package aplicacioGrafica;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import dades.*; 

public class FinestraPrincipal extends JFrame {

    // Referències
    private LlistaActivitats dades;
    private JButton[] botonsDies;
    private JButton[] botonsFiltre;
    
    // Components de control de data
    private JComboBox<String> comboMesos;
    private JTextField textAny;
    private JButton botoActualitzar;

    // Estat actual
    private int filtreActual = 0; // 0=Tots, 1=UnDia, 2=Periodica, 3=Online
    
    // Configuració inicial
    private int mesActual = 11; // Novembre per defecte
    private int anyActual = 2025;
    private final int MAX_DIES = 31; // Màxim possible de botons

    public FinestraPrincipal() {
        super("Agenda Benestar URV");
        
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));

        // 1. CARREGAR DADES
        dades = new LlistaActivitats(100);
        dades.carregarActivitatsFitxer("activitats.txt");

        // 2. CREAR ELS PANELLS
        crearPanellSuperior(); // Ara inclou selecció de data
        crearCalendariCentral();
        crearFiltresInferior();

        // 3. PINTAR INICIALMENT
        actualitzarCalendari(); 

        this.setVisible(true);
    }

    private void crearPanellSuperior() {
        JPanel panellNord = new JPanel();
        panellNord.setLayout(new GridLayout(2, 1)); // Dues files: Títol i Controls

        // --- Fila 1: Títol ---
        JPanel panellTitol = new JPanel();
        JLabel titol = new JLabel("CALENDARI D'ACTIVITATS");
        titol.setFont(new Font("Arial", Font.BOLD, 18));
        panellTitol.add(titol);
        
        // --- Fila 2: Controls de Data ---
        JPanel panellControls = new JPanel();
        panellControls.setLayout(new FlowLayout());

        panellControls.add(new JLabel("Mes:"));
        
        // Desplegable de mesos
        String[] mesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", 
                          "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
        comboMesos = new JComboBox<>(mesos);
        comboMesos.setSelectedIndex(10); // Seleccionem Novembre (index 10) per defecte
        panellControls.add(comboMesos);

        panellControls.add(new JLabel("Any:"));
        
        // Caixa de text per l'any
        textAny = new JTextField("2025", 5);
        panellControls.add(textAny);

        // Botó per canviar
        botoActualitzar = new JButton("Canviar Data");
        // Li passem 'this' a l'escoltador
        botoActualitzar.addActionListener(new EscoltadorEsdeveniments(this));
        panellControls.add(botoActualitzar);

        // Afegim tot al panell nord
        panellNord.add(panellTitol);
        panellNord.add(panellControls);
        
        this.add(panellNord, BorderLayout.NORTH);
    }

    private void crearCalendariCentral() {
        JPanel panellCalendari = new JPanel();
        // GridLayout: 5 files, 7 columnes = 35 forats (suficient per 31 dies)
        panellCalendari.setLayout(new GridLayout(5, 7, 5, 5));

        botonsDies = new JButton[MAX_DIES];
        EscoltadorEsdeveniments escoltador = new EscoltadorEsdeveniments(this);

        for (int i = 0; i < MAX_DIES; i++) {
            botonsDies[i] = new JButton(String.valueOf(i + 1));
            botonsDies[i].setBackground(Color.LIGHT_GRAY);
            botonsDies[i].addActionListener(escoltador);
            panellCalendari.add(botonsDies[i]);
        }
        this.add(panellCalendari, BorderLayout.CENTER);
    }

    private void crearFiltresInferior() {
        JPanel panellFiltres = new JPanel();
        panellFiltres.setLayout(new FlowLayout());

        String[] noms = {"Totes", "Un Dia", "Periòdiques", "Online"};
        botonsFiltre = new JButton[noms.length];
        EscoltadorEsdeveniments escoltador = new EscoltadorEsdeveniments(this);

        for (int i = 0; i < noms.length; i++) {
            botonsFiltre[i] = new JButton(noms[i]);
            botonsFiltre[i].addActionListener(escoltador);
            panellFiltres.add(botonsFiltre[i]);
        }
        botonsFiltre[0].setBackground(Color.CYAN);
        this.add(panellFiltres, BorderLayout.SOUTH);
    }

    // --- LÒGICA VISUAL ---

    // Aquest mètode es crida quan cliquem "Canviar Data"
    public void recarregarData() {
        try {
            // Obtenim l'any del quadre de text
            int nouAny = Integer.parseInt(textAny.getText());
            // Obtenim el mes del desplegable (0=Gener, ... 10=Novembre). Sumem 1 per LocalDate
            int nouMes = comboMesos.getSelectedIndex() + 1; 

            this.anyActual = nouAny;
            this.mesActual = nouMes;

            actualitzarCalendari(); // Repintar
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "L'any ha de ser un número vàlid.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void canviarFiltre(int nouFiltre) {
        this.filtreActual = nouFiltre;
        for (int i = 0; i < botonsFiltre.length; i++) {
            if (i == nouFiltre) botonsFiltre[i].setBackground(Color.CYAN);
            else botonsFiltre[i].setBackground(null);
        }
        actualitzarCalendari();
    }

    // Mètode principal que pinta els dies
    public void actualitzarCalendari() {
        // 1. Calculem quants dies té el mes seleccionat
        YearMonth anyMes = YearMonth.of(anyActual, mesActual);
        int diesAlMes = anyMes.lengthOfMonth(); // Ex: Febrer 2025 -> 28

        for (int i = 0; i < MAX_DIES; i++) {
            int dia = i + 1;

            if (dia <= diesAlMes) {
                // El dia existeix (Ex: dia 5) -> El mostrem i calculem color
                botonsDies[i].setVisible(true);
                
                LocalDate dataDia = LocalDate.of(anyActual, mesActual, dia);
                if (hiHaActivitat(dataDia)) {
                    botonsDies[i].setBackground(Color.GREEN);
                } else {
                    botonsDies[i].setBackground(Color.LIGHT_GRAY);
                }
            } else {
                // El dia no existeix (Ex: dia 30 de Febrer) -> L'amaguem
                botonsDies[i].setVisible(false);
            }
        }
    }

    private boolean hiHaActivitat(LocalDate dia) {
        for (int i = 0; i < dades.getnElems(); i++) {
            Activitat act = dades.getActivitat(i);
            if (act.teClasseAvui(dia)) {
                boolean compleix = false;
                if (filtreActual == 0) compleix = true;
                else if (filtreActual == 1 && act instanceof ActivitatUnDia) compleix = true;
                else if (filtreActual == 2 && act instanceof ActivitatPeriodica) compleix = true;
                else if (filtreActual == 3 && act instanceof ActivitatOnline) compleix = true;
                
                if (compleix) return true;
            }
        }
        return false;
    }

    // Retorna els detalls per al Popup
    public String getTextDetallDia(int dia) {
        LocalDate dataDia = LocalDate.of(anyActual, mesActual, dia);
        String resultat = "";
        boolean trobat = false;

        for (int i = 0; i < dades.getnElems(); i++) {
            Activitat act = dades.getActivitat(i);
            if (act.teClasseAvui(dataDia)) {
                boolean mostrar = (filtreActual == 0) ||
                                  (filtreActual == 1 && act instanceof ActivitatUnDia) ||
                                  (filtreActual == 2 && act instanceof ActivitatPeriodica) ||
                                  (filtreActual == 3 && act instanceof ActivitatOnline);
                if (mostrar) {
                    resultat += act.toString() + "\n\n";
                    trobat = true;
                }
            }
        }
        if (!trobat) return "No hi ha activitats aquest dia.";
        return resultat;
    }

    public static void main(String[] args) {
        new FinestraPrincipal();
    }
}