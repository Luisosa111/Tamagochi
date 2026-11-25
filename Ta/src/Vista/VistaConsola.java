package Vista;
import java.util.Scanner;
import java.util.List;
import Modelo.Tamagotchi;

public class VistaConsola {
    private final Scanner scanner = new Scanner(System.in);
    public void mostrarBannerInicio() { mostrarMensaje("=== TAMAGOTCHI's ==="); }

    public void mostrarMensaje(String texto) { System.out.println(texto); }
    public String leerTexto(String pregunta) { System.out.print(pregunta + " "); return scanner.nextLine(); }
    public int leerEntero(String pregunta) {
        while (true) { try { return Integer.parseInt(leerTexto(pregunta)); } catch (NumberFormatException e) { mostrarMensaje("Introduce un número."); } }
    }

    public int mostrarMenuPrincipal() {
        return leerEntero("\n" +
                "1. Crear mascota\n" +
                "2. Gestionar mascota\n" +
                "3. Resumen refugio\n" +
                "4. Avanzar hora\n" +
                "5. Crear especie\n" +
                "6. Salir\n" +
                "Opción:"); }

    public void mostrarMascotas(List<Tamagotchi> mascotas) { for (int i = 0; i < mascotas.size(); i++) mostrarMensaje((i+1) + ". " + mascotas.get(i)); }
}
