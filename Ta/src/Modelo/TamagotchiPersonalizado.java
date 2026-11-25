package Modelo;

public class TamagotchiPersonalizado extends Tamagotchi {

    private final Especie especie;

    public TamagotchiPersonalizado(String nombre, Especie especie) {
        super(nombre);
        this.especie = especie;
    }

    @Override
    protected int energiaAlimento(Alimento alimento) {
        return especie.getEnergiaAlimento();
    }

    @Override
    protected int felicidadJuego(TipoJuego juego) {
        return especie.getFelicidadJuego();
    }

    @Override
    protected String comportamiento() {
        return especie.getComportamiento();
    }

    @Override
    public String getTipo() {
        return especie.getNombre();
    }
}