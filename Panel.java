import javax.swing.*;
import java.awt.event.*;

public class Panel extends JPanel implements ActionListener {

    JButton btnReservar;
    JLabel lblLugar;
    JLabel lblFecha;
    JLabel lblHora;

    JComboBox boxLugar;
    JComboBox boxFecha;
    JComboBox boxHora;

    String[] strLugar;
    String[] strFecha;
    String[] strHora;

    Panel(){
        setLayout(null);

        strLugar = new String[3];
        strFecha = new String[3];
        strHora = new String[3];
        
        for(int i=0;i<3;i++){
            strLugar[i] = "Lugar";
            strFecha[i] = "Fecha";
            strHora[i] = "Hora";
        }

        boxLugar = new JComboBox(strLugar);
        boxFecha = new JComboBox(strFecha);
        boxHora = new JComboBox(strHora);
        lblLugar = new JLabel("Lugar");    
        lblFecha = new JLabel("Fecha");
        lblHora = new JLabel("Hora");

        btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(this);

        lblLugar.setBounds(50,40,50,20);
        lblFecha.setBounds(150,40,50,20);
        lblHora.setBounds(250,40,50,20);
        btnReservar.setBounds(350,110,100,20);
        boxLugar.setBounds(50,70,80,20);
        boxFecha.setBounds(150,70,80,20);
        boxHora.setBounds(250,70,80,20);

        add(boxLugar);
        add(boxFecha);
        add(boxHora);
        add(lblLugar);
        add(lblFecha);
        add(lblHora);
        add(btnReservar);
    }

    public void actionPerformed(ActionEvent eve){
        System.out.println("Felicidades reservaste, eres un genio");
    }
}
