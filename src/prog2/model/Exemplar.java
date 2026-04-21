package prog2.model;

public class Exemplar implements InExemplar {
    @Override
    void setId(String id);
    @Override
    String getId();
    @Override
    void setTitol(String titol);
    @Override
    String getTitol();
    @Override
    void setAutor(String autor);
    @Override
    String getAutor();
    @Override
    void setAdmetPrestecLlarg(boolean admetPrestecLlarg);
    @Override
    boolean getAdmetPrestecLlarg();

    @Override
    String toString();
}
