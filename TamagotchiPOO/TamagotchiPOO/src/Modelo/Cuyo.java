package Modelo;

public class Cuyo extends Tamagotchi {
    private int sonidosCuy;
    private int vecesRoeMadera;

    public Cuyo(String nombre) { super(nombre); }

    @Override
    protected int energiaExtra(TipoAlimento alimento) {
        return switch (alimento) {
            case CROQUETAS -> 1;
            case FRUTA -> 7;
            case VERDURAS -> 13;
        };
    }

    @Override
    protected int felicidadExtra(TipoJuego juego) {
        return switch (juego) {
            case PELOTA -> 2;
            case ESCONDIDAS -> 8;
            case CARRERA -> 6;
        };
    }

    @Override
    protected int gastoExtra(TipoJuego juego) {
        return juego == TipoJuego.CARRERA ? 4 : 1;
    }

    @Override
    public String nombreAccion1() { return "Hacer cuy cuy"; }
    @Override
    public String nombreAccion2() { return "Roer madera"; }

    @Override
    public String accionEspecial1() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        sonidosCuy++;
        cambiarFelicidad(3);
        return getNombre() + ": ¡Cuy, cuy! (Sonidos: " + sonidosCuy + ")";
    }

    @Override
    public String accionEspecial2() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        vecesRoeMadera++;
        cambiarFelicidad(4);
        cambiarEnergia(-3);
        return getNombre() + " está royendo madera.";
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Sonidos cuy: " + sonidosCuy + " | Veces que royó madera: " + vecesRoeMadera;
    }
}
