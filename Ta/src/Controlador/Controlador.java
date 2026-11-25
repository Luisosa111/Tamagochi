
package Controlador;

import Modelo.*;
import Vista.VistaConsola;
import java.util.List;

public class Controlador {

    private final Coleccion coleccion = new Coleccion();
    private final VistaConsola vista;

    public Controlador(VistaConsola vista) {
        this.vista = vista;
    }

    public void iniciar() {
        vista.mostrarBannerInicio();
        boolean ejecutando = true;

        while (ejecutando) {
            switch (vista.mostrarMenuPrincipal()) {
                case 1:
                    crearMascota();
                    break;
                case 2:
                    gestionarMascota();
                    break;
                case 3:
                    mostrarResumenRefugio();
                    break;
                case 4:
                    coleccion.avanzarHora();
                    vista.mostrarMensaje("Pasó una hora.");
                    break;
                case 5:
                    agregarEspecie();
                    break;
                case 6:
                    ejecutando = false;
                    break;
                default:
                    vista.mostrarMensaje("Opción inválida.");
                    break;
            }
        }
    }

    // Permite registrar una especie personalizada.
    private void agregarEspecie() {
        String nombre = vista.leerTexto("Nombre de la especie:");

        int energia = vista.leerEntero(
                "Energía que recupera al alimentarse:"
        );

        int felicidad = vista.leerEntero(
                "Felicidad que obtiene al jugar:"
        );

        String comportamiento = vista.leerTexto(
                "Describe su acción especial:"
        );

        try {
            Especie especie = new Especie(
                    nombre, energia, felicidad, comportamiento
            );

            coleccion.agregarEspecie(especie);

            vista.mostrarMensaje(
                    "Especie " + nombre + " registrada correctamente."
            );

        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    // Permite crear una mascota.
    private void crearMascota() {
        StringBuilder menu = new StringBuilder(
                "1. Perro\n2. Gato\n3. Cuyo\n4. Muñeca\n"
        );

        List<Especie> especies = coleccion.getEspeciesPersonalizadas();

        for (int i = 0; i < especies.size(); i++) {
            menu.append(i + 5)
                    .append(". ")
                    .append(especies.get(i).getNombre())
                    .append("\n");
        }

        int tipo = vista.leerEntero(menu.toString());

        String nombre = vista.leerTexto("Nombre de la mascota:");

        try {
            Tamagotchi mascota;

            if (tipo >= 1 && tipo <= 4) {
                mascota = Coleccion.crear(tipo, nombre);
            } else if (tipo >= 5) {
                mascota = coleccion.crearPersonalizada(tipo - 5, nombre);
            } else {
                vista.mostrarMensaje("Tipo inválido.");
                return;
            }

            coleccion.agregar(mascota);
            vista.mostrarMensaje("Mascota creada correctamente.");

        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    // Muestra los datos de todas las mascotas.
    private void mostrarResumenRefugio() {
        if (coleccion.getMascotas().isEmpty()) {
            vista.mostrarMensaje("No hay mascotas.");
        } else {
            vista.mostrarMascotas(coleccion.getMascotas());
        }
    }

    // Permite seleccionar y gestionar una mascota.
    private void gestionarMascota() {
        List<Tamagotchi> mascotas = coleccion.getMascotas();

        mostrarResumenRefugio();

        if (mascotas.isEmpty()) return;

        int indice = vista.leerEntero("Selecciona mascota:") - 1;

        if (indice < 0 || indice >= mascotas.size()) return;

        Tamagotchi m = mascotas.get(indice);

        while (true) {
            vista.mostrarMensaje(m.toString());

            int op = vista.leerEntero(
                    "1. Alimentar  2. Jugar  3. Dormir  4. Despertar  5. Acción especial  6. Volver"
            );

            if (op == 6) return;

            if (m.isDormido() && (op == 1 || op == 2 || op == 5)) {
                vista.mostrarMensaje("La mascota está dormida.");
                continue;
            }

            switch (op) {
                case 1: {
                    int a = vista.leerEntero(
                            "1. Croquetas  2. Fruta  3. Verduras"
                    );

                    if (a >= 1 && a <= 3) {
                        m.alimentar(Alimento.values()[a - 1]);
                    }
                    break;
                }

                case 2: {
                    int j = vista.leerEntero(
                            "1. Pelota  2. Escondidas  3. Carrera"
                    );

                    if (m.getEnergia() < 15) {
                        vista.mostrarMensaje("Energía insuficiente.");
                    } else if (j >= 1 && j <= 3) {
                        m.jugar(TipoJuego.values()[j - 1]);
                    }
                    break;
                }

                case 3:
                    m.dormir();
                    break;

                case 4:
                    m.despertar();
                    break;

                case 5:
                    vista.mostrarMensaje(m.accionEspecial());
                    break;

                default:
                    vista.mostrarMensaje("Opción inválida.");
                    break;
            }
        }
    }
}
