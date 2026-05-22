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
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // 1. Creamos el diálogo pasándole esta ventana como padre y el adaptador
                FrmAfegirUsuari ventanaUsuaris = new FrmAfegirUsuari(AppBiblioUB.this, adaptador);

                // 2. Lo hacemos visible (al ser modal, el usuario no podrá volver al menú hasta cerrarlo)
                ventanaUsuaris.setVisible(true);
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