# Simulador de Tamagotchis - Java y POO

Proyecto con arquitectura **Modelo / Vista / Controlador (MVC)**. Incluye **interfaz de consola** y **ventana gráfica con Swing**, ambas basadas en el mismo modelo. No es necesario instalar JavaFX.

## Requisitos

- JDK 17 o superior (se usa `switch` moderno y `String.isBlank()`).
- IntelliJ IDEA u otro entorno Java.
- Para la versión gráfica, ejecutar en un equipo con escritorio (no en una terminal sin pantalla).

## Estructura

```text
src/
  Modelo/
    TamagotchiActions.java  - interfaz de acciones básicas
    Tamagotchi.java         - clase abstracta con necesidades y tiempo
    Perro.java              - ladridos y perseguir cola
    Gato.java               - maullidos, ronroneos y dormir enroscado
    Cuyo.java               - hace cuy cuy y roe madera
    Muneca.java             - expresa alegría/aburrimiento y llora
    TipoAlimento.java       - enum: croquetas, fruta, verduras
    TipoJuego.java          - enum: pelota, escondidas, carrera
    Coleccion.java          - gestiona varias mascotas
  Vista/
    VistaConsola.java       - menús de texto
    VistaGUI.java           - ventana de Swing
  Controlador/
    Controlador.java        - coordina la versión de consola
    ControladorGUI.java     - coordina botones de la ventana
  Principal.java            - inicia consola
  PrincipalGUI.java         - inicia ventana gráfica
tests/
  Pruebas.java              - verificaciones básicas
```

## Ejecución en IntelliJ

1. Crea un proyecto **Java** nuevo con JDK 17 o superior, **sin JavaFX obligatorio**.
2. Copia los archivos de `src` conservando los directorios `Modelo`, `Vista` y `Controlador`.
3. Marca `src` como *Sources Root* si IntelliJ no lo hace automáticamente.
4. Ejecuta `Principal.main()` para consola **o** `PrincipalGUI.main()` para ventana.
5. Si tu proyecto existente tiene `module-info.java`, añade `requires java.desktop;` para Swing, o elimina `module-info.java` si no lo necesitas.

## Compilar por terminal

En Linux/macOS:
```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name '*.java')
java -cp out Principal
java -cp out PrincipalGUI
```

En Windows con IntelliJ es más sencillo ejecutar directamente las clases principales.

## Principios POO utilizados

- **Abstracción:** `Tamagotchi` representa lo común a cualquier mascota; no se instancia directamente.
- **Encapsulación:** nombre, hambre, energía, felicidad y sueño son privados; se consultan mediante getters y se actualizan por métodos controlados (rango 0-100).
- **Herencia:** `Perro`, `Gato`, `Cuyo` y `Muneca` extienden `Tamagotchi`.
- **Polimorfismo:** una variable `Tamagotchi` puede contener cualquiera de las cuatro especies; cada clase define sus propios valores y comportamientos mediante métodos sobrescritos.
- **Interfaz:** `TamagotchiActions` declara las acciones de alimentar, jugar, dormir y despertar.

## Reglas del simulador

- Las necesidades iniciales son hambre 35, energía 65 y felicidad 65.
- Alimentar reduce hambre y aumenta energía/felicidad; cada especie obtiene energía extra distinta según alimento.
- Jugar aumenta felicidad y consume energía; el efecto varía entre especies y juegos.
- Dormir aporta +30 energía, deja la mascota dormida y, durante cada hora de sueño, recupera +20 energía.
- Hay un botón/acción para despertarla. Mientras duerme no puede comer, jugar ni realizar acciones especiales.
- Al avanzar una hora, hambre aumenta 12; despierta, energía baja 10 y felicidad baja 6; si tiene mucha hambre, su felicidad disminuye adicionalmente.
- Estadísticas en rango de 0 a 100.
- Se muestran estado de ánimo y características propias de cada especie.

**Nota:** Son reglas ficticias de un videojuego, no recomendaciones sobre alimentación real de animales. La muñeca simula alimentación imaginaria.

## Pruebas

```bash
javac -encoding UTF-8 -cp out -d out tests/Pruebas.java
java -cp out Pruebas
```
