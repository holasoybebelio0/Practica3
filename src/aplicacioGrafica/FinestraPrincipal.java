package aplicacioGrafica;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import dades.*;
import dades.activitats.Activitat;
import dades.activitats.ActivitatOnline;
import dades.activitats.ActivitatPeriodica;
import dades.activitats.ActivitatUnDia;
import dades.activitats.LlistaActivitats; 

public class FinestraPrincipal extends JFrame {
    public static void main(String[] args) {
        new FinestraPrincipal();
    }
    
    private LlistaActivitats dades;
    private JButton[] botonsDies;
    private JButton[] botonsFiltre;
    
    private JComboBox<String> botoMes;
    private JComboBox<Integer> botoAny;
    private JButton botoAplicar;
    
    private int filtreActual = 0; // 0=Tots, 1=UnDia, 2=Periodica, 3=Online
    
    
    private int mesSeleccionat = 1;
    private int anySeleccionat = 2026;
    private final int MAX_DIES = 31;

    
    public FinestraPrincipal() {
        super("Programa Benestar URV");
        
        this.setSize(800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 10));

        // Carregar dades d'un fitxer que es digui activitats.txt
        dades = new LlistaActivitats(1000);
        dades.carregarActivitatsFitxer("activitats.txt");

        // Crear el menu
        crearPanellSuperior();
        crearCalendari();
        crearBotonsFiltres();

        // Actualitzar el calendari per defecte
        actualitzarCalendari(); 

        this.setVisible(true);
    }



    private void crearPanellSuperior() {
        int nfil=2, ncols=1;
        JPanel panellSuperior = new JPanel();
        panellSuperior.setLayout(new GridLayout(nfil, ncols)); // Títol i seleccio de data

        // Titol
        JPanel panellTitol = new JPanel();
        panellTitol.add(new JLabel("CALENDARI D'ACTIVITATS"));
        

        // Seleccio de data
        JPanel panellControls = new JPanel();
        panellControls.setLayout(new FlowLayout());
        panellControls.add(new JLabel("Mes:"));
        

        // Boto per a seleccionar el mes
        String[] mesos = {"Gener", "Febrer", "Març", "Abril", "Maig", "Juny", "Juliol", "Agost", "Setembre", "Octubre", "Novembre", "Desembre"};
        
        botoMes = new JComboBox<String>(); // Lo del combo es per posar una llista desplegable
        for (int i = 0; i < mesos.length; i++) {
            botoMes.addItem(mesos[i]);
        }
        panellControls.add(botoMes);


        // Text per escriure l'any
        panellControls.add(new JLabel("Any:"));
        
        botoAny = new JComboBox<Integer>();
        for (int i = 2020; i <= 2030; i++) {
            botoAny.addItem(i);
        }
        panellControls.add(botoAny);
        botoAny.setSelectedItem(2026);


        // Botó per canviar la data
        botoAplicar = new JButton("Aplicar Data");
        botoAplicar.addActionListener(new ListenerBotons(this));// Listener de apretar el botó de canviar la data
        panellControls.add(botoAplicar);

        // Afegir tot al panell superior
        panellSuperior.add(panellTitol);
        panellSuperior.add(panellControls);
        
        this.add(panellSuperior, BorderLayout.NORTH);
    }

    private void crearCalendari() {
        JPanel panellCalendari = new JPanel();
        panellCalendari.setLayout(new GridLayout(5,7)); // 7 columnes pels dies de la setmana i 5 files pel maxim de setmanes d'un mes

        botonsDies = new JButton[MAX_DIES];
        ListenerBotons listener = new ListenerBotons(this);

        for (int i = 0; i < MAX_DIES; i++) {
            botonsDies[i] = new JButton(String.valueOf(i + 1));
            botonsDies[i].setBackground(Color.RED);
            botonsDies[i].addActionListener(listener);
            panellCalendari.add(botonsDies[i]);
        }
        this.add(panellCalendari, BorderLayout.CENTER);
    }

    private void crearBotonsFiltres() {
        JPanel panellFiltres = new JPanel();
        panellFiltres.setLayout(new FlowLayout());

        String[] noms = {"Totes", "Un Dia", "Periòdiques", "Online"};
        botonsFiltre = new JButton[4];
        ListenerBotons listener = new ListenerBotons(this);

        for (int i = 0; i < noms.length; i++) {
            botonsFiltre[i] = new JButton(noms[i]);
            botonsFiltre[i].addActionListener(listener);
            panellFiltres.add(botonsFiltre[i]);
        }
        this.add(panellFiltres, BorderLayout.SOUTH);
    }


    /**
     * Funció per recarregar la data seleccionada per la selecció de mes i any al clicar el botó "Aplicar Data"
     */
    public void recarregarData() {
            int mesSeleccionat = botoMes.getSelectedIndex() + 1; 
            int anySeleccionat = botoAny.getSelectedIndex() + 2020;

            this.anySeleccionat = anySeleccionat;
            this.mesSeleccionat = mesSeleccionat;

            actualitzarCalendari();
    }

    /**
     * Funció per canviar el filtre segons el botó premut
     * @param seleccio Índex del filtre seleccionat (0=Tots, 1=UnDia, 2=Periodica, 3=Online)
     */
    public void canviarFiltre(int seleccio) {
        this.filtreActual = seleccio;
        for (int i = 0; i < 4; i++) { // i < 4 perque tenim 4 botons de filtre
            if (i == seleccio){
                botonsFiltre[i].setBackground(Color.GREEN);
            } 
            else {
                botonsFiltre[i].setBackground(Color.LIGHT_GRAY);
            } 
        }
        actualitzarCalendari();
    }

    /**
     * Funció per actualitzar el calendari segons el mes, any i filtre actuals
     */
    public void actualitzarCalendari() {

        // Mirar el mes i any per mirar quants dies té
        YearMonth anyMes = YearMonth.of(anySeleccionat, mesSeleccionat);
        int diesAlMes = anyMes.lengthOfMonth();

        for (int i = 0; i < MAX_DIES; i++) {
            int dia = i + 1;
            if (dia <= diesAlMes) {
                // El setvisible en true fa que es vegi el boto del dia
                botonsDies[i].setVisible(true);
                
                LocalDate dataDia = LocalDate.of(anySeleccionat, mesSeleccionat, dia);
                if (hiHaActivitat(dataDia)) {
                    botonsDies[i].setBackground(Color.GREEN);
                } else {
                    botonsDies[i].setBackground(Color.WHITE);
                }
            } else {
                // Si el dia no existeix al mes seleccionat s'amaga el boto
                botonsDies[i].setVisible(false);
            }
        }
    }

    /**
     * Comprova si hi ha alguna activitat per a la data segons el filtre actual
     * @param data Data a comprovar
     * @return true si hi ha alguna activitat, false si no
     */
    private boolean hiHaActivitat(LocalDate data) {
        boolean coincideix = false;

        for (int i = 0; i < dades.getnElems(); i++) {
            Activitat act = dades.getActivitat(i);

            if (act.teClasseAvui(data)) {
                if (filtreActual == 0) {
                    coincideix = true;
                } 
                else if (filtreActual == 1 && act instanceof ActivitatUnDia){
                    coincideix = true;
                } 
                else if (filtreActual == 2 && act instanceof ActivitatPeriodica){
                    coincideix = true;
                }
                else if (filtreActual == 3 && act instanceof ActivitatOnline){
                    coincideix = true;
                }
            }
        }
        return coincideix;
    }

    
    public String detallDia(int dia) {
        LocalDate data = LocalDate.of(anySeleccionat, mesSeleccionat, dia);
        String resultat = "";
        boolean hiHaAlguna = false;
        boolean mostrar = false;

        for (int i = 0; i < dades.getnElems(); i++) {
            Activitat act = dades.getActivitat(i);
            mostrar = false;

            if (act.teClasseAvui(data)) {
                if (act.teClasseAvui(data)) {
                    if (filtreActual == 0) {
                        mostrar = true;
                    } 
                    else if (filtreActual == 1 && act instanceof ActivitatUnDia){
                        mostrar = true;
                    } 
                    else if (filtreActual == 2 && act instanceof ActivitatPeriodica){
                        mostrar = true;
                    }
                    else if (filtreActual == 3 && act instanceof ActivitatOnline){
                        mostrar = true;
                    }

                    if (mostrar) {
                        resultat += act.toString() + "\n\n";
                        hiHaAlguna = true;
                    }
                }
            }
        }

        if (!hiHaAlguna) {
                resultat += "No hi ha activitats aquest dia amb el filtre seleccionat.";
        }

        return resultat;
    }

}