package prog2.model;

import java.util.Date;

public class Prestec implements InPrestec{
    @Override
    void setExemplar(Exemplar exemplar);
    @Override
    Exemplar getExemplar();
    @Override
    void setUsuari(Usuari usuari);
    @Override
    Usuari getUsuari();
    @Override
    void setDataCreacio(Date data);
    @Override
    Date getDataCreacio();
    @Override
    void setDataLimitRetorn(Date data);
    @Override
    Date getDataLimitRetorn();
    @Override
    String tipusPrestec();
    @Override
    void setRetornat(boolean retornat);
    @Override
    boolean getRetornat();

    /**
     * Retornar prestec. Llança excepció si el prestec ja es vaig retornar
     */
    @Override
    void retorna();

    /**
     * Retornar durada prestec. La durada del prestec depen del tipus de prestec
     */
    @Override
    long duradaPrestec();

    /**
     * Retornar true si el prestec està endarrerit per a la data actual
     */
    @Override
    boolean prestecEndarrerit();

    @Override
    String toString();
}
