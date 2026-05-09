package prog2.model;

import org.junit.jupiter.api.Test;
import prog2.vista.BiblioException;
import static org.junit.jupiter.api.Assertions.*;

class LlistaExemplarsTest {

    @Test
    void contains() throws BiblioException {
        LlistaExemplars llista = new LlistaExemplars();
        Exemplar ex = new Exemplar("ID1", "El Quijote", "Cervantes", true);

        assertFalse(llista.contains("ID1"));

        llista.afegir(ex);
        assertTrue(llista.contains("ID1"));

        assertFalse(llista.contains("ID2"));
    }

    @Test
    void afegir() throws BiblioException {
        LlistaExemplars llista = new LlistaExemplars();
        Exemplar ex1 = new Exemplar("ID1", "El Quijote", "Cervantes", true);
        Exemplar ex2 = new Exemplar("ID1", "Otro Libro", "Otro Autor", false);

        assertDoesNotThrow(() -> llista.afegir(ex1));

        BiblioException exception = assertThrows(BiblioException.class, () -> {
            llista.afegir(ex2);
        });

        assertEquals("Ya existe un ejemplar con el mismo identificador.", exception.getMessage());
    }
}