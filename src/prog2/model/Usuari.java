package prog2.model;

public class Usuari implements InUsuari {
    @Override
    void setEmail(String email);
    @Override
    String getEmail();
    @Override
    void setNom(String nom);
    @Override
    String getNom();
    @Override
    void setAdreca(String adreca);
    @Override
    String getAdreca();
    @Override
    String tipusUsuari();
    @Override
    void setNumPrestecsNormals(int numPrestecsNormals);
    @Override
    int getNumPrestecsNormals();
    @Override
    void setNumPrestecsLlargs(int numPrestecstLlargs);
    @Override
    int getNumPrestecsLlargs();
    @Override
    int getMaxPrestecsNormals();
    @Override
    int getMaxPrestecsLlargs();

    @Override
    String toString();
}
