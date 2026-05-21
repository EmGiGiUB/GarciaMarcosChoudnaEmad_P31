package prog2.adaptador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import prog2.model.Exemplar;
import prog2.model.Prestec;
import prog2.model.Usuari;
import prog2.vista.BiblioException;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AdaptadorTest {

    private Adaptador adaptador;

    @BeforeEach
    void setUp() {
           adaptador = new Adaptador();
    }

    @Test
    void afegirExemplar() {
        adaptador.afegirExemplar("101", "Cervantes", "Quijote", true);
        ArrayList<Exemplar> llista = adaptador.mostrarExemplars();

        assertEquals(1, llista.size(), "La llista hauria de tenir 1 exemplar");
        assertEquals("Cervantes", llista.get(0).getTitol());
    }

    @Test
    void afegirUsuari() {
        adaptador.afegirUsuari("joan@ub.edu", "Joan", "Carrer A", true);
        ArrayList<Usuari> llista = adaptador.mostrarUsuaris();

        assertEquals(1, llista.size());
        assertEquals("Joan", llista.get(0).getNom());
    }

    @Test
    void afegirPrestec(@TempDir Path tempDir) throws BiblioException {
        // Preparem dades: necessitem un usuari i un exemplar per fer un préstec
        adaptador.afegirExemplar("1", "Autor", "Llibre", false);
        adaptador.afegirUsuari("mail@test.com", "Usuari", "Adreça", false);

        assertDoesNotThrow(() -> adaptador.afegirPrestec(0, 0, false));

        ArrayList<Prestec> prestecs = adaptador.mostrarPrestecs();
        assertEquals(1, prestecs.size(), "S'hauria d'haver creat un préstec");
    }

    @Test
    void retornarPrestec() throws BiblioException {
        adaptador.afegirExemplar("1", "Autor", "Llibre", false);
        adaptador.afegirUsuari("u@u.com", "U", "A", false);
        adaptador.afegirPrestec(0, 0, false);

        assertDoesNotThrow(() -> adaptador.retornarPrestec(0));
    }

    @Test
    void guardaICarregaDades(@TempDir Path tempDir) throws BiblioException {
        adaptador.afegirExemplar("EXT-01", "El Hobbit", "Tolkien", true);

        File fitxerTemp = tempDir.resolve("dades_test.dat").toFile();
        String cami = fitxerTemp.getAbsolutePath();

        adaptador.guardaDades(cami);
        assertTrue(fitxerTemp.exists(), "El fitxer s'hauria d'haver creat");

        Adaptador nouAdaptador = new Adaptador();
        nouAdaptador.carregaDades(cami);

        ArrayList<Exemplar> exemplarsRecuperats = nouAdaptador.mostrarExemplars();
        assertEquals(1, exemplarsRecuperats.size());
        assertEquals("El Hobbit", exemplarsRecuperats.getFirst().getTitol());
    }

    @Test
    void mostarExemplars() {
        assertTrue(adaptador.mostrarExemplars().isEmpty(), "Al principi ha d'estar buida");
        adaptador.afegirExemplar("ID", "A", "T", false);
        assertFalse(adaptador.mostrarExemplars().isEmpty());
    }

    @Test
    void mostrarUsuaris() {
        assertTrue(adaptador.mostrarUsuaris().isEmpty());
        adaptador.afegirUsuari("e", "n", "a", true);
        assertEquals(1, adaptador.mostrarUsuaris().size());
    }

    @Test
    void mostrarPrestecs() {
        assertTrue(adaptador.mostrarPrestecs().isEmpty());
    }
}