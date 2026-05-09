package prog2.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuariTest {

    private Usuari getUsuari() {
        return new Usuari("test@ub.edu", "NomTest", "AdrecaTest") {
            @Override public String tipusUsuari() { return "UsuariBase"; }
            @Override public int getMaxPrestecsNormals() { return 2; }
            @Override public int getMaxPrestecsLlargs() { return 1; }
        };
    }

    @Test
    void setEmail() {
        Usuari u = getUsuari();
        u.setEmail("nou@ub.edu");
        assertEquals("nou@ub.edu", u.getEmail());
    }

    @Test
    void getEmail() {
        Usuari u = getUsuari();
        assertEquals("test@ub.edu", u.getEmail());
    }

    @Test
    void setNom() {
        Usuari u = getUsuari();
        u.setNom("NouNom");
        assertEquals("NouNom", u.getNom());
    }

    @Test
    void getNom() {
        Usuari u = getUsuari();
        assertEquals("NomTest", u.getNom());
    }

    @Test
    void setAdreca() {
        Usuari u = getUsuari();
        u.setAdreca("NovaAdreca");
        assertEquals("NovaAdreca", u.getAdreca());
    }

    @Test
    void getAdreca() {
        Usuari u = getUsuari();
        assertEquals("AdrecaTest", u.getAdreca());
    }

    @Test
    void tipusUsuari() {
        Usuari u = getUsuari();
        assertEquals("UsuariBase", u.tipusUsuari());
    }

    @Test
    void setNumPrestecsNormals() {
        Usuari u = getUsuari();
        u.setNumPrestecsNormals(5);
        assertEquals(5, u.getNumPrestecsNormals());
    }

    @Test
    void getNumPrestecsNormals() {
        Usuari u = getUsuari();
        assertEquals(0, u.getNumPrestecsNormals()); // 0 per defecte
    }

    @Test
    void setNumPrestecsLlargs() {
        Usuari u = getUsuari();
        u.setNumPrestecsLlargs(3);
        assertEquals(3, u.getNumPrestecsLlargs());
    }

    @Test
    void getNumPrestecsLlargs() {
        Usuari u = getUsuari();
        assertEquals(0, u.getNumPrestecsLlargs());
    }

    @Test
    void getMaxPrestecsNormals() {
        Usuari u = getUsuari();
        assertEquals(2, u.getMaxPrestecsNormals());
    }

    @Test
    void getMaxPrestecsLlargs() {
        Usuari u = getUsuari();
        assertEquals(1, u.getMaxPrestecsLlargs());
    }

    @Test
    void testToString() {
        Usuari u = getUsuari();
        String expected = "Tipus = UsuariBase, Email= test@ub.edu, Nom= NomTest, Adreca= AdrecaTest, Num. prestecs normals= 0, Num. prestecs llargs= 0";
        assertEquals(expected, u.toString());
    }
}