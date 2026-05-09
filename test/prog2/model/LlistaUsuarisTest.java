package prog2.model;

import org.junit.jupiter.api.Test;
import prog2.vista.BiblioException;
import static org.junit.jupiter.api.Assertions.*;

class LlistaUsuarisTest {
    private Usuari crearUsuariTest(String email) {
        return new Usuari(email, "Joan", "Carrer Fals 123") {
            @Override public String tipusUsuari() { return "Test"; }
            @Override public int getMaxPrestecsNormals() { return 2; }
            @Override public int getMaxPrestecsLlargs() { return 1; }
        };
    }

    @Test
    void contains() throws BiblioException {
        LlistaUsuaris llista = new LlistaUsuaris();
        Usuari u = crearUsuariTest("joan@ub.edu");

        assertFalse(llista.contains("joan@ub.edu"));

        llista.afegir(u);
        assertTrue(llista.contains("joan@ub.edu"));
        assertFalse(llista.contains("pere@ub.edu"));
    }

    @Test
    void afegir() {
        LlistaUsuaris llista = new LlistaUsuaris();
        Usuari u1 = crearUsuariTest("joan@ub.edu");
        Usuari u2 = crearUsuariTest("joan@ub.edu");

        assertDoesNotThrow(() -> llista.afegir(u1));

        BiblioException exception = assertThrows(BiblioException.class, () -> {
            llista.afegir(u2);
        });

        assertEquals("Ya existe un usuario con el mismo email.", exception.getMessage());
    }
}