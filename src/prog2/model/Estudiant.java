package prog2.model;

public class Estudiant extends Usuari{
    public Estudiant(String email, String nom, String adreca) {
        super(email, nom, adreca);
        setNumPrestecsLlargs(1);
        setNumPrestecsNormals(2);
    }

    @Override
    public String tipusUsuari() {
        return "Estudiant";
    }

    @Override
    public int getMaxPrestecsNormals() {
        return 2;
    }

    @Override
    public int getMaxPrestecsLlargs() {
        return 1;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
