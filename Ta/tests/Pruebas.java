import Modelo.*;
// Comprueba las reglas del modelo mediante assert (ejecutar con -ea).
public class Pruebas {
    // Punto de entrada que inicia el programa o ejecuta las pruebas.
    public static void main(String[] args) {
        Tamagotchi m = new Perro("Max");
        assert m.getHambre() == 35;
        m.alimentar(TipoAlimento.CROQUETAS);
        assert m.getHambre() == 10 && m.getEnergia() == 85;
        m.dormir(); assert m.isDormido() && m.getEnergia() == 100;
        m.jugar(TipoJuego.PELOTA); assert m.getEnergia() == 100;
        m.avanzarHora(); assert m.getHambre() == 22;
        m.despertar(); m.jugar(TipoJuego.PELOTA); assert m.getEnergia() == 85;
        Coleccion c = new Coleccion(); c.agregar(m); c.agregar(new Gato("Luna")); c.avanzarHora();
        assert c.getMascotas().size() == 2 && m.getEnergia() == 75;
        System.out.println("Pruebas correctas.");
    }
}
