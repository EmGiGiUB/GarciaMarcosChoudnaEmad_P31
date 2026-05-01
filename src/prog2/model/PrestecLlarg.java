package prog2.model;

import java.util.Date;

public class PrestecLlarg extends Prestec{

    public PrestecLlarg (Exemplar exemplar, Usuari usuari, Date dataCreacio){
        super(exemplar, usuari, dataCreacio);
    }

    public String tipusPrestec(){
        return "Llarg";
    }

    public long duradaPrestec(){
        return 140000L;
    }
}
