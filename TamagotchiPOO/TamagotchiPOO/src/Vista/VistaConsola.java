package Vista;

import Modelo.Tamagotchi;
import Modelo.TipoAlimento;
import Modelo.TipoJuego;
import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMensaje(String mensaje) { System.out.println(mensaje); }

    public int mostrarMenuPrincipal() {
        System.out.println("\n=== TAMAGOTCHI VIRTUAL ===");
        System.out.println("1. Crear mascota");
        System.out.println("2. Interactuar con mascota");
        System.out.println("3. Mostrar todas las mascotas");
        System.out.println("4. Avanzar una hora");
        System.out.println("5. Salir");
        return leerEntero("Opción: ", 1, 5);
    }

    public int seleccionarEspecie() {
        System.out.println("\n1. Perro  2. Gato  3. Cuyo  4. Muñeca");
        return leerEntero("Especie: ", 1, 4);
    }

    public String pedirNombre() {
        System.out.print("Nombre: ");
        return scanner.nextLine().trim();
    }

    public int seleccionarMascota(List<Tamagotchi> mascotas) {
        System.out.println("\n=== Tus mascotas ===");
        for (int i = 0; i < mascotas.size(); i++) {
            System.out.println((i + 1) + ". " + mascotas.get(i));
        }
        return leerEntero("Selecciona una mascota: ", 1, mascotas.size()) - 1;
    }

    public int menuInteraccion(Tamagotchi mascota) {
        mostrarEstado(mascota);
        System.out.println("\n1. Alimentar  2. Jugar  3. Dormir  4. Despertar");
        System.out.println("5. " + mascota.nombreAccion1());
        System.out.println("6. " + mascota.nombreAccion2());
        System.out.println("7. Ver estado  8. Regresar");
        return leerEntero("Acción: ", 1, 8);
    }

    public TipoAlimento seleccionarAlimento() {
        TipoAlimento[] alimentos = TipoAlimento.values();
        for (int i = 0; i < alimentos.length; i++) {
            System.out.println((i + 1) + ". " + alimentos[i].getNombre());
        }
        return alimentos[leerEntero("Alimento: ", 1, alimentos.length) - 1];
    }

    public TipoJuego seleccionarJuego() {
        TipoJuego[] juegos = TipoJuego.values();
        for (int i = 0; i < juegos.length; i++) {
            System.out.println((i + 1) + ". " + juegos[i].getNombre());
        }
        return juegos[leerEntero("Juego: ", 1, juegos.length) - 1];
    }

    public void mostrarEstado(Tamagotchi t) {
        System.out.println("\n--- " + t + " ---");
        System.out.println("Hambre: " + t.getHambre() + "/100");
        System.out.println("Energía: " + t.getEnergia() + "/100");
        System.out.println("Felicidad: " + t.getFelicidad() + "/100");
        System.out.println("Durmiendo: " + (t.estaDurmiendo() ? "Sí" : "No"));
        System.out.println("Estado: " + t.obtenerEstadoAnimo());
        System.out.println("Detalles: " + t.obtenerDetallesEspecificos());
    }

    private int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            try {
                int valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max) return valor;
            } catch (NumberFormatException ignored) {
                // Se vuelve a solicitar un número válido.
            }
            System.out.println("Ingresa un número entre " + min + " y " + max + ".");
        }
    }
}
