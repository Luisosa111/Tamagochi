package Modelo;

public abstract class Tamagotchi implements TamagotchiActions {
    private final String nombre;
    private int hambre;
    private int energia;
    private int felicidad;
    private boolean durmiendo;

    public Tamagotchi(String nombre) {
        this.nombre = (nombre == null || nombre.isBlank()) ? "SinNombre" : nombre.trim();
        this.hambre = 35;
        this.energia = 65;
        this.felicidad = 65;
        this.durmiendo = false;
    }

    public String getNombre() { return nombre; }
    public int getHambre() { return hambre; }
    public int getEnergia() { return energia; }
    public int getFelicidad() { return felicidad; }
    public boolean estaDurmiendo() { return durmiendo; }
    public String getEspecie() { return getClass().getSimpleName(); }

    // Protegidos: las subclases cambian estadísticas sin acceder a atributos privados.
    protected final void cambiarHambre(int cantidad) { hambre = limitar(hambre + cantidad); }
    protected final void cambiarEnergia(int cantidad) { energia = limitar(energia + cantidad); }
    protected final void cambiarFelicidad(int cantidad) { felicidad = limitar(felicidad + cantidad); }
    protected final int limitar(int valor) { return Math.max(0, Math.min(100, valor)); }

    // Cada especie define cómo le afecta cada alimento y cada juego.
    protected abstract int energiaExtra(TipoAlimento alimento);
    protected abstract int felicidadExtra(TipoJuego juego);
    protected abstract int gastoExtra(TipoJuego juego);

    @Override
    public final String alimentar(TipoAlimento alimento) {
        if (durmiendo) return nombre + " está durmiendo. Despiértalo primero.";
        if (alimento == null) return "Selecciona un alimento.";
        cambiarHambre(-alimento.getReduccionHambre());
        cambiarEnergia(alimento.getEnergiaBase() + energiaExtra(alimento));
        cambiarFelicidad(alimento.getFelicidadBase());
        return nombre + " comió " + alimento.getNombre() + ".";
    }

    @Override
    public final String jugar(TipoJuego juego) {
        if (durmiendo) return nombre + " está durmiendo. Despiértalo primero.";
        if (juego == null) return "Selecciona un juego.";
        if (energia < 10) return nombre + " está muy cansado para jugar.";
        cambiarFelicidad(juego.getAumentoFelicidad() + felicidadExtra(juego));
        cambiarEnergia(-(juego.getGastoEnergia() + gastoExtra(juego)));
        cambiarHambre(8);
        return nombre + " jugó a " + juego.getNombre() + ".";
    }

    @Override
    public String dormir() {
        if (durmiendo) return nombre + " ya está durmiendo.";
        durmiendo = true;
        cambiarEnergia(30);
        return nombre + " se durmió y recuperó energía.";
    }

    @Override
    public String despertar() {
        if (!durmiendo) return nombre + " ya está despierto.";
        durmiendo = false;
        return nombre + " despertó.";
    }

    public void avanzarTiempo() {
        cambiarHambre(12);
        if (durmiendo) {
            cambiarEnergia(20);
        } else {
            cambiarEnergia(-10);
            cambiarFelicidad(-6);
        }
        if (hambre >= 75) cambiarFelicidad(-6);
    }

    public String obtenerEstadoAnimo() {
        if (hambre >= 80 || energia <= 20 || felicidad <= 20) return "Está mal: atención urgente";
        if (hambre >= 60 || energia <= 40 || felicidad <= 40) return "Necesita atención";
        return "Se siente bien";
    }

    public abstract String nombreAccion1();
    public abstract String nombreAccion2();
    public abstract String accionEspecial1();
    public abstract String accionEspecial2();
    public abstract String obtenerDetallesEspecificos();

    @Override
    public String toString() { return nombre + " (" + getEspecie() + ")"; }
}
