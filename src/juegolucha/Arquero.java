package juegolucha;

public class Arquero extends Personaje {

    public Arquero(String nombre, Arma arma) {
        super(nombre, 100, 3, 25, arma);
    }

    @Override
    public int ataqueEspecial() {
        // Tiro certero: 25..45
        int base = RAND.nextInt(21) + 25;
        int bonus = (arma != null) ? arma.getDano() : 0;
        return base + bonus;
    }
}
