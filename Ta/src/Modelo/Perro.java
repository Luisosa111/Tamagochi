package Modelo;

public class Perro extends Tamagotchi {

    public Perro(String nombre) {
        super(nombre); }
    public String getTipo() {
        return "Perro"; }
    protected int energiaAlimento(Alimento alimento) {
        return alimento == Alimento.CROQUETAS ? 20 : 10; }
    protected int felicidadJuego(TipoJuego juego) {
        return juego == TipoJuego.PELOTA ? 25 : 15; }
    protected String comportamiento() {
        return "Ladra y persigue su cola."; }
}
