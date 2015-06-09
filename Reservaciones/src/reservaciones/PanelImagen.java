package reservaciones;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelImagen extends JPanel{

    String nombreImagen;

    protected PanelImagen(String nombreImagen) {
        this.nombreImagen = nombreImagen;
        setLayout(null);
    }


    @Override
    public void paint(Graphics grafico){
        repaint();
        Dimension tamaño = getSize();
        //System.out.println(nombreImagen);
        ImageIcon img = new ImageIcon(getClass().getResource("/Imagenes/"+nombreImagen+".png"));
        grafico.drawImage(img.getImage(), 0, 0, tamaño.width, tamaño.height, null);
        setOpaque(false);
        super.paintComponent(grafico);
    }

}
