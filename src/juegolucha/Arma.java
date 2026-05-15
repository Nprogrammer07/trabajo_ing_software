package juegolucha;

/**
 * Clase abstracta para todas las armas.
 * Implementa Cloneable para el patron Prototype.
 */
public abstract class Arma implements Cloneable {

    protected String nombre;
    protected int dano;

    protected Arma(String nombre, int dano) {
        this.nombre = nombre;
        this.dano = dano;
    }

    public String getNombre() { return nombre; }
    public int getDano() { return dano; }

    @Override
    public Arma clone() {
        try {
            return (Arma) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return nombre + " (+" + dano + " dano)";
    }
}
