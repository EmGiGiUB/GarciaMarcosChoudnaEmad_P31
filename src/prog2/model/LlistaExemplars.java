package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaExemplars extends Llista<Exemplar> implements Serializable {
    public LlistaExemplars() {
        super();
    }

    public boolean contains (String id) {
        Iterator<Exemplar> miIterador = llista.iterator();
        while (miIterador.hasNext()) {
            Exemplar miExemplar = miIterador.next();
            if (miExemplar.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void afegir(Exemplar exemplar) throws BiblioException {
        if (contains(exemplar.getId())) {
            throw new BiblioException("Ya existe un ejemplar con el mismo identificador.");
        }
        super.afegir(exemplar);
    }
}
