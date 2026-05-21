
package prog2.model;
import prog2.vista.BiblioException;

import java.io.Serializable;
import java.security.KeyStore;
import java.util.Date;

public abstract class Prestec implements InPrestec, Serializable {

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
    public void retorna() throws BiblioException{
        //literalmente hace lo mismo que el setRetornat()????
        if (retornat) throw new BiblioException("El prèstec ja ha sigut retornat");
        setRetornat(true);
        //Decrementamos el número de prestamos.
        if (tipusPrestec().equalsIgnoreCase("Llarg")) {
        usuari.setNumPrestecsLlargs(usuari.getNumPrestecsLlargs()-1);
        } else if (tipusPrestec().equalsIgnoreCase("Normal")) {
            usuari.setNumPrestecsNormals(usuari.getNumPrestecsNormals() - 1);
        }
        //Volvemos a poner el ejemplar en disponible porque ya se a devuelto.
        exemplar.setDisponible(true);
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
        if (retornat) return false;
        Date dataActual = new Date();
        return dataActual.after(dataLimitRetorn);
    }

    @Override
    public String toString(){
        return "Tipus = " + tipusPrestec() + ", Exemplar = " + getExemplar() + ", Usuari = " + getUsuari() + ", Data de creació = " + getDataCreacio() + ", Data límit retorn = " + getDataLimitRetorn() + ", Retornat = " + getRetornat();
    }
}
