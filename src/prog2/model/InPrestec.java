package prog2.model;

import java.util.Date;

public interface InPrestec {
    public void setExemplar(Exemplar exemplar);

    public Exemplar getExemplar();

    public void setUsuari(Usuari usuari);

    public Usuari getUsuari();

    public void setDataCreacio(Date data);

    public Date getDataCreacio();

    public void setDataLimitRetorn(Date data);

    public Date getDataLimitRetorn();

    public String tipusPrestec();

    public void setRetornat(boolean retornat);

    public boolean getRetornat();

    /**
     * Retornar prestec. Llança excepció si el prestec ja es vaig retornar
     */
    public void retorna();

    /**
     * Retornar durada prestec. La durada del prestec depen del tipus de prestec
     */
    public long duradaPrestec();

    /**
     * Retornar true si el prestec està endarrerit per a la data actual
     */
    public boolean prestecEndarrerit();

    @Override
    public String toString();
}
