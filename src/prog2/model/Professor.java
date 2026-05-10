package prog2.model;

import java.io.Serializable;

public class Professor extends Usuari implements Serializable {
    public Professor(String email, String nom, String adreca) {
        super(email, nom, adreca);
        setNumPrestecsLlargs(2);
        setNumPrestecsNormals(2);
    }

    @Override
    public int getMaxPrestecsNormals() {
        return 2;
    }

    @Override
    public int getMaxPrestecsLlargs() {
        return 2;
    }

    @Override
    public String tipusUsuari() {
        return "Professor";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
