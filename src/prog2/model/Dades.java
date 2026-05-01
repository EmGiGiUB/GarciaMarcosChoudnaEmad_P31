package prog2.model;

import prog2.vista.BiblioException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Dades implements InDades {

    protected ArrayList<Exemplar> llistaExemplars = new ArrayList<>();
    protected ArrayList<Usuari> llistaUsuaris = new ArrayList<>();
    protected ArrayList<Prestec> llistaPrestecs = new ArrayList<>();
    /**
     * Afegeix exemplar. Llança excepció si l'id ja existeix
     */
    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException {
        Iterator<Exemplar> iterator = llistaExemplars.iterator();

        while(iterator.hasNext()){
            Exemplar exemplarActual = iterator.next();
            if(exemplarActual.getId().equals(id)){
                throw new BiblioException("Un exemplar amb aquest ID ja està registrat");
            }
        }
        new Exemplar(id, autor, titol, admetPrestecLlarg);
    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els exemplars
     */
    @Override
    public ArrayList<Exemplar> recuperaExemplars(){
        //quiero entender que aquí hay un fallo, es decir, sera recuperar ejemplares
        Iterator<Prestec> iterator = llistaPrestecs.iterator();

        while(iterator.hasNext()){
            Prestec prestecActual = iterator.next();
            prestecActual.retorna();
        }

        ArrayList<Exemplar> exemplars = new ArrayList<>(llistaExemplars);
        return exemplars;
    }

    /**
     * Afegeix usuari. Llança excepció si l'email ja existeix
     */
    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException{
        Iterator<Usuari> iterator = llistaUsuaris.iterator();

        while(iterator.hasNext()){
            Usuari usuariActual = iterator.next();
            if(usuariActual.getEmail().equals(email)){
                throw new BiblioException("Un usuari amb aquest email ja està registrat");
            }
        }
        new Usuari(email, nom, adreca, esEstudiant);
    }

    /**
     * Recuperar usuaris. Retorna un ArrayList amb tots els usuaris
     */
    @Override
    public ArrayList<Usuari> recuperaUsuaris(){
        ArrayList<Usuari> usuaris = new ArrayList<>(llistaUsuaris);
        return usuaris;
    }

    /**
     * Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     */

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException{
        if (exemplarPos < 0 || usuariPos < 0){
            throw new BiblioException("L'índex donat no és correcte.");
        }
        if (llistaUsuaris.isEmpty() || llistaExemplars.isEmpty()){
            throw new BiblioException("No es pot accedir a l'index ja que les llistes estan buides.");
        }
        Date dataActual = new Date();
        if (esLlarg) {
            new PrestecLlarg(llistaExemplars.get(exemplarPos), llistaUsuaris.get(usuariPos), dataActual);
        }
        else new PrestecNormal(llistaExemplars.get(exemplarPos), llistaUsuaris.get(usuariPos), dataActual);
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
        llistaPrestecs.get(position).retorna();
    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecs(){
        ArrayList<Prestec> prestecs = new ArrayList<>(llistaPrestecs);
        return prestecs;
    }

    /**
     * Recuperar préstecs. Retorna un ArrayList amb els préstecs no retornats
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats(){
        ArrayList<Prestec> prestecsNoRetornats = new ArrayList<>();
        Iterator<Prestec> iterator = llistaPrestecs.iterator();

        while(iterator.hasNext()){
            Prestec prestecActual = iterator.next();
            if (!prestecActual.retornat){
                prestecsNoRetornats.add(prestecActual);
            }
        }
        return prestecsNoRetornats;
    }
}

