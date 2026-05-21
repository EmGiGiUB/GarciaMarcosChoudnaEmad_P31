package prog2.model;

import java.io.Serializable;

public class Exemplar implements InExemplar, Serializable {
    private String id;
    private String autor;
    private String titol;
    private boolean admetPrestecLlarg;
    private boolean isDisponible;

    public Exemplar(String id, String titol, String autor, boolean admetPrestecLlarg_) {
        this.id = id;
        this.titol = titol;
        this.autor = autor;
        this.admetPrestecLlarg = admetPrestecLlarg_;
        this.isDisponible = true;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public void setTitol(String titol) {
        this.titol = titol;
    }
    @Override
    public String getTitol() {
        return titol;
    }
    @Override
    public void setAutor(String autor) {
        this.autor = autor;
    }
    @Override
    public String getAutor() {
        return autor;
    }
    @Override
    public void setAdmetPrestecLlarg(boolean admetPrestecLlarg) {
        this.admetPrestecLlarg = admetPrestecLlarg;
    }
    @Override
    public boolean getAdmetPrestecLlarg() {
        return admetPrestecLlarg;
    }

    public boolean isDisponible() {
        return isDisponible;
    }

    public void setDisponible(boolean disponible) {
        this.isDisponible = disponible;
    }

    @Override
    public String toString() {
        return "Id=" + id + ", Titol=" + titol + ", Autor=" + autor + ", Admet Prestec Llarg=" + admetPrestecLlarg +
                ", Disponible=" + isDisponible();
    }
}
