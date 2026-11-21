package Modelo;

public class Muneca extends Tamagotchi {
    private int llantos;

    public Muneca(String nombre) { super(nombre); }

    @Override
    protected int energiaExtra(TipoAlimento alimento) {
        // Comida imaginaria: la muñeca simula que se alimenta.
        return switch (alimento) {
            case CROQUETAS -> 0;
            case FRUTA -> 5;
            case VERDURAS -> 8;
        };
    }

    @Override
    protected int felicidadExtra(TipoJuego juego) {
        return switch (juego) {
            case PELOTA -> 4;
            case ESCONDIDAS -> 10;
            case CARRERA -> 3;
        };
    }

    @Override
    protected int gastoExtra(TipoJuego juego) {
        return juego == TipoJuego.CARRERA ? 3 : 0;
    }

    @Override
    public String nombreAccion1() { return "Decir cómo se siente"; }
    @Override
    public String nombreAccion2() { return "Llorar"; }

    @Override
    public String accionEspecial1() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        return getFelicidad() < 50
                ? getNombre() + " dice: Estoy aburrida. ¡Juega conmigo!"
                : getNombre() + " dice: ¡Estoy muy feliz!";
    }

    @Override
    public String accionEspecial2() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        llantos++;
        cambiarFelicidad(-5);
        return getNombre() + ": ¡Buaaa! (Llantos: " + llantos + ")";
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Llantos: " + llantos + " | Ánimo: " + (getFelicidad() < 50 ? "Aburrida" : "Feliz");
    }
}
