import Controlador.ControladorGUI;
import Vista.VistaGUI;
import javax.swing.SwingUtilities;

public class PrincipalGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ControladorGUI(new VistaGUI()).iniciar());
    }
}
