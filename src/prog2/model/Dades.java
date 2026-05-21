package prog2.model;

import prog2.vista.BiblioException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Dades implements InDades, Serializable {

    protected LlistaExemplars llistaExemplars = new LlistaExemplars();
    protected LlistaUsuaris llistaUsuaris = new LlistaUsuaris();
    protected LlistaPrestecs llistaPrestecs = new LlistaPrestecs();
    /**
     * Afegeix exemplar. Llança excepció si l'id ja existeix
     */
    @Override
    public void afegirExemplar(String id, String autor, String titol, boolean admetPrestecLlarg) throws BiblioException {
        Exemplar miExemplar = new Exemplar(id, autor, titol, admetPrestecLlarg);
        llistaExemplars.afegir(miExemplar);
    }
    /**
     * Recuperar exemplars. Retorna un ArrayList amb tots els exemplars
     */
    @Override
    public ArrayList<Exemplar> recuperaExemplars(){
        return llistaExemplars.getArrayList();
    }

    /**
     * Afegeix usuari. Llança excepció si l'email ja existeix
     */
    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException{
        if (esEstudiant) {
            Estudiant nuevoEstudiant = new Estudiant(email, nom, adreca);
            llistaUsuaris.afegir(nuevoEstudiant);
        } else {
            Professor nuevoProfessor = new Professor(email, nom, adreca);
            llistaUsuaris.afegir(nuevoProfessor);
        }

    }

    /**
     * Recuperar usuaris. Retorna un ArrayList amb tots els usuaris
     */
    @Override
    public ArrayList<Usuari> recuperaUsuaris(){
        return llistaUsuaris.getArrayList();
    }

    public boolean prestecsEndarrerits (Usuari usuari) {
        //Obtenemos el iterator de la copia de la lista Prestecs.
        Iterator <Prestec> miIterator = llistaPrestecs.getArrayList().iterator();
        while (miIterator.hasNext()) {
            Prestec prestec = miIterator.next();

            //La recorremos buscando si algún préstamo tiene el mismo usuario comparando por email.
            if (prestec.getUsuari().getEmail().equals(usuari.getEmail())) {

                //Si el préstamo está atrasado, devolvemos true.
                if (prestec.prestecEndarrerit()) {
                    return true;
                }
            }
        }
        return false; //En caso contrario devolvemos false.
    }

    /**
     * Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     */

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException{
        Exemplar miExemplar = llistaExemplars.getAt(exemplarPos);
        Usuari miUsuari = llistaUsuaris.getAt(usuariPos);

        if (!miExemplar.isDisponible()) {
            throw new BiblioException("ERROR: L'exemplar ja està prestat.");
        } else if (!miExemplar.getAdmetPrestecLlarg() && esLlarg) {
            throw new BiblioException("ERROR: L'exemplar no admet un préstec de llarg termini.");
        } else if (prestecsEndarrerits(miUsuari)) {
            throw new BiblioException("ERROR: Té préstecs endarrerits.");
        } else if (!esLlarg && miUsuari.getNumPrestecsNormals() >= miUsuari.getMaxPrestecsNormals()) {
            throw new BiblioException("ERROR: L'usuari ha assolit el límit de préstecs normals.");
        } else if ((esLlarg && miUsuari.getNumPrestecsLlargs() >= miUsuari.getMaxPrestecsLlargs())) {
            throw new BiblioException("ERROR: L'usuari ha assolit el límit de préstecs llargs.");
        } else {
            Prestec nuevoPrestec;
            Date dataActual = new Date();
            if (esLlarg) {
                nuevoPrestec = new PrestecLlarg(miExemplar, miUsuari, dataActual);
                miUsuari.setNumPrestecsLlargs(miUsuari.getNumPrestecsLlargs() + 1);
            } else {
                nuevoPrestec = new PrestecNormal(miExemplar, miUsuari, dataActual);
                miUsuari.setNumPrestecsNormals(miUsuari.getNumPrestecsNormals() + 1);
            }
            miExemplar.setDisponible(false);
            llistaPrestecs.afegir(nuevoPrestec);

        }
    }



    /**
     * Retornar préstec. Llança excepció si el prestec ja es vaig retornar.
     * El préstec s'identifica amb la seva posició dins de l'ArrayList
     */
    @Override
    public void retornarPrestec(int position) throws BiblioException{
        if (llistaPrestecs.isEmpty()){
            throw new BiblioException("No hi ha préstecs per retornar.");
        }
        if (position < 0 || position >= llistaPrestecs.getSize()) {
            throw new BiblioException("ERROR: Posició de préstec no vàlida.");
        }
        Prestec miPrestec = llistaPrestecs.getAt(position);
        Usuari usuari = miPrestec.getUsuari();
        miPrestec.retorna();

    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecs(){
        return llistaPrestecs.getArrayList();
    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb els préstecs no retornats
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats(){

        Iterator<Prestec> iterator = llistaPrestecs.getArrayList().iterator();
        ArrayList<Prestec> p = new ArrayList<>();
        while (iterator.hasNext()) {
            Prestec prestec = iterator.next();
            if (!prestec.getRetornat()) {
                p.add(prestec);
            }
        }
        return p;
    }
}

