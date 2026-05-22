package prog2.vista;

import javax.swing.*;

public class FrmGestioExemplars extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public FrmGestioExemplars() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }
}
