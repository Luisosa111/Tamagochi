package Vista;

import Modelo.Tamagotchi;
import Modelo.TipoAlimento;
import Modelo.TipoJuego;
import java.awt.*;
import java.util.List;
import javax.swing.*;

public class VistaGUI extends JFrame {
    public final JComboBox<Tamagotchi> comboMascotas = new JComboBox<>();
    public final JButton btnCrear = new JButton("+ Crear mascota");
    public final JButton btnAlimentar = new JButton("Alimentar");
    public final JButton btnJugar = new JButton("Jugar");
    public final JButton btnDormir = new JButton("Dormir");
    public final JButton btnDespertar = new JButton("Despertar");
    public final JButton btnEspecial1 = new JButton("Acción especial 1");
    public final JButton btnEspecial2 = new JButton("Acción especial 2");
    public final JButton btnTiempo = new JButton("Avanzar 1 hora");

    private final JLabel lblNombre = new JLabel("Selecciona o crea una mascota", SwingConstants.CENTER);
    private final JLabel lblEstado = new JLabel("Estado: -");
    private final JLabel lblDormir = new JLabel("Durmiendo: -");
    private final JLabel lblDetalles = new JLabel("Detalles: -");
    private final JProgressBar barHambre = crearBarra();
    private final JProgressBar barEnergia = crearBarra();
    private final JProgressBar barFelicidad = crearBarra();
    private final JTextArea registro = new JTextArea(6, 55);

    public VistaGUI() {
        super("Simulador Tamagotchi - POO");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        superior.add(new JLabel("Mascota:"));
        comboMascotas.setPreferredSize(new Dimension(220, 30));
        superior.add(comboMascotas);
        superior.add(btnCrear);
        add(superior, BorderLayout.NORTH);

        JPanel barras = new JPanel(new GridLayout(3, 2, 8, 10));
        barras.setBorder(BorderFactory.createTitledBorder("Necesidades (0-100)"));
        barras.add(new JLabel("Hambre (menor es mejor):"));
        barras.add(barHambre);
        barras.add(new JLabel("Energía:"));
        barras.add(barEnergia);
        barras.add(new JLabel("Felicidad:"));
        barras.add(barFelicidad);

        JPanel estado = new JPanel(new GridLayout(4, 1, 4, 6));
        lblNombre.setFont(new Font("Dialog", Font.BOLD, 18));
        estado.add(lblNombre);
        estado.add(lblEstado);
        estado.add(lblDormir);
        estado.add(lblDetalles);

        JPanel central = new JPanel(new BorderLayout(12, 12));
        central.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        central.add(estado, BorderLayout.NORTH);
        central.add(barras, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new GridLayout(2, 4, 8, 8));
        acciones.add(btnAlimentar);
        acciones.add(btnJugar);
        acciones.add(btnDormir);
        acciones.add(btnDespertar);
        acciones.add(btnEspecial1);
        acciones.add(btnEspecial2);
        acciones.add(btnTiempo);
        central.add(acciones, BorderLayout.SOUTH);
        add(central, BorderLayout.CENTER);

        registro.setEditable(false);
        registro.setLineWrap(true);
        registro.setWrapStyleWord(true);
        registro.setText("Bienvenido al simulador Tamagotchi.\n");
        add(new JScrollPane(registro), BorderLayout.SOUTH);

        setSize(820, 540);
        setLocationRelativeTo(null);
        actualizarEstado(null);
    }

    private JProgressBar crearBarra() {
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setStringPainted(true);
        return barra;
    }

    public String seleccionarEspecie() {
        Object[] opciones = {"Perro", "Gato", "Cuyo", "Muñeca"};
        Object seleccion = JOptionPane.showInputDialog(this, "Elige una especie:",
                "Adoptar Tamagotchi", JOptionPane.QUESTION_MESSAGE,
                null, opciones, opciones[0]);
        return seleccion == null ? null : seleccion.toString();
    }

    public String pedirNombre() {
        return JOptionPane.showInputDialog(this, "Nombre de la mascota:");
    }

    public TipoAlimento seleccionarAlimento() {
        return (TipoAlimento) JOptionPane.showInputDialog(this, "Elige un alimento:",
                "Alimentar", JOptionPane.QUESTION_MESSAGE,
                null, TipoAlimento.values(), TipoAlimento.CROQUETAS);
    }

    public TipoJuego seleccionarJuego() {
        return (TipoJuego) JOptionPane.showInputDialog(this, "Elige un juego:",
                "Jugar", JOptionPane.QUESTION_MESSAGE,
                null, TipoJuego.values(), TipoJuego.PELOTA);
    }

    public Tamagotchi mascotaSeleccionada() {
        return (Tamagotchi) comboMascotas.getSelectedItem();
    }

    public void agregarMascota(Tamagotchi mascota) {
        comboMascotas.addItem(mascota);
        comboMascotas.setSelectedItem(mascota);
    }

    public void mostrarMensaje(String mensaje) {
        registro.append(mensaje + "\n");
        registro.setCaretPosition(registro.getDocument().getLength());
    }

    public void actualizarEstado(Tamagotchi t) {
        boolean seleccionada = t != null;
        btnAlimentar.setEnabled(seleccionada);
        btnJugar.setEnabled(seleccionada);
        btnDormir.setEnabled(seleccionada);
        btnDespertar.setEnabled(seleccionada);
        btnEspecial1.setEnabled(seleccionada);
        btnEspecial2.setEnabled(seleccionada);
        if (!seleccionada) {
            lblNombre.setText("Selecciona o crea una mascota");
            lblEstado.setText("Estado: -");
            lblDormir.setText("Durmiendo: -");
            lblDetalles.setText("Detalles: -");
            barHambre.setValue(0);
            barEnergia.setValue(0);
            barFelicidad.setValue(0);
            return;
        }
        lblNombre.setText(t.toString());
        lblEstado.setText("Estado: " + t.obtenerEstadoAnimo());
        lblDormir.setText("Durmiendo: " + (t.estaDurmiendo() ? "Sí" : "No"));
        lblDetalles.setText("Detalles: " + t.obtenerDetallesEspecificos());
        barHambre.setValue(t.getHambre());
        barEnergia.setValue(t.getEnergia());
        barFelicidad.setValue(t.getFelicidad());
        btnEspecial1.setText(t.nombreAccion1());
        btnEspecial2.setText(t.nombreAccion2());
    }
}
