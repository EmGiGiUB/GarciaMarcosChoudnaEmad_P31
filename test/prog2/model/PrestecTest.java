package prog2.model;

import org.junit.jupiter.api.Test;
import prog2.vista.BiblioException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class PrestecTest {

    private Prestec crearPrestec(Exemplar ex, Usuari us, Date data, long durada) {
        return new Prestec(ex, us, data) {
            @Override public String tipusPrestec() { return "PrestecTest"; }
            @Override public long duradaPrestec() { return durada; }
        };
    }

    private Exemplar ex = new Exemplar("1", "Titol", "Autor", true);
    private Usuari us = new Usuari("email", "nom", "adreca") {};
    private Date now = new Date();

    @Test
    void setExemplar() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        Exemplar ex2 = new Exemplar("2", "T2", "A2", false);
        p.setExemplar(ex2);
        assertEquals(ex2, p.getExemplar());
    }

    @Test
    void getExemplar() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        assertEquals(ex, p.getExemplar());
    }

    @Test
    void setUsuari() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        Usuari us2 = new Usuari("e2", "n2", "a2") {};
        p.setUsuari(us2);
        assertEquals(us2, p.getUsuari());
    }

    @Test
    void getUsuari() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        assertEquals(us, p.getUsuari());
    }

    @Test
    void setDataCreacio() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        Date novaData = new Date(100000);
        p.setDataCreacio(novaData);
        assertEquals(novaData, p.getDataCreacio());
    }

    @Test
    void getDataCreacio() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        assertEquals(now, p.getDataCreacio());
    }

    @Test
    void setDataLimitRetorn() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        Date limit = new Date(now.getTime() + 5000);
        p.setDataLimitRetorn(limit);
        assertEquals(limit, p.getDataLimitRetorn());
    }

    @Test
    void getDataLimitRetorn() {
        long durada = 10000;
        Prestec p = crearPrestec(ex, us, now, durada);
        assertEquals(now.getTime() + durada, p.getDataLimitRetorn().getTime());
    }

    @Test
    void tipusPrestec() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        assertEquals("PrestecTest", p.tipusPrestec());
    }

    @Test
    void setRetornat() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        p.setRetornat(true);
        assertTrue(p.getRetornat());
    }

    @Test
    void getRetornat() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        assertFalse(p.getRetornat()); // false por defecto
    }

    @Test
    void retorna() {
        Prestec p = crearPrestec(ex, us, now, 1000);

        assertDoesNotThrow(p::retorna);
        assertTrue(p.getRetornat());

        assertThrows(BiblioException.class, p::retorna);
    }

    @Test
    void duradaPrestec() {
        Prestec p = crearPrestec(ex, us, now, 5555);
        assertEquals(5555, p.duradaPrestec());
    }

    @Test
    void prestecEndarrerit() {
        Date haceUnaHora = new Date(System.currentTimeMillis() - 3600000);
        Prestec pEndarrerit = crearPrestec(ex, us, haceUnaHora, 1000);
        assertTrue(pEndarrerit.prestecEndarrerit());

        Prestec pNormal = crearPrestec(ex, us, new Date(), 3600000);
        assertFalse(pNormal.prestecEndarrerit());
    }

    @Test
    void testToString() {
        Prestec p = crearPrestec(ex, us, now, 1000);
        String text = p.toString();
        assertTrue(text.contains("Tipus = PrestecTest"));
        assertTrue(text.contains("Retornat = false"));
    }
}