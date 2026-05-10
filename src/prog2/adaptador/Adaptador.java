package prog2.adaptador;
import prog2.model.Dades;
import prog2.model.Exemplar;
import prog2.model.Prestec;
import prog2.model.Usuari;
import prog2.vista.BiblioException;
import java.io.*;
import java.util.ArrayList;

public class Adaptador {

    private Dades dades;

    public Adaptador(){
        this.dades = new Dades();
    }

    public void guardaDades(String camiDesti) throws BiblioException {
        File fitxer = new File(camiDesti);

        try (FileOutputStream fout = new FileOutputStream(fitxer);
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
             oos.writeObject(dades);

        } catch (IOException e) {
            throw new BiblioException("Error en guardar las dades: " + e.getMessage());
        }
    }

    public void carregaDades(String camiOrigen) throws BiblioException {
        File fitxer = new File(camiOrigen);

        try (FileInputStream fin = new FileInputStream(fitxer);
             ObjectInputStream ois = new ObjectInputStream(fin)) {

            dades = (Dades) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new BiblioException("Error en carregar les dades: " + e.getMessage());
        }
    }

    public void afegirExemplar (String id, String autor, String titol, boolean prestecLlarg) {
       dades.afegirExemplar(id, autor, titol, prestecLlarg);
    }

    public ArrayList<Exemplar> mostarExemplars () {
        return dades.recuperaExemplars();
    }

    public void afegirUsuari (String email, String nom, String adreca, boolean isProfessor) {
        dades.afegirUsuari(email, nom, adreca, isProfessor);
    }

    public ArrayList<Usuari> mostrarUsuaris () {
        return dades.recuperaUsuaris();
    }

    public ArrayList<Prestec> mostrarPrestecs () {
        return dades.recuperaPrestecs();
    }

    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) {
        dades.afegirPrestec(exemplarPos, usuariPos, esLlarg);
    }

    public void retornarPrestec(int pos) {
        dades.retornarPrestec(pos);
    }
}
