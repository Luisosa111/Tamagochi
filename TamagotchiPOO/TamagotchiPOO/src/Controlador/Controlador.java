package Controlador;

import Modelo.*;
import Vista.VistaConsola;

public class Controlador {
    private final Coleccion coleccion = new Coleccion();
    private final VistaConsola vista;

    public Controlador(VistaConsola vista) { this.vista = vista; }

    public void iniciar() {
        boolean ejecutando = true;
        while (ejecutando) {
            int opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1 -> crearMascota();
                case 2 -> interactuar();
                case 3 -> mostrarMascotas();
                case 4 -> avanzarTiempo();
                case 5 -> {
                    vista.mostrarMensaje("¡Hasta luego!");
                    ejecutando = false;
                }
            }
        }
    }

    private void crearMascota() {
        int especie = vista.seleccionarEspecie();
        String nombre = vista.pedirNombre();
        Tamagotchi mascota = switch (especie) {
            case 1 -> new Perro(nombre);
            case 2 -> new Gato(nombre);
            case 3 -> new Cuyo(nombre);
            case 4 -> new Muneca(nombre);
            default -> throw new IllegalArgumentException("Especie inválida");
        };
        coleccion.agregar(mascota);
        vista.mostrarMensaje("Mascota creada: " + mascota);
    }

    private void interactuar() {
        if (coleccion.estaVacia()) {
            vista.mostrarMensaje("Primero debes crear una mascota.");
            return;
        }
        Tamagotchi mascota = coleccion.obtener(vista.seleccionarMascota(coleccion.getLista()));
        boolean seguir = true;
        while (seguir) {
            int opcion = vista.menuInteraccion(mascota);
            switch (opcion) {
                case 1 -> vista.mostrarMensaje(mascota.alimentar(vista.seleccionarAlimento()));
                case 2 -> vista.mostrarMensaje(mascota.jugar(vista.seleccionarJuego()));
                case 3 -> vista.mostrarMensaje(mascota.dormir());
                case 4 -> vista.mostrarMensaje(mascota.despertar());
                case 5 -> vista.mostrarMensaje(mascota.accionEspecial1());
                case 6 -> vista.mostrarMensaje(mascota.accionEspecial2());
                case 7 -> vista.mostrarEstado(mascota);
                case 8 -> seguir = false;
            }
        }
    }

    private void mostrarMascotas() {
        if (coleccion.estaVacia()) {
            vista.mostrarMensaje("No hay mascotas.");
            return;
        }
        for (Tamagotchi mascota : coleccion.ordenarPorFelicidad()) vista.mostrarEstado(mascota);
    }

    private void avanzarTiempo() {
        if (coleccion.estaVacia()) {
            vista.mostrarMensaje("No hay mascotas.");
            return;
        }
        coleccion.avanzarTiempoTodos();
        vista.mostrarMensaje("Pasó una hora: las necesidades cambiaron.");
    }
}
