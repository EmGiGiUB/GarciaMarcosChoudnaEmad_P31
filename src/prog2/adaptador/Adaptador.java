package prog2.adaptador;
import prog2.model.Dades;
import prog2.vista.BiblioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Adaptador {

    private Dades dades;

    public void guardaDades(String camiDesti) throws BiblioException{
        String llistaExemplars = dades.recuperaExemplars().toString();
        String llistaUsuaris = dades.recuperaUsuaris().toString();
        String llistaPrestecs = dades.recuperaPrestecs().toString();

        File fitxer = new File(camiDesti);
        FileOutputStream fout = new FileOutputStream(fitxer);
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(llistaExemplars);


    }
            ;
    public void carregaDades(String camiOrigen) throws BiblioException {
    }


}
