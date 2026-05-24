package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmGestioPrestecs extends JDialog {
    private JPanel contentPane;
    private JCheckBox chkNoRetornats;
    private JList<String> lstPrestecs;
    private JButton btnAfegirPrestec;
    private JButton btnRetornarPrestec;
    private JButton btnTornar;
    private JLabel lblPrestecs;

    private Adaptador adaptador;

    public FrmGestioPrestecs(JFrame parent, boolean modal, Adaptador adaptador) {
        super(parent, modal);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(modal);
        getRootPane().setDefaultButton(btnAfegirPrestec);

        actualitzarLlista();

        chkNoRetornats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualitzarLlista();
            }
        });

        btnRetornarPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int indexSeleccionat = lstPrestecs.getSelectedIndex();

                if (indexSeleccionat == -1) {
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Si us plau, selecciona un préstec de la llista.", "Atenció", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    adaptador.retornarPrestec(indexSeleccionat);
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Préstec retornat correctament.");

                    actualitzarLlista();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(FrmGestioPrestecs.this, "Error en retornar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnAfegirPrestec.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirPrestec dialog = new FrmAfegirPrestec(FrmGestioPrestecs.this, true, adaptador);
                dialog.pack();
                dialog.setLocationRelativeTo(FrmGestioPrestecs.this);
                dialog.setVisible(true);

                actualitzarLlista();
            }
        });

        btnTornar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void actualitzarLlista() {
        DefaultListModel<String> model = new DefaultListModel<>();
        ArrayList<String> llistaPrestecsStr = adaptador.mostrarPrestecs();

        if (llistaPrestecsStr != null) {
            for (String pStr : llistaPrestecsStr) {

                if (chkNoRetornats.isSelected()) {
                    String textoMinusculas = pStr.toLowerCase();
                    if (textoMinusculas.contains("retornat = true")) {
                        continue;
                    }
                }
                model.addElement(pStr);
            }
        }
        lstPrestecs.setModel(model);
    }
}