package juegolucha;

/**
 * PATRON ABSTRACT FACTORY.
 * Cada fabrica concreta produce un personaje con su arma compatible,
 * garantizando que la pareja (personaje + arma) sea coherente.
 */
public interface FabricaPersonaje {
    Personaje crearPersonaje(String nombre);
    Arma crearArmaCompatible();
}
