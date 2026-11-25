package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Coleccion {

    private final ArrayList<Tamagotchi> mascotas = new ArrayList<>();

    public void agregar(Tamagotchi mascota) { mascotas.add(mascota); }

    public List<Tamagotchi> getMascotas() { return List.copyOf(mascotas); }
    private final ArrayList<Especie> especiePeronalizado = new ArrayList<>();
    // Registra una nueva especie.
    public void agregarEspecie(Especie especie) {
        especiePeronalizado.add(especie);
    }

    // Devuelve las especies registradas.
    public List<Especie> getEspeciesPersonalizadas() {
        return List.copyOf(especiePeronalizado);
    }

    // Crea una mascota de una especie personalizada.
    public Tamagotchi crearPersonalizada(int indice, String nombre) {
        if (indice < 0 || indice >= especiePeronalizado.size()) {
            throw new IllegalArgumentException("Especie inválida");
        }

        return new TamagotchiPersonalizado(
                nombre, especiePeronalizado.get(indice)
        );
    }
    public void avanzarHora() { for (Tamagotchi mascota : mascotas) mascota.avanzarHora(); }

    public static Tamagotchi crear(int tipo, String nombre) {
        switch (tipo) {
            case 1: return new Perro(nombre);
            case 2: return new Gato(nombre);
            case 3: return new Cuyo(nombre);
            case 4: return new Muneca(nombre);
            default: throw new IllegalArgumentException("Tipo inválido");
        }
    }
}
