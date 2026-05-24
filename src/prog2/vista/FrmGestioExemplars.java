package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmGestioExemplars extends JDialog {
    private JPanel contentPane;
    private JButton btnAfegirExemplar;
    private JButton btnTornar;
    private JList<String> lstExemplars;
    private JLabel lblTitolLlista;

    private Adaptador adaptador;

    public FrmGestioExemplars(JFrame parent, boolean modal, Adaptador adaptador) {
        super(parent, modal);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(modal);
        getRootPane().setDefaultButton(btnAfegirExemplar);

        actualitzarLlista();

        btnAfegirExemplar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirExemplar dialog = new FrmAfegirExemplar(FrmGestioExemplars.this, true, adaptador);
                dialog.pack();
                dialog.setLocationRelativeTo(FrmGestioExemplars.this);
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

        ArrayList<String> llistaExemplarsStr = adaptador.mostrarExemplars();

        if (llistaExemplarsStr != null) {
            for (String exStr : llistaExemplarsStr) {
                model.addElement(exStr);
            }
        }

        lstExemplars.setModel(model);
    }
}