package prog2.model;

import java.util.ArrayList;

public class Dades implements InDades {
    /**
     * Afegeix exemplar. Llança excepció si l'id ja existeix
     */
    @Override
    public void afegirExemplar(String id, String titol, String autor, boolean admetPrestecLlarg) throws BiblioException;

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els exemplars
     */
    @Override
    public ArrayList<Exemplar> recuperaExemplars();

    /**
     * Afegeix usuari. Llança excepció si l'email ja existeix
     */
    @Override
    public void afegirUsuari(String email, String nom, String adreca, boolean esEstudiant) throws BiblioException;

    /**
     * Recuperar usuaris. Retorna un ArrayList amb tots els usuaris
     */
    @Override
    public ArrayList<Usuari> recuperaUsuaris();

    /**
     * Afegeix préstec. Ha de fer diferents comprovacions que poden llançar excepcions.
     * Quan s'afegeix el préstec, s'han de tenir en compte les posicions d'exemplar
     * i usuari dins dels seus ArrayLists
     */

    @Override
    public void afegirPrestec(int exemplarPos, int usuariPos, boolean esLlarg) throws BiblioException;

    /**
     * Retornar préstec. Llança excepció si el prestec ja es vaig retornar.
     * El préstec s'identifica amb la seva posició dins de l'ArrayList
     */
    @Override
    public void retornarPrestec(int position) throws BiblioException;

    /**
     * Recuperar préstecs. Retorna un ArrayList amb tots els préstecs
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecs();

    /**
     * Recuperar préstecs. Retorna un ArrayList amb els préstecs no retornats
     */
    @Override
    public ArrayList<Prestec> recuperaPrestecsNoRetornats();
}

