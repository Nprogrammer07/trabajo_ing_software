package juegolucha;

public class FabricaMago implements FabricaPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Mago(nombre, crearArmaCompatible());
    }
    @Override
    public Arma crearArmaCompatible() {
        return ArmaFactory.crear(ArmaFactory.TipoArma.BASTON);
    }
}
