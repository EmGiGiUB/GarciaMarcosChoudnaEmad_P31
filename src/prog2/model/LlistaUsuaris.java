package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.Iterator;

public class LlistaUsuaris extends Llista<Usuari> implements Serializable {
    public LlistaUsuaris() {
        super();
    }

    public boolean contains (String email) {
        Iterator <Usuari> miIterator = llista.iterator();
        while (miIterator.hasNext()) {
            Usuari miUsuari = miIterator.next();
            if (miUsuari.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void afegir(Usuari usuari) throws BiblioException {
        if (contains(usuari.getEmail())) {
            throw new BiblioException("Ya existe un usuario con el mismo email.");
        }
        super.afegir(usuari);
    }
}
