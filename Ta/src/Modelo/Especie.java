package Modelo;

public class Especie {

    private final String nombre;
    private final int energiaAlimento;
    private final int felicidadJuego;
    private final String comportamiento;

    public Especie(String nombre, int energiaAlimento,
                   int felicidadJuego, String comportamiento) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre requerido");
        }

        this.nombre = nombre.trim();
        this.energiaAlimento = energiaAlimento;
        this.felicidadJuego = felicidadJuego;
        this.comportamiento = comportamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEnergiaAlimento() {
        return energiaAlimento;
    }

    public int getFelicidadJuego() {
        return felicidadJuego;
    }

    public String getComportamiento() {
        return comportamiento;
    }
}