package Modelo;

public class Perro extends Tamagotchi {
    private int nivelLadridos;
    private int vueltasCola;

    public Perro(String nombre) { super(nombre); }

    @Override
    protected int energiaExtra(TipoAlimento alimento) {
        return switch (alimento) {
            case CROQUETAS -> 14;
            case FRUTA -> 3;
            case VERDURAS -> 1;
        };
    }

    @Override
    protected int felicidadExtra(TipoJuego juego) {
        return switch (juego) {
            case PELOTA -> 10;
            case ESCONDIDAS -> 3;
            case CARRERA -> 6;
        };
    }

    @Override
    protected int gastoExtra(TipoJuego juego) {
        return juego == TipoJuego.CARRERA ? 5 : 2;
    }

    @Override
    public String nombreAccion1() { return "Ladrar"; }
    @Override
    public String nombreAccion2() { return "Perseguir su cola"; }

    @Override
    public String accionEspecial1() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        nivelLadridos++;
        cambiarFelicidad(2);
        return getNombre() + ": ¡Guau, guau! (Ladridos: " + nivelLadridos + ")";
    }

    @Override
    public String accionEspecial2() {
        if (estaDurmiendo()) return getNombre() + " está durmiendo.";
        vueltasCola++;
        cambiarFelicidad(5);
        cambiarEnergia(-5);
        return getNombre() + " persigue su cola. (Vueltas: " + vueltasCola + ")";
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Ladridos: " + nivelLadridos + " | Vueltas persiguiendo cola: " + vueltasCola;
    }
}
