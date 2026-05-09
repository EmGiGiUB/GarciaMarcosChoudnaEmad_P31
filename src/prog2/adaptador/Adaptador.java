package prog2.adaptador;
import prog2.model.Dades;
import prog2.vista.BiblioException;
import java.io.*;

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
}
