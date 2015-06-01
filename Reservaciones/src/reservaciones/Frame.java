package reservaciones;

import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;

public class Frame extends JFrame {

    JPanel panel;

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
        
        setSize(600,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new Panel();

        add(panel);

        setVisible(true);
    }
}
