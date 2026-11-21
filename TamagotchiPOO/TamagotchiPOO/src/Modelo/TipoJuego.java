package Modelo;

public enum TipoJuego {
    PELOTA("Pelota", 16, 14),
    ESCONDIDAS("Escondidas", 14, 10),
    CARRERA("Carrera", 18, 20);

    private final String nombre;
    private final int aumentoFelicidad;
    private final int gastoEnergia;

    TipoJuego(String nombre, int aumentoFelicidad, int gastoEnergia) {
        this.nombre = nombre;
        this.aumentoFelicidad = aumentoFelicidad;
        this.gastoEnergia = gastoEnergia;
    }

    public String getNombre() { return nombre; }
    public int getAumentoFelicidad() { return aumentoFelicidad; }
    public int getGastoEnergia() { return gastoEnergia; }

    @Override
    public String toString() { return nombre; }
}
