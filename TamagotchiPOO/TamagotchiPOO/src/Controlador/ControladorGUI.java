package Controlador;

import Modelo.*;
import Vista.VistaGUI;

public class ControladorGUI {
    private final Coleccion coleccion = new Coleccion();
    private final VistaGUI vista;

    public ControladorGUI(VistaGUI vista) {
        this.vista = vista;
    }

    public void iniciar() {
        vista.btnCrear.addActionListener(e -> crearMascota());
        vista.btnAlimentar.addActionListener(e -> alimentar());
        vista.btnJugar.addActionListener(e -> jugar());
        vista.btnDormir.addActionListener(e -> actuarDormir());
        vista.btnDespertar.addActionListener(e -> actuarDespertar());
        vista.btnEspecial1.addActionListener(e -> accionEspecial1());
        vista.btnEspecial2.addActionListener(e -> accionEspecial2());
        vista.btnTiempo.addActionListener(e -> avanzarTiempo());
        vista.comboMascotas.addActionListener(e -> actualizar());
        vista.setVisible(true);
    }

    private void crearMascota() {
        String especie = vista.seleccionarEspecie();
        if (especie == null) return;
        String nombre = vista.pedirNombre();
        if (nombre == null) return;
        Tamagotchi mascota = switch (especie) {
            case "Perro" -> new Perro(nombre);
            case "Gato" -> new Gato(nombre);
            case "Cuyo" -> new Cuyo(nombre);
            case "Muñeca" -> new Muneca(nombre);
            default -> throw new IllegalArgumentException("Especie inválida");
        };
        coleccion.agregar(mascota);
        vista.agregarMascota(mascota);
        vista.mostrarMensaje("Mascota creada: " + mascota);
        actualizar();
    }

    private void alimentar() {
        Tamagotchi t = vista.mascotaSeleccionada();
        if (t == null) return;
        TipoAlimento alimento = vista.seleccionarAlimento();
        if (alimento != null) registrar(t.alimentar(alimento));
    }

    private void jugar() {
        Tamagotchi t = vista.mascotaSeleccionada();
        if (t == null) return;
        TipoJuego juego = vista.seleccionarJuego();
        if (juego != null) registrar(t.jugar(juego));
    }

    private void actuarDormir() {
        Tamagotchi t = vista.mascotaSeleccionada();
        if (t != null) registrar(t.dormir());
    }

    private void actuarDespertar() {
        Tamagotchi t = vista.mascotaSeleccionada();
        if (t != null) registrar(t.despertar());
    }

    private void accionEspecial1() {
        Tamagotchi t = vista.mascotaSeleccionada();
        if (t != null) registrar(t.accionEspecial1());
    }

    private void accionEspecial2() {
        Tamagotchi t = vista.mascotaSeleccionada();
        if (t != null) registrar(t.accionEspecial2());
    }

    private void avanzarTiempo() {
        if (coleccion.estaVacia()) {
            vista.mostrarMensaje("Primero crea una mascota.");
            return;
        }
        coleccion.avanzarTiempoTodos();
        registrar("Pasó una hora para todas las mascotas.");
    }

    private void registrar(String mensaje) {
        vista.mostrarMensaje(mensaje);
        actualizar();
    }

    private void actualizar() {
        vista.actualizarEstado(vista.mascotaSeleccionada());
    }
}
