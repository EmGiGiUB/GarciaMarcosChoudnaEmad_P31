package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FrmGestioUsuaris extends JDialog {
    private JPanel contentPane;
    private JList<String> lstUsuaris;
    private JButton btnAfegirUsuari;
    private JButton btnTornar;

    private Adaptador adaptador;

    public FrmGestioUsuaris(JFrame parent, boolean modal, Adaptador adaptador) {
        super(parent, modal);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(modal);
        getRootPane().setDefaultButton(btnAfegirUsuari);

        actualitzarLlista();

        btnAfegirUsuari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmAfegirUsuari dialog = new FrmAfegirUsuari(FrmGestioUsuaris.this, true, adaptador);
                dialog.pack();
                dialog.setLocationRelativeTo(FrmGestioUsuaris.this);
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

        ArrayList<String> llistaUsuarisStr = adaptador.mostrarUsuaris();
        if (llistaUsuarisStr != null) {
            for (String uStr : llistaUsuarisStr) {
                model.addElement(uStr);
            }
        }

        lstUsuaris.setModel(model);
    }
}