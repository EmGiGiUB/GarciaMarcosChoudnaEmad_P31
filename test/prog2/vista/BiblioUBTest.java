package prog2.vista;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BiblioUBTest {

    @Test
    void gestioBiblioUB() {
        BiblioUB biblio = new BiblioUB();
        assertNotNull(biblio, "La instància de BiblioUB no hauria de ser nul·la");
    }
}