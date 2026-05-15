package juegolucha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PATRON SINGLETON.
 * Instancia unica que registra estadisticas globales de la partida:
 * - dano total infligido por personaje
 * - esquives realizados
 * - historial de turnos
 */
public class GestorJuego {

    private static GestorJuego instancia;

    private final Map<String, Integer> danoTotal = new HashMap<>();
    private final Map<String, Integer> esquives = new HashMap<>();
    private final List<String> historial = new ArrayList<>();
    private int turnos = 0;

    private GestorJuego() {}

    public static synchronized GestorJuego getInstancia() {
        if (instancia == null) instancia = new GestorJuego();
        return instancia;
    }

    public void registrarDano(String atacante, int dano) {
        danoTotal.merge(atacante, dano, Integer::sum);
    }

    public void registrarEsquive(String defensor) {
        esquives.merge(defensor, 1, Integer::sum);
    }

    public void registrarTurno(String descripcion) {
        turnos++;
        historial.add("Turno " + turnos + ": " + descripcion);
    }

    public void mostrarEstadisticas() {
        System.out.println("\n========== ESTADISTICAS FINALES ==========");
        System.out.println("Turnos jugados: " + turnos);
        System.out.println("\nDano total infligido:");
        danoTotal.forEach((k, v) -> System.out.println("  " + k + ": " + v));
        System.out.println("\nEsquives:");
        if (esquives.isEmpty()) System.out.println("  (ninguno)");
        else esquives.forEach((k, v) -> System.out.println("  " + k + ": " + v));
        System.out.println("==========================================\n");
    }

    public void reiniciar() {
        danoTotal.clear();
        esquives.clear();
        historial.clear();
        turnos = 0;
    }
}
