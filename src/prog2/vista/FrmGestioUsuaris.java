package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioUsuaris extends JDialog {
    private JPanel contentPane;
    private JButton btnLlista;
    private JButton btnAfegirUsuari;

    private Adaptador adaptador;

    public FrmGestioUsuaris(JFrame parent, Adaptador adaptador) {
        // Inicialización del JDialog (Padre, Título, Modal)
        super(parent, "Gestió d'Usuaris", true);
        this.adaptador = adaptador;

        setContentPane(contentPane);

        // Configuramos el botón por defecto
        getRootPane().setDefaultButton(btnLlista);

        // --- LISTENERS (Esqueletos) ---

        // Acción para el botón de Listar
        btnLlista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí irá la lógica para refrescar la JTable o JList
            }
        });

        // Acción para el botón de Añadir Usuario
        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí llamarás a: new FrmAfegirUsuari(parent, adaptador).setVisible(true);
            }
        });

        // Ajustes de visualización
        pack();
        setLocationRelativeTo(parent);
    }
}