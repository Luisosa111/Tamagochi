package Modelo;

public enum TipoAlimento {
    CROQUETAS("Croquetas", 25, 6, 3),
    FRUTA("Fruta", 18, 8, 5),
    VERDURAS("Verduras", 22, 5, 4);

    private final String nombre;
    private final int reduccionHambre;
    private final int energiaBase;
    private final int felicidadBase;

    TipoAlimento(String nombre, int reduccionHambre, int energiaBase, int felicidadBase) {
        this.nombre = nombre;
        this.reduccionHambre = reduccionHambre;
        this.energiaBase = energiaBase;
        this.felicidadBase = felicidadBase;
    }

    public String getNombre() { return nombre; }
    public int getReduccionHambre() { return reduccionHambre; }
    public int getEnergiaBase() { return energiaBase; }
    public int getFelicidadBase() { return felicidadBase; }

    @Override
    public String toString() { return nombre; }
}
