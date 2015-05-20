import javax.swing.*;

public class Frame extends JFrame {

    JPanel panel;

    Frame(){
        super("Reservaciones");
        setSize(500,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new Panel();

        add(panel);

        setVisible(true);
    }
}
