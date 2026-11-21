package Modelo;

public class Gato extends Tamagotchi {
    private int maullidos;
    private boolean ronroneando;

    public Gato(String nombre) { super(nombre); }

    @Override
    protected int energiaExtra(TipoAlimento alimento) {
        return switch (alimento) {
            case CROQUETAS -> 12;
            case FRUTA -> 1;
            case VERDURAS -> 0;
        };
    }

    @Override
    protected int felicidadExtra(TipoJuego juego) {
        return switch (juego) {
            case PELOTA -> 9;
            case ESCONDIDAS -> 8;
            case CARRERA -> 1;
        };
    }

    @Override
    protected int gastoExtra(TipoJuego juego) {
        return juego == TipoJuego.CARRERA ? 6 : 0;
    }

    @Override
    public String nombreAccion1() { return "Maullar"; }
    @Override
    public String nombreAccion2() { return "Ronronear"; }

    @Override
    public String accionEspecial1() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        maullidos++;
        ronroneando = false;
        return getNombre() + ": ¡Miau! (Maullidos: " + maullidos + ")";
    }

    @Override
    public String accionEspecial2() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        ronroneando = true;
        cambiarFelicidad(6);
        return getNombre() + " está ronroneando: prrr...";
    }

    @Override
    public String dormir() {
        if (estaDurmiendo()) return getNombre() + " ya está durmiendo enroscado.";
        super.dormir();
        ronroneando = false;
        return getNombre() + " se hace bolita y duerme enroscado.";
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Maullidos: " + maullidos + " | Ronroneando: " + (ronroneando ? "Sí" : "No");
    }
}
