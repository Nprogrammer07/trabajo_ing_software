package juegolucha;

public class Mago extends Personaje {

    public Mago(String nombre, Arma arma) {
        super(nombre, 90, 2, 15, arma);
    }

    @Override
    public int ataqueEspecial() {
        // Hechizo arcano: 30..50
        int base = RAND.nextInt(21) + 30;
        int bonus = (arma != null) ? arma.getDano() : 0;
        return base + bonus + 5; // bono magico
    }
}
