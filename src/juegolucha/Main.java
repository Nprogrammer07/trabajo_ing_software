package juegolucha;

import java.util.Scanner;

/**
 * Punto de entrada del juego.
 * Ofrece un menu para elegir como crear los personajes,
 * demostrando los distintos patrones creacionales.
 */
public class Main {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   JUEGO DE LUCHA - PATRONES CREACIONALES");
        System.out.println("==========================================");

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nElija el modo de creacion de personajes:");
            System.out.println("  1) Abstract Factory (fabrica por clase)");
            System.out.println("  2) Builder           (personalizado)");
            System.out.println("  3) Prototype         (clonar prototipo)");
            System.out.println("  4) Demo automatica   (CPU vs CPU)");
            System.out.println("  0) Salir");
            System.out.print("Opcion: ");

            String opt = SC.nextLine().trim();
            switch (opt) {
                case "1": modoAbstractFactory(); break;
                case "2": modoBuilder(); break;
                case "3": modoPrototype(); break;
                case "4": demoAutomatica(); break;
                case "0": continuar = false; break;
                default: System.out.println("Opcion invalida."); break;
            }
            if (continuar) {
                GestorJuego.getInstancia().reiniciar();
            }
        }
        System.out.println("Hasta la proxima!");
    }

    private static FabricaPersonaje pedirFabrica(String etiqueta) {
        while (true) {
            System.out.println("Tipo para " + etiqueta + ": 1) Guerrero  2) Mago  3) Arquero");
            System.out.print("Opcion: ");
            String s = SC.nextLine().trim();
            switch (s) {
                case "1": return new FabricaGuerrero();
                case "2": return new FabricaMago();
                case "3": return new FabricaArquero();
                default: System.out.println("Opcion invalida."); break;
            }
        }
    }

    private static String pedirNombre(String etiqueta) {
        System.out.print("Nombre de " + etiqueta + ": ");
        String n = SC.nextLine().trim();
        return n.isEmpty() ? etiqueta : n;
    }

    private static void modoAbstractFactory() {
        FabricaPersonaje f1 = pedirFabrica("Jugador 1");
        String n1 = pedirNombre("Jugador 1");
        FabricaPersonaje f2 = pedirFabrica("Jugador 2");
        String n2 = pedirNombre("Jugador 2");

        Personaje p1 = f1.crearPersonaje(n1);
        Personaje p2 = f2.crearPersonaje(n2);
        new JuegoLucha(p1, p2).iniciarPelea();
    }

    private static void modoBuilder() {
        Personaje p1 = construirConBuilder("Jugador 1");
        Personaje p2 = construirConBuilder("Jugador 2");
        new JuegoLucha(p1, p2).iniciarPelea();
    }

    private static Personaje construirConBuilder(String etiqueta) {
        PersonajeBuilder b = new PersonajeBuilder();
        System.out.println("Construyendo " + etiqueta + " con Builder.");
        System.out.println("Tipo: 1) Guerrero  2) Mago  3) Arquero");
        System.out.print("Opcion: ");
        String t = SC.nextLine().trim();
        switch (t) {
            case "2": b.tipo(PersonajeBuilder.TipoPersonaje.MAGO); break;
            case "3": b.tipo(PersonajeBuilder.TipoPersonaje.ARQUERO); break;
            default:  b.tipo(PersonajeBuilder.TipoPersonaje.GUERRERO); break;
        }
        b.nombre(pedirNombre(etiqueta));
        System.out.println("Arma: 1) Espada  2) Baston  3) Arco  (enter = por defecto)");
        System.out.print("Opcion: ");
        String a = SC.nextLine().trim();
        switch (a) {
            case "1": b.arma(new Espada()); break;
            case "2": b.arma(new Baston()); break;
            case "3": b.arma(new Arco()); break;
            default: break;
        }
        return b.build();
    }

    private static void modoPrototype() {
        RegistroPrototipos registro = new RegistroPrototipos();
        System.out.println("Prototipos disponibles: guerrero, mago, arquero");
        System.out.print("Prototipo para Jugador 1: ");
        String k1 = SC.nextLine().trim().toLowerCase();
        String n1 = pedirNombre("Jugador 1");
        System.out.print("Prototipo para Jugador 2: ");
        String k2 = SC.nextLine().trim().toLowerCase();
        String n2 = pedirNombre("Jugador 2");

        try {
            Personaje p1 = registro.obtener(k1, n1);
            Personaje p2 = registro.obtener(k2, n2);
            new JuegoLucha(p1, p2).iniciarPelea();
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private static void demoAutomatica() {
        System.out.println("\n[Demo] Guerrero(Aragorn) vs Mago(Gandalf) -- creados con Abstract Factory");
        Personaje p1 = new FabricaGuerrero().crearPersonaje("Aragorn");
        Personaje p2 = new FabricaMago().crearPersonaje("Gandalf");
        new JuegoLucha(p1, p2).iniciarPelea();
    }
}
