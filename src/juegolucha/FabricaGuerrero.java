package juegolucha;

public class FabricaGuerrero implements FabricaPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Guerrero(nombre, crearArmaCompatible());
    }
    @Override
    public Arma crearArmaCompatible() {
        return ArmaFactory.crear(ArmaFactory.TipoArma.ESPADA);
    }
}
