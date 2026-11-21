import Modelo.*;

public class Pruebas {
    private static void verificar(boolean condicion, String mensaje) {
        if (!condicion) throw new AssertionError(mensaje);
    }

    public static void main(String[] args) {
        Tamagotchi[] mascotas = {
            new Perro("Luna"), new Gato("Michi"),
            new Cuyo("Pipo"), new Muneca("Lola")
        };
        for (Tamagotchi t : mascotas) {
            verificar(t.getHambre() == 35 && t.getEnergia() == 65 && t.getFelicidad() == 65, "Inicio");
            t.alimentar(TipoAlimento.CROQUETAS);
            verificar(t.getHambre() == 10 && t.getEnergia() > 65 && t.getFelicidad() > 65, "Alimentar");
            t.jugar(TipoJuego.PELOTA);
            verificar(t.getFelicidad() > 65 && t.getEnergia() < 100, "Jugar");
            t.dormir();
            verificar(t.estaDurmiendo(), "Dormir");
            verificar(t.jugar(TipoJuego.PELOTA).contains("durmiendo"), "No jugar dormido");
            t.avanzarTiempo();
            verificar(t.getEnergia() >= 0 && t.getEnergia() <= 100, "Limitar energía");
            t.despertar();
            verificar(!t.estaDurmiendo(), "Despertar");
            verificar(t.accionEspecial1() != null && t.accionEspecial2() != null, "Acciones únicas");
            for (int i = 0; i < 30; i++) t.avanzarTiempo();
            verificar(t.getHambre() >= 0 && t.getHambre() <= 100, "Hambre en rango");
            verificar(t.getEnergia() >= 0 && t.getEnergia() <= 100, "Energía en rango");
            verificar(t.getFelicidad() >= 0 && t.getFelicidad() <= 100, "Felicidad en rango");
        }
        Perro perro = new Perro("Fido");
        Cuyo cuyo = new Cuyo("Pepe");
        perro.alimentar(TipoAlimento.CROQUETAS);
        cuyo.alimentar(TipoAlimento.CROQUETAS);
        verificar(perro.getEnergia() != cuyo.getEnergia(), "Alimentación propia por especie");
        Perro perro2 = new Perro("Otro");
        Gato gato = new Gato("Otro más");
        perro2.jugar(TipoJuego.PELOTA);
        gato.jugar(TipoJuego.PELOTA);
        verificar(perro2.getFelicidad() != gato.getFelicidad(), "Juegos propios por especie");
        Coleccion coleccion = new Coleccion();
        for (Tamagotchi t : mascotas) coleccion.agregar(t);
        verificar(coleccion.getLista().size() == 4, "Colección");
        System.out.println("PRUEBAS CORRECTAS: 4 especies, alimentación, juego, dormir, despertar, comportamientos, tiempo y rangos.");
    }
}
