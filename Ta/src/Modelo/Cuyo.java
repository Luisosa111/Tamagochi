package Modelo;

public class Cuyo extends Tamagotchi {

    public Cuyo(String nombre) {
        super(nombre); }

    public String getTipo() {
        return "Cuyo"; }

    protected int energiaAlimento(Alimento alimento) {
        return alimento == Alimento.VERDURAS ? 20 : 10; }
    protected int felicidadJuego(TipoJuego juego) {
        return juego == TipoJuego.CARRERA ? 25 : 15; }
    protected String comportamiento() {
        return "cuy cuy cuy y roe madera."; }
}
