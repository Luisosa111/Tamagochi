package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Coleccion {
    private final List<Tamagotchi> mascotas = new ArrayList<>();

    public void agregar(Tamagotchi mascota) {
        if (mascota != null) mascotas.add(mascota);
    }

    public boolean estaVacia() { return mascotas.isEmpty(); }
    public List<Tamagotchi> getLista() { return Collections.unmodifiableList(mascotas); }
    public Tamagotchi obtener(int indice) { return mascotas.get(indice); }

    public void avanzarTiempoTodos() {
        for (Tamagotchi mascota : mascotas) mascota.avanzarTiempo();
    }

    public List<Tamagotchi> ordenarPorFelicidad() {
        List<Tamagotchi> copia = new ArrayList<>(mascotas);
        copia.sort(Comparator.comparingInt(Tamagotchi::getFelicidad).reversed());
        return copia;
    }
}
