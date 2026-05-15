package juegolucha;

public class Guerrero extends Personaje {

    public Guerrero(String nombre, Arma arma) {
        super(nombre, 120, 5, 10, arma);
    }

    @Override
    public int ataqueEspecial() {
        // Golpe brutal: 35..55
        int base = RAND.nextInt(21) + 35;
        int bonus = (arma != null) ? arma.getDano() : 0;
        return base + bonus;
    }
}
