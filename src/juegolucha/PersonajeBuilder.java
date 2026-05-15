package juegolucha;

/**
 * PATRON BUILDER.
 * Permite construir un personaje paso a paso configurando
 * tipo, nombre y arma de forma fluida.
 */
public class PersonajeBuilder {

    public enum TipoPersonaje { GUERRERO, MAGO, ARQUERO }

    private TipoPersonaje tipo = TipoPersonaje.GUERRERO;
    private String nombre = "Sin Nombre";
    private Arma arma;

    public PersonajeBuilder tipo(TipoPersonaje tipo) { this.tipo = tipo; return this; }
    public PersonajeBuilder nombre(String nombre) { this.nombre = nombre; return this; }
    public PersonajeBuilder arma(Arma arma) { this.arma = arma; return this; }

    public Personaje build() {
        Arma armaFinal = (arma != null) ? arma : armaPorDefecto();
        switch (tipo) {
            case GUERRERO: return new Guerrero(nombre, armaFinal);
            case MAGO:     return new Mago(nombre, armaFinal);
            case ARQUERO:  return new Arquero(nombre, armaFinal);
            default: throw new IllegalStateException();
        }
    }

    private Arma armaPorDefecto() {
        switch (tipo) {
            case MAGO:    return new Baston();
            case ARQUERO: return new Arco();
            default:      return new Espada();
        }
    }
}
