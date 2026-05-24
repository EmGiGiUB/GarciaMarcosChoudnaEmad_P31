package prog2.vista;

import prog2.adaptador.Adaptador;
import javax.swing.*;
import java.awt.event.*;

public class FrmAfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtNom;
    private JTextField txtEmail;
    private JCheckBox chkEstudiant;
    private JLabel lblNom;

    private Adaptador adaptador;

    public FrmAfegirUsuari(JDialog parent, boolean modal, Adaptador adaptador) {
        super(parent, modal);
        this.adaptador = adaptador;

        setContentPane(contentPane);
        setModal(modal);
        getRootPane().setDefaultButton(buttonOK);

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

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        String nom = txtNom.getText().trim();
        String email = txtEmail.getText().trim();
        boolean esEstudiant = chkEstudiant.isSelected();

        if (nom.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Tots els camps (Nom i Email) són obligatoris.",
                    "Atenció", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            adaptador.afegirUsuari(email, nom,"No especificada", esEstudiant);

            JOptionPane.showMessageDialog(this, "Usuari afegit correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);

            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error en afegir l'usuari: " + ex.getMessage(),
                    "Error de validació", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void onCancel() {
        dispose();
    }
}