package juegolucha;

import java.util.Random;

/**
 * Clase abstracta base para todos los personajes del juego.
 * Implementa Cloneable para soportar el patron Prototype.
 */
public abstract class Personaje implements Cloneable {

    protected String nombre;
    protected int puntosDeVida;
    protected int puntosDeVidaMax;
    protected int defensa;       // reduccion de dano
    protected int esquive;       // probabilidad 0-100 de esquivar
    protected Arma arma;
    protected static final Random RAND = new Random();

    protected Personaje(String nombre, int hp, int defensa, int esquive, Arma arma) {
        this.nombre = nombre;
        this.puntosDeVida = hp;
        this.puntosDeVidaMax = hp;
        this.defensa = defensa;
        this.esquive = esquive;
        this.arma = arma;
    }

    /** Cada subclase define su ataque especial. */
    public abstract int ataqueEspecial();

    /** Realiza un ataque normal con el arma equipada. */
    public void atacar(Personaje oponente) {
        int dano;
        // 25% de probabilidad de usar ataque especial
        if (RAND.nextInt(100) < 25) {
            dano = ataqueEspecial();
            System.out.println("  >>> " + nombre + " usa ATAQUE ESPECIAL!");
        } else {
            int base = RAND.nextInt(21) + 10; // 10..30
            int bonus = (arma != null) ? arma.getDano() : 0;
            dano = base + bonus;
        }
        oponente.recibirDano(dano, this);
    }

    public void recibirDano(int dano, Personaje atacante) {
        if (RAND.nextInt(100) < esquive) {
            System.out.println("  " + nombre + " ESQUIVA el ataque de " + atacante.getNombre() + "!");
            GestorJuego.getInstancia().registrarEsquive(nombre);
            return;
        }
        int real = Math.max(1, dano - defensa);
        puntosDeVida -= real;
        if (puntosDeVida < 0) puntosDeVida = 0;
        System.out.println("  " + atacante.getNombre() + " causa " + real + " de dano a " + nombre
                + " (HP: " + puntosDeVida + "/" + puntosDeVidaMax + ")");
        GestorJuego.getInstancia().registrarDano(atacante.getNombre(), real);
    }

    public boolean estaVivo() { return puntosDeVida > 0; }
    public String getNombre() { return nombre; }
    public int getPuntosDeVida() { return puntosDeVida; }
    public int getPuntosDeVidaMax() { return puntosDeVidaMax; }
    public Arma getArma() { return arma; }
    public void setArma(Arma arma) { this.arma = arma; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** Prototype: permite clonar un personaje preconfigurado. */
    @Override
    public Personaje clone() {
        try {
            Personaje copia = (Personaje) super.clone();
            if (this.arma != null) {
                copia.arma = this.arma.clone();
            }
            // resetear HP al maximo en la copia
            copia.puntosDeVida = copia.puntosDeVidaMax;
            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    @Override
    public String toString() {
        return String.format("%s [%s] HP=%d/%d DEF=%d ESQ=%d%% Arma=%s",
                nombre, getClass().getSimpleName(), puntosDeVida, puntosDeVidaMax,
                defensa, esquive, (arma != null ? arma.getNombre() : "ninguna"));
    }
}
