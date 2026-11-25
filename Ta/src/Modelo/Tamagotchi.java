package Modelo;

public abstract class Tamagotchi implements AccionesTam {

    private final String nombre;
    private int hambre = 35, energia = 65, felicidad = 65;

    private boolean dormido;
    public Tamagotchi(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Ingresa nombre");
        this.nombre = nombre.trim();
    }

    public String getNombre() {
        return nombre; }

    public int getHambre() {
        return hambre; }

    public int getEnergia() {
        return energia; }

    public int getFelicidad() {
        return felicidad; }
    public boolean isDormido() {
        return dormido; }
    private int limitar(int n) {
        return Math.max(0, Math.min(100, n)); }

    protected abstract int energiaAlimento(Alimento alimento);
    protected abstract int felicidadJuego(TipoJuego juego);
    protected abstract String comportamiento();
    public abstract String getTipo();
    public void alimentar(Alimento alimento) {

        if (dormido || alimento == null)
            return;
        hambre = limitar(hambre - 25);
        energia = limitar(energia + energiaAlimento(alimento));
        felicidad = limitar(felicidad + 5);
    }
    public void jugar(TipoJuego juego) {
        if (dormido || juego == null || energia < 15)
            return;
        energia = limitar(energia - 15);
        felicidad = limitar(felicidad + felicidadJuego(juego));
        hambre = limitar(hambre + 5);
    }

//DORMIR,DESPERTAR,AVANZAR HORA

    public void dormir() {
        if (!dormido) { dormido = true; energia = limitar(energia + 30); } }
    public void despertar() {
        dormido = false; }
    public void avanzarHora() {
        hambre = limitar(hambre + 12);
        if (dormido) energia = limitar(energia + 20);
        else { energia = limitar(energia - 10); felicidad = limitar(felicidad - 6); }
        if (hambre >= 80) felicidad = limitar(felicidad - 8);
    }
    public String accionEspecial() {
        return dormido ? "La mascota está dormida." : comportamiento(); }

    public String getEstadoAnimo() {
        if (dormido) return "Dormido";
        if (hambre >= 80) return "Hambriento";
        if (energia <= 20) return "Cansado";
        return felicidad >= 60 ? "Feliz" : "Triste";
    }

    public String toString() {
        return nombre + " (" + getTipo() + ") | Hambre: " + hambre + " | Energía: " + energia + " | Felicidad: " + felicidad + " | " + getEstadoAnimo(); }
}
