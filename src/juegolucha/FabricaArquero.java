package juegolucha;

public class FabricaArquero implements FabricaPersonaje {
    @Override
    public Personaje crearPersonaje(String nombre) {
        return new Arquero(nombre, crearArmaCompatible());
    }
    @Override
    public Arma crearArmaCompatible() {
        return ArmaFactory.crear(ArmaFactory.TipoArma.ARCO);
    }
}
