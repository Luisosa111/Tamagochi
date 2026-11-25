package Modelo;

public class Gato extends Tamagotchi {

    public Gato(String nombre) {
        super(nombre); }

    public String getTipo() {
        return "Gato"; }
    protected int energiaAlimento(Alimento alimento) {
        return alimento == Alimento.CROQUETAS ? 20 : 10; }
    protected int felicidadJuego(TipoJuego juego) {
        return juego == TipoJuego.ESCONDIDAS ? 25 : 15; }
    protected String comportamiento() {
        return "Maúlla y ronronea."; }
}
