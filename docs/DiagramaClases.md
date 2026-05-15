# Diagrama de Clases - Juego de Lucha (Patrones Creacionales)

## Diagrama Mermaid

```mermaid
classDiagram
    %% ===== Jerarquia Personaje =====
    class Personaje {
        <<abstract>>
        -String nombre
        -int puntosDeVida
        -int puntosDeVidaMax
        -int defensa
        -int esquive
        -Arma arma
        +atacar(Personaje) void
        +recibirDano(int, Personaje) void
        +estaVivo() boolean
        +getNombre() String
        +getPuntosDeVida() int
        +ataqueEspecial()* int
        +clone() Personaje
    }
    class Guerrero
    class Mago
    class Arquero
    Personaje <|-- Guerrero
    Personaje <|-- Mago
    Personaje <|-- Arquero

    %% ===== Jerarquia Arma =====
    class Arma {
        <<abstract>>
        -String nombre
        -int dano
        +getNombre() String
        +getDano() int
        +clone() Arma
    }
    class Espada
    class Baston
    class Arco
    Arma <|-- Espada
    Arma <|-- Baston
    Arma <|-- Arco

    Personaje "1" o-- "1" Arma : usa

    %% ===== Singleton =====
    class GestorJuego {
        <<Singleton>>
        -static GestorJuego instancia
        -Map danoTotal
        -Map esquives
        +getInstancia() GestorJuego
        +registrarDano(String,int) void
        +registrarEsquive(String) void
        +mostrarEstadisticas() void
    }

    %% ===== Factory Method =====
    class ArmaFactory {
        <<Factory>>
        +crear(TipoArma) Arma$
    }
    ArmaFactory ..> Arma : crea

    %% ===== Abstract Factory =====
    class FabricaPersonaje {
        <<interface>>
        +crearPersonaje(String) Personaje
        +crearArmaCompatible() Arma
    }
    class FabricaGuerrero
    class FabricaMago
    class FabricaArquero
    FabricaPersonaje <|.. FabricaGuerrero
    FabricaPersonaje <|.. FabricaMago
    FabricaPersonaje <|.. FabricaArquero
    FabricaGuerrero ..> Guerrero
    FabricaMago ..> Mago
    FabricaArquero ..> Arquero

    %% ===== Builder =====
    class PersonajeBuilder {
        <<Builder>>
        -TipoPersonaje tipo
        -String nombre
        -Arma arma
        +tipo(...) PersonajeBuilder
        +nombre(...) PersonajeBuilder
        +arma(...) PersonajeBuilder
        +build() Personaje
    }
    PersonajeBuilder ..> Personaje : construye

    %% ===== Prototype =====
    class RegistroPrototipos {
        <<Prototype Registry>>
        -Map prototipos
        +registrar(String, Personaje) void
        +obtener(String, String) Personaje
    }
    RegistroPrototipos ..> Personaje : clona

    %% ===== Controlador =====
    class JuegoLucha {
        -Personaje jugador1
        -Personaje jugador2
        +iniciarPelea() void
    }
    JuegoLucha o-- Personaje
    JuegoLucha ..> GestorJuego

    class Main {
        +main(String[]) void$
    }
    Main ..> JuegoLucha
    Main ..> FabricaPersonaje
    Main ..> PersonajeBuilder
    Main ..> RegistroPrototipos
```

## Diagrama ASCII (resumen rapido)

```
                +---------------------+
                |     <<abstract>>    |
                |      Personaje      |<>------> Arma (<<abstract>>)
                +---------------------+              ^
                  ^        ^        ^                |
                  |        |        |        +-------+-------+
              Guerrero   Mago   Arquero    Espada  Baston   Arco

  PATRONES CREACIONALES:
  - Singleton ............ GestorJuego
  - Factory Method ....... ArmaFactory
  - Abstract Factory ..... FabricaPersonaje -> FabricaGuerrero/Mago/Arquero
  - Builder .............. PersonajeBuilder
  - Prototype ............ Personaje.clone() + RegistroPrototipos
```
