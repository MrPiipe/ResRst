package reservaciones;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class FrameImagen extends JFrame{
    private PanelImagen panel;
    private String img;
 
    FrameImagen(String img){
        super("Ubicaciones");
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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
        this.img = img;
        setSize(600,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getRootPane());

        panel = new PanelImagen(img);

        add(panel);

        setVisible(true);
    }

}
