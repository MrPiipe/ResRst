package reservaciones;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Frame extends JFrame {

    Panel panel;
    PanelImagen panelImagen;

    Frame(){
        super("Reservaciones");
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).
                    log(java.util.logging.Level.SEVERE, null, e);
        }

        setSize(600,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(null);

        panel = new Panel(this);
        panelImagen = new PanelImagen();

        panel.setBounds(0,0,600,200);
        panelImagen.setBounds(0,200,600,300);

        add(panel);
        add(panelImagen);

        setVisible(true);
    }
}
