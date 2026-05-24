package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FrmAfegirPrestec extends JDialog {
    private JPanel contentPane;
    private JComboBox<String> cmbUsuaris;
    private JComboBox<String> cmbExemplars;
    private JCheckBox chkPrestecLlarg;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lblUsuaris;
    private JLabel lblExemplar;

    private Adaptador adaptador;

    public FrmAfegirPrestec(JDialog parent, boolean modal, Adaptador adaptador) {
        super(parent, modal);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(modal);
        getRootPane().setDefaultButton(buttonOK);

        configurarDesplegables();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
    }

    private void configurarDesplegables() {
        ArrayList<String> usuaris = adaptador.mostrarUsuaris();
        if (usuaris != null) {
            for (String u : usuaris) {
                cmbUsuaris.addItem(u);
            }
        }

        ArrayList<String> exemplars = adaptador.mostrarExemplars();
        if (exemplars != null) {
            for (String ex : exemplars) {
                cmbExemplars.addItem(ex);
            }
        }
    }

    private void onOK() {
        int indexUsuari = cmbUsuaris.getSelectedIndex();
        int indexExemplar = cmbExemplars.getSelectedIndex();

        if (indexUsuari == -1 || indexExemplar == -1) {
            JOptionPane.showMessageDialog(this, "Cal seleccionar un usuari i un exemplar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            boolean esLlarg = chkPrestecLlarg.isSelected();

            adaptador.afegirPrestec(indexUsuari, indexExemplar, esLlarg);

            JOptionPane.showMessageDialog(this, "Préstec afegit correctament.");
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en afegir el préstec: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}
