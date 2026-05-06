package prog2.model;

public class Professor extends Usuari{
    public Professor(String email, String nom, String adreca) {
        super(email, nom, adreca);
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
