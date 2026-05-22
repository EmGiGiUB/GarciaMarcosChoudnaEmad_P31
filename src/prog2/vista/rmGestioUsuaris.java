package prog2.vista;

import javax.swing.*;

public class rmGestioUsuaris extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    public rmGestioUsuaris() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }
}
