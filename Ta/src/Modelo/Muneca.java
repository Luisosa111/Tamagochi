package Modelo;

public class Muneca extends Tamagotchi {

    public Muneca(String nombre) {
        super(nombre); }
    public String getTipo() {
        return "Muñeca"; }
    protected int energiaAlimento(Alimento alimento) {
        return alimento == Alimento.FRUTA ? 20 : 10; }
    protected int felicidadJuego(TipoJuego juego) {
        return juego == TipoJuego.ESCONDIDAS ? 25 : 15; }
    protected String comportamiento() {
        return " SOY FELIZ"; }
}
