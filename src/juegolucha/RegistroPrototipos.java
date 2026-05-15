package juegolucha;

import java.util.HashMap;
import java.util.Map;

/**
 * PATRON PROTOTYPE.
 * Registro de personajes preconfigurados que se pueden clonar
 * para crear nuevos personajes rapidamente.
 */
public class RegistroPrototipos {

    private final Map<String, Personaje> prototipos = new HashMap<>();

    public RegistroPrototipos() {
        // Preconfigurar prototipos clasicos
        prototipos.put("guerrero", new Guerrero("Prototipo-Guerrero", new Espada()));
        prototipos.put("mago",     new Mago("Prototipo-Mago", new Baston()));
        prototipos.put("arquero",  new Arquero("Prototipo-Arquero", new Arco()));
    }

    public void registrar(String clave, Personaje p) {
        prototipos.put(clave.toLowerCase(), p);
    }

    public Personaje obtener(String clave, String nuevoNombre) {
        Personaje base = prototipos.get(clave.toLowerCase());
        if (base == null) throw new IllegalArgumentException("Prototipo no encontrado: " + clave);
        Personaje copia = base.clone();
        copia.setNombre(nuevoNombre);
        return copia;
    }
}
