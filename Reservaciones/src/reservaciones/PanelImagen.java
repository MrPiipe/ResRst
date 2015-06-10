package reservaciones;

import javax.swing.*;

public class PanelImagen extends JPanel{

    ImageIcon img;
    JLabel lblImg;

    PanelImagen(){
        setLayout(null);
    }

    public void getImg(String nombre){
        img = new ImageIcon(getClass().getResource("/Imagenes/"+nombre+".png"));
    }

    public int[] getImgSize(){
        int[] size = new int[2];
        size[0] = img.getIconWidth();
        size[1] = img.getIconHeight();
        return size;
    }

    public void setImage(){
        int[] dimension = getImgSize();
        lblImg = new JLabel(img);
        lblImg.setBounds(10,0,dimension[0],dimension[1]);
        add(lblImg);
        if(dimension[0] > 600)
            setBounds(0,170,dimension[0],dimension[1]);
        else
            setBounds(0,170,600,dimension[1]);
    }
}
