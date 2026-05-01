package prog2.model;

import java.util.Date;

public class PrestecNormal extends Prestec {

    public PrestecNormal (Exemplar exemplar, Usuari usuari, Date dataCreacio){
        super(exemplar, usuari, dataCreacio);
    }

    public String tipusPrestec(){
        return "Normal";
    }

    public long duradaPrestec(){
        return 70000L;
    }
}
