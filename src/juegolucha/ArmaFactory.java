package juegolucha;

/**
 * PATRON FACTORY METHOD.
 * Crea armas a partir de un identificador, sin que el cliente
 * dependa de las clases concretas.
 */
public class ArmaFactory {

    public enum TipoArma { ESPADA, BASTON, ARCO }

    public static Arma crear(TipoArma tipo) {
        switch (tipo) {
            case ESPADA: return new Espada();
            case BASTON: return new Baston();
            case ARCO:   return new Arco();
            default: throw new IllegalArgumentException("Arma desconocida: " + tipo);
        }
    }
}
