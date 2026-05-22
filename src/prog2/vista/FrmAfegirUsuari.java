package prog2.vista;

import javax.swing.*;

public class FrmAfegirUsuari extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public FrmAfegirUsuari() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }
}
