package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.*;

public class FrmAfegirExemplar extends JDialog {
    private JPanel contentPane;
    private JButton btnTornar;
    private JTextField txtTitol;
    private JCheckBox chkPrestecLlarg;
    private JTextField txtAutor;
    private JTextField txtId;
    private JButton btnAfegirExemplar;
    private JLabel lblTitol;
    private JLabel lblAutor;
    private JLabel lblId;

    private Adaptador adaptador;


    public FrmAfegirExemplar(JDialog parent, boolean modal, Adaptador adaptador) {
        super(parent, modal);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(modal);
        getRootPane().setDefaultButton(btnAfegirExemplar);


        btnAfegirExemplar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btnTornar.addActionListener(new ActionListener() {
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

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        try {
            String id = txtId.getText().trim();
            String titol = txtTitol.getText().trim();
            String autor = txtAutor.getText().trim();
            boolean esLlarg = chkPrestecLlarg.isSelected();

            if (id.isEmpty() || titol.isEmpty() || autor.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Tots els camps (ID, Títol i Autor) són obligatoris.");
                return;
            }

            adaptador.afegirExemplar(id, titol, autor, esLlarg);
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en afegir l'exemplar: " + ex.getMessage());
        }
    }
    private void onCancel() {
        dispose();
    }
}
