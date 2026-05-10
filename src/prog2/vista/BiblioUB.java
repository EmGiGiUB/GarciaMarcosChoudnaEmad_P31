/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.vista;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import prog2.adaptador.Adaptador;
import prog2.model.Exemplar;
import prog2.model.LlistaExemplars;
import prog2.model.Prestec;
import prog2.model.Usuari;

/**
 *
 * @author dortiz
 */
public class BiblioUB {
    
    // Declarem les constants del menu principal
    static private enum OpcionsMenuPrincipal {
        MENU_PRINCIPAL_EXEMPLARS,
        MENU_PRINCIPAL_USUARIS,
        MENU_PRINCIPAL_PRESTECS,
        MENU_PRINCIPAL_SAVE,
        MENU_PRINCIPAL_LOAD,
        MENU_PRINCIPAL_EXIT};
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuPrincipal={"Gestió Exemplars",
                                               "Gestió Usuaris",
                                               "Gestió Prestecs",
                                               "Guardar Dades",
                                               "Recuperar Dades",
                                               "Sortir"};

    static private enum OpcionsMenuGestioExemplars {
        MENU_GESTIO_EXEMPLARS_ADD,
        MENU_GESTIO_EXEMPLARS_VIEW,
        MENU_GESTIO_EXEMPLARS_EXIT
    };
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioExemplars ={"Afegir Exemplar",
                                                      "Visualitzar Exemplars",
                                                      "Sortir"};

    static private enum OpcionsMenuGestioClients {
        MENU_GESTIO_USUARIS_ADD,
        MENU_GESTIO_USUARIS_VIEW,
        MENU_GESTIO_USUARIS_EXIT
    };
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioUsuaris ={"Afegir Usuari",
                                                    "Visualitzar Usuaris",
                                                    "Sortir"};

    static private enum OpcionsMenuGestioPrestecs {
        MENU_GESTIO_PRESTECS_ADD,
        MENU_GESTIO_PRESTECS_REMOVE,
        MENU_GESTIO_PRESTECS_VIEW,
        MENU_GESTIO_PRESTECS_VIEW_URG,
        MENU_GESTIO_PRESTECS_EXIT
    };
    
    // Declarem descripcions personalitzades per a les opcions del menú principal
    static private String[] descMenuGestioPrestecs ={"Afegir Prestec",
                                                     "Retornar Prestec",
                                                     "Visualitzar Prestecs",
                                                     "Visualitzar Prestecs no Retornats",
                                                     "Sortir"};

    
    /** Adaptador de l'aplicació */
    private Adaptador adaptador;
    
    /* Constructor*/
    public BiblioUB() {
        adaptador = new Adaptador();
    }
     
    public void gestioBiblioUB() {
        // Creem un objecte per llegir des del teclat
        Scanner sc = new Scanner(System.in);
        
        // Creem l'objecte per al menú. Li passem com a primer paràmetre el nom del menú
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu principal", OpcionsMenuPrincipal.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);
        
        OpcionsMenuPrincipal opcio;
        do {
            // Mostrem les opcions del menú i demanem una opció
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            // Fem les accions necessàries per a l'opció triada
            switch(opcio) {
                case MENU_PRINCIPAL_EXEMPLARS:
                    // Mostra el menú per a la gestió d'exemplars
                    menuGestioExemplars(sc);
                    break;

                case MENU_PRINCIPAL_USUARIS:
                    // Mostra el menú per a la gestió d'usuaris
                    menuGestioUsuaris(sc);
                    break;

                case MENU_PRINCIPAL_PRESTECS:
                    // Mostra el menú per a la gestió de prestecs
                    menuGestioPrestecs(sc);
                    break;

                case MENU_PRINCIPAL_SAVE:
                    // Guardar dades
                    String dstFile = getFilePath(sc,false); // Obtenir el fitxer de sortida
                    if(dstFile != null) {
                        // Guardar les dades al fitxer triat
                        try {
                             this.adaptador.guardaDades(dstFile);
                             System.err.println("Dades guardades");
                        } catch (BiblioException ex) {
                            System.out.println("Error guardant les dades: " + ex.getMessage());
                        }
                    }                   
                    break;
                case MENU_PRINCIPAL_LOAD:
                    // Carregar dades                   
                    String srcFile = getFilePath(sc,false); // Obtenir el fitxer d'entrada
                    if(srcFile != null) {
                        // Carregar les dades del fitxer triat
                        try {
                             this.adaptador.carregaDades(srcFile);
                             System.err.println("Dades carregades");
                        } catch(BiblioException ex) {
                            System.out.println("Error carregant les dades." + ex.getMessage());
                        }
                    }     
                    break;
                case MENU_PRINCIPAL_EXIT:
                    // sortir      1
                    System.err.println("Sortint de l'aplicació...");
                    break;
            }
        } while(opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_EXIT);
    }
    
    private void menuGestioExemplars(Scanner sc) {

        Menu<OpcionsMenuGestioExemplars> menu = new Menu<>("Menu gestió d'exemplars", OpcionsMenuGestioExemplars.values());

        menu.setDescripcions(descMenuGestioExemplars);

        OpcionsMenuGestioExemplars opcio;

        do{
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch (opcio){
                case MENU_GESTIO_EXEMPLARS_ADD:
                    afegirExemplar(sc);
                    break;

                case MENU_GESTIO_EXEMPLARS_VIEW:
                    listarExemplars();
                    break;

                case MENU_GESTIO_EXEMPLARS_EXIT:
                    break;

            }
        } while (opcio != OpcionsMenuGestioExemplars.MENU_GESTIO_EXEMPLARS_EXIT);
    }
    
    /**
     * Afegir un nou article
     * @param sc
     */
    
    private void afegirExemplar(Scanner sc){
        String titol;
        String autor;
        String id;
        String admetPrestecLlarg;
        System.out.println("Introduce el título del libro: ");
        titol = sc.nextLine();
        System.out.println("Introduce el nombre del autor: ");
        autor = sc.nextLine();
        System.out.println("Introduce el id numérico: ");
        id = sc.nextLine();
        System.out.println("¿Admite préstamo de largo termino? (si o no)");
        admetPrestecLlarg = sc.nextLine();

        if (admetPrestecLlarg.equalsIgnoreCase("si")) {
            adaptador.afegirExemplar(id, autor, titol, true);
        } else {
            adaptador.afegirExemplar(id, autor, titol, false);
        }
    }

    private void listarExemplars () {
        ArrayList<Exemplar> llista = adaptador.mostarExemplars();

        if (llista.isEmpty()) {
            System.out.println("No hay ejemplares para mostrar.");
            return;
        }
        List<String> toStringExemplars = new ArrayList<>();

        for (Exemplar e : llista) {
            toStringExemplars.add(e.toString());
        }
        showList("LLISTA D'EXEMPLARS", toStringExemplars);
    }

    private void menuGestioUsuaris(Scanner sc) {

        Menu<OpcionsMenuGestioClients> menu = new Menu<>("Menu gestió d'clients", OpcionsMenuGestioClients.values());

        menu.setDescripcions(descMenuGestioUsuaris);

        OpcionsMenuGestioClients opcio;

        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);
            switch (opcio) {
                case MENU_GESTIO_USUARIS_ADD:
                    afegirUsuari(sc);
                    break;

                case MENU_GESTIO_USUARIS_VIEW:
                  listarUsuaris();
                    break;

                case MENU_GESTIO_USUARIS_EXIT:
                    break;
            }
        } while (opcio != OpcionsMenuGestioClients.MENU_GESTIO_USUARIS_EXIT);
    }
    
    /**
     * Afegir un nou usuari
     * @param sc
     */
    
    private void afegirUsuari(Scanner sc){
        String nom;
        String email;
        String adreca;
        String isEstudiant;

        System.out.println("Introduce el nombre del usuario: ");
        nom = sc.nextLine();
        System.out.println("Introduce el email: ");
        email = sc.nextLine();
        System.out.println("Introduce la dirección: ");
        adreca = sc.nextLine();
        System.out.println("¿El cliente/usuario es estudiant? (si o no)");
        isEstudiant = sc.nextLine();
        try {
            if (isEstudiant.equalsIgnoreCase(("si"))) {
                adaptador.afegirUsuari(email, nom, adreca, true);
            } else {
                adaptador.afegirUsuari(email, nom, adreca, false);
            }
        } catch (BiblioException e) {
            System.out.println(e.getMessage());
        }
    }
    private void listarUsuaris() {
        // 1. Pedimos la lista al adaptador
        ArrayList<Usuari> llista = adaptador.mostrarUsuaris();
        if (llista.isEmpty()) {
            System.out.println("No hay ningun usuario registrado.");
            return;
        }
        // 2. Preparamos la lista de Strings para el formato
        List<String> toStringUsuaris = new ArrayList<>();
        for (Usuari u : llista) {
            toStringUsuaris.add(u.toString());
        }

        // 3. Usamos el metodo para mostrar la lista.
        showList("LLISTA D'USUARIS", toStringUsuaris);
    }

    private void menuGestioPrestecs(Scanner sc) {
        adaptador.carregaDades(getFilePath(sc, true));
        Menu<OpcionsMenuGestioPrestecs> menu = new Menu<>("Menu gestió d'Prestecs", OpcionsMenuGestioPrestecs.values());

        OpcionsMenuGestioPrestecs opcio;

        do {
            menu.mostrarMenu();
            opcio = menu.getOpcio(sc);

            switch (opcio) {
                case MENU_GESTIO_PRESTECS_ADD:
                    afegirPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_REMOVE:
                    retornaPrestec(sc);
                    break;
                case MENU_GESTIO_PRESTECS_VIEW:
                    listarPrestecs();
                    break;
                case MENU_GESTIO_PRESTECS_EXIT:
                    break;

            }
        } while(opcio != OpcionsMenuGestioPrestecs.MENU_GESTIO_PRESTECS_EXIT);

    }
    
    /**
     * Afegir un nou préstec
     * @param sc
     */

    private void afegirPrestec(Scanner sc){
        int exemplarPos;
        int usuariPos;
        String esLlarg;

        listarExemplars();
        System.out.println("¿Qué ejemplar va a ser prestado? (Introduce el número): ");
        exemplarPos = sc.nextInt();
        listarUsuaris();
        System.out.println("¿A qué usuario desea añadirle el préstamo? (Introduzca el número): ");
        usuariPos = sc.nextInt();
        sc.nextLine();
        System.out.println("¿Es un préstamo de largo termino? (sí o no): ");
        esLlarg = sc.nextLine();
        try {
            if (esLlarg.equalsIgnoreCase("si")) {
                adaptador.afegirPrestec(exemplarPos, usuariPos, true);
            } else {
                adaptador.afegirPrestec(exemplarPos, usuariPos, false);
            }
            System.out.println("Préstec realitzat correctament.");
        } catch (BiblioException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(new BiblioException("ERROR: Dada introduida no valida.").getMessage());
            sc.nextLine();
        }
    }

    private void listarPrestecs () {
        // 1. Pedimos la lista al adaptador
        ArrayList<Prestec> llista = adaptador.mostrarPrestecs();
        if (llista.isEmpty()) {
            System.out.println("No hay ningun usuario registrado.");
            return;
        }
        // 2. Preparamos la lista de Strings para el formato
        List<String> toStringUsuaris = new ArrayList<>();
        for (Prestec u : llista) {
            toStringUsuaris.add(u.toString());
        }

        // 3. Usamos el método de apoyo que ya tienes
        showList("LLISTA D'PRESTECS", toStringUsuaris);
    }

    private void retornaPrestec(Scanner sc){
        listarPrestecs(); // Muestra todos los préstamos con su índice [i]
        if (/*si la lista estaba vacía, el método listar ya habrá hecho el return*/)

            System.out.println("Selecciona el número del préstamo a retornar: ");
        try {
            int pos = Integer.parseInt(sc.nextLine()); // Usando el truco de la línea completa
            adaptador.retornarPrestec(pos);
            System.out.println("Libro devuelto con éxito.");
        } catch (BiblioException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("ERROR: Selección no válida.");
        }
    }

     /**
     * Mostra una llista d'objectes
     * @param title Títol a posar com a capçalera
     * @param lines Llista d'objectes per mostrar
     */
    private void showList(String title, List<String> lines) {
        System.out.println("============================================");
        System.out.println(title);
        System.out.println("============================================");
        int i = 0;
        for(String l : lines) {
            System.out.println("\t[" + (i++) + "] " + l);
        }
        System.out.println("============================================");
    }


    /**
     * Demana el camí d'un fitxer
     * @param sc Objecte per a la lectura de dades de teclat
     * @param mustExist Exigeix que el fitxer existeixi (True) o no (False)
     * @return Ruta al fitxer entrada per l'usuari o null si s'ha cancelat
     */
    private String getFilePath(Scanner sc, boolean mustExist) {
        String filePath = null;
        boolean existe = false;

        while (!existe) {
            System.out.println("Entra ruta completa fitxer (o ENTER per ometre):");
            filePath = sc.nextLine();

            if (filePath.isEmpty()) return null;

            if (mustExist) {
                File info = new File(filePath);
                if (info.exists() && info.isFile()) {
                    existe = true;
                } else {
                    System.out.println("ERROR: El fitxer no existeix. Torna a intentar-ho");
                }
            } else {
                existe = true;
                }
            }
        return filePath;
        }
    }
