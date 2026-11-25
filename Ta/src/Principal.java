import Controlador.Controlador;
import Vista.VistaConsola;

public class Principal {
    public static void main(String[] args) { new Controlador(new VistaConsola()).iniciar(); }
}
