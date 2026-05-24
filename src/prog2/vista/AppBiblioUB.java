package prog2.vista;

import prog2.adaptador.Adaptador; // Importa tu adaptador
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppBiblioUB extends JFrame {

    private JPanel formMenu; // Asegúrate de que este es el nombre del JPanel principal en el .form
    private JButton btnGestioUsuaris;
    private JButton btnGestioExemplars;
    private JButton btnGestioPrestecs;
    private JButton btnGuardarDades;
    private JButton btnCargarDades;

    // Añadimos el adaptador como atributo
    private Adaptador adaptador;

    public AppBiblioUB() {
        // 1. Inicializamos la lógica
        adaptador = new Adaptador();

        // 2. Configuramos la ventana principal
        setTitle("Biblioteca UB - Menú Principal");

        // 3. Conectamos el diseño del .form con este JFrame
        setContentPane(formMenu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // Ajusta la ventana al tamaño de los botones
        setLocationRelativeTo(null); // La centra en la pantalla
        btnGestioUsuaris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Instanciamos la ventana de gestión de usuarios pasándole esta ventana como padre
                FrmGestioUsuaris dialog = new FrmGestioUsuaris(AppBiblioUB.this, true, adaptador);
                dialog.pack();
                dialog.setLocationRelativeTo(AppBiblioUB.this); // Centrado
                dialog.setVisible(true); // Abre de forma modal
            }
        });
        btnGestioExemplars.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioExemplars dialog = new FrmGestioExemplars(AppBiblioUB.this, true, adaptador);
                dialog.pack();
                dialog.setLocationRelativeTo(AppBiblioUB.this);
                dialog.setVisible(true);
            }
        });
        btnGestioPrestecs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioPrestecs dialog = new FrmGestioPrestecs(AppBiblioUB.this, true, adaptador);
                dialog.pack();
                dialog.setLocationRelativeTo(AppBiblioUB.this);
                dialog.setVisible(true);
            }
        });
        btnGuardarDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Creamos el explorador de archivos nativo de Java
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona on vols guardar el fitxer de dades");

                // 2. Abrimos el cuadro en modo "Guardar" centrado en esta ventana
                int seleccio = fileChooser.showSaveDialog(AppBiblioUB.this);

                // 3. Si el usuario selecciona una carpeta y le da a "Guardar"
                if (seleccio == JFileChooser.APPROVE_OPTION) {
                    try {
                        // Obtenemos la ruta completa del archivo elegido (ej: C:\Usuarios\Escritorio\biblioteca.dat)
                        String rutaFitxer = fileChooser.getSelectedFile().getAbsolutePath();

                        // Llamamos al método de tu Adaptador (guardaDades)
                        adaptador.guardaDades(rutaFitxer);

                        // Mostramos un mensaje de confirmación exitosa
                        JOptionPane.showMessageDialog(AppBiblioUB.this,
                                "Dades guardades correctament a:\n" + rutaFitxer,
                                "Èxit", JOptionPane.INFORMATION_MESSAGE);

                    } catch (BiblioException ex) {
                        // Si ocurre un error de entrada/salida, capturamos tu BiblioException
                        JOptionPane.showMessageDialog(AppBiblioUB.this,
                                ex.getMessage(),
                                "Error en guardar", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // --------------------------------------------------------------------
        // 5. ACCIÓN: CARREGAR DADES (Usa JFileChooser obligatorio)
        // --------------------------------------------------------------------
        btnCargarDades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Creamos el explorador de archivos nativo de Java
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Selecciona el fitxer de dades a carregar");

                // 2. Abrimos el cuadro en modo "Abrir/Cargar"
                int seleccio = fileChooser.showOpenDialog(AppBiblioUB.this);

                // 3. Si el usuario elige un archivo válido y le da a "Obrir"
                if (seleccio == JFileChooser.APPROVE_OPTION) {
                    try {
                        String rutaFitxer = fileChooser.getSelectedFile().getAbsolutePath();

                        // Llamamos al método de tu Adaptador (carregaDades)
                        adaptador.carregaDades(rutaFitxer);

                        JOptionPane.showMessageDialog(AppBiblioUB.this,
                                "Dades carregades correctament des del fitxer.",
                                "Èxit", JOptionPane.INFORMATION_MESSAGE);

                    } catch (BiblioException ex) {
                        // Capturamos el error si el archivo está corrupto, no existe o no es compatible
                        JOptionPane.showMessageDialog(AppBiblioUB.this,
                                ex.getMessage(),
                                "Error en carregar", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

    }

    public static void main(String[] args) {
        // Lanzamos la aplicación
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB ventana = new AppBiblioUB();
            ventana.setVisible(true);
        });
    }
}