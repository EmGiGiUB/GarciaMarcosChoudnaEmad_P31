package prog2.model;

import prog2.vista.BiblioException;

import java.util.Date;

public abstract class Prestec implements InPrestec{

    protected Date dataCreacio;
    protected Date dataLimitRetorn;
    protected boolean retornat;
    protected Usuari usuari;
    protected Exemplar exemplar;

    public Prestec(Exemplar exemplar, Usuari usuari, Date dataCreacio){
        this.dataCreacio = dataCreacio;
        this.usuari = usuari;
        this.exemplar = exemplar;
        this.retornat = false;
        this.dataLimitRetorn = new Date(this.dataCreacio.getTime() + this.duradaPrestec());
    }
    @Override
    public void setExemplar(Exemplar exemplar){this.exemplar = exemplar;}
    @Override
    public Exemplar getExemplar(){return exemplar;}
    @Override
    public void setUsuari(Usuari usuari){this.usuari = usuari;}
    @Override
    public Usuari getUsuari(){
        return usuari;
    }
    @Override
    public void setDataCreacio(Date data){
        this.dataCreacio = data;
    }
    @Override
    public Date getDataCreacio(){
        return dataCreacio;
    }
    @Override
    public void setDataLimitRetorn(Date data){
        this.dataLimitRetorn = data;
    }
    @Override
    public Date getDataLimitRetorn(){
        return dataLimitRetorn;
    }
    @Override
    public abstract String tipusPrestec();

    @Override
    public void setRetornat(boolean retornat){this.retornat = retornat;}
    @Override
    public boolean getRetornat(){
        return retornat;
    }

    /**
     * Retornar prestec. Llança excepció si el prestec ja es vaig retornar
     */
    @Override
    public void retorna(){
        //literalmente hace lo mismo que el setRetornat()????
        if (retornat) throw new BiblioException("El prèstec ja ha sigut retornat");
        setRetornat(true);
    }

    /**
     * Retornar durada prestec. La durada del prestec depen del tipus de prestec
     */
    @Override
    public abstract long duradaPrestec();

    /**
     * Retornar true si el prestec està endarrerit per a la data actual
     */
    @Override
    public boolean prestecEndarrerit(){
        Date dataActual = new Date();
        long duradaActual = dataActual.getTime() - dataCreacio.getTime();

        return !retornat && (duradaActual > duradaPrestec());
    }

    @Override
    public String toString(){
        return "Tipus = " + tipusPrestec() + ", Exemplar = " + getExemplar() + ", Usuari = " + getUsuari() + ", Data de creació = " + getDataCreacio() + ", Data límit retorn = " + getDataLimitRetorn() + ", Retornat = " + getRetornat();
    }
}
