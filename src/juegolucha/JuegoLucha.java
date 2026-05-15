package juegolucha;

/**
 * Controla el flujo del combate por turnos.
 */
public class JuegoLucha {

    private final Personaje jugador1;
    private final Personaje jugador2;

    public JuegoLucha(Personaje jugador1, Personaje jugador2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
    }

    public void iniciarPelea() {
        System.out.println("\n=============================================");
        System.out.println(" LA PELEA COMIENZA!");
        System.out.println(" " + jugador1);
        System.out.println(" VS");
        System.out.println(" " + jugador2);
        System.out.println("=============================================\n");

        GestorJuego gestor = GestorJuego.getInstancia();
        int ronda = 1;

        while (jugador1.estaVivo() && jugador2.estaVivo()) {
            System.out.println("--- Ronda " + ronda + " ---");
            turno(jugador1, jugador2);
            if (jugador2.estaVivo()) {
                turno(jugador2, jugador1);
            }
            gestor.registrarTurno(jugador1.getNombre() + " vs " + jugador2.getNombre());
            ronda++;
            System.out.println();
        }

        Personaje ganador = jugador1.estaVivo() ? jugador1 : jugador2;
        Personaje perdedor = jugador1.estaVivo() ? jugador2 : jugador1;
        System.out.println("=============================================");
        System.out.println(" " + ganador.getNombre() + " HA GANADO LA PELEA!");
        System.out.println(" " + perdedor.getNombre() + " ha sido derrotado.");
        System.out.println("=============================================");

        gestor.mostrarEstadisticas();
    }

    private void turno(Personaje atacante, Personaje defensor) {
        System.out.println("Turno de " + atacante.getNombre()
                + " (HP " + atacante.getPuntosDeVida() + "/" + atacante.getPuntosDeVidaMax() + ")");
        atacante.atacar(defensor);
    }
}
