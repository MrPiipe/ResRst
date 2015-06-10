package reservaciones;

import javax.swing.*;

public class PanelImagen extends JPanel{

    ImageIcon img;
    JLabel lblImg;

    public void setImage(String nombre){
         img = new ImageIcon(getClass().getResource("/Imagenes/"+nombre+".png"));
         lblImg = new JLabel(img);
         add(lblImg);
    }
}
